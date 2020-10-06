package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.entity.UserCoin;
import io.inaam.main.entity.UserCoinPK;
import io.inaam.main.exception.CoinException;
import io.inaam.main.repository.CoinRepository;
import io.inaam.main.repository.CoinTransactionRepository;
import io.inaam.main.repository.UserCoinRepository;
import io.inaam.main.transformer.CoinTransformer;
import io.inaam.main.util.CoinTransactionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService
{
    private final CoinRepository coinRepository;
    private final CoinTransformer coinTransformer;
    private final RealmService realmService;
    private final UserService userService;
    private final CoinTransactionRepository coinTransactionRepository;
    private final UserCoinRepository userCoinRepository;

    @Override
    public void createCoin(CoinDto coinDto, String realmName)
    {
        Coin coinEntity = coinTransformer.toCoinEntity(coinDto, realmService.getRealm(realmName).getId());
        coinRepository.save(coinEntity);
    }

    @Override
    public List<CoinDto> getCoins(String realmName)
    {
        String realmId = realmService.getRealm(realmName).getId();
        return coinTransformer.toListOfCoinDto(coinRepository.findAllByRealmId(realmId));
    }

    @Override
    @Transactional
    public void createTransactionAndAddUserCoins(String realmName, String userName, List<CoinTransactionDto> coinTransactionDtoList)
    {
        String realmId = realmService.getRealm(realmName).getId();
        String userId = userService.getUserByNameAndRealmId(userName, realmId).getId();
        List<String> coinIds = coinTransactionDtoList.stream()
                                                     .map(CoinTransactionDto::getCoinName)
                                                     .map(coinName -> coinRepository.findByRealmIdAndName(realmId, coinName))
                                                     .map(Coin::getId)
                                                     .collect(Collectors.toList());

        IntStream.range(0, coinIds.size())
                 .forEach(i -> {
                     String coinId = coinIds.get(i);
                     CoinTransactionDto coinTransactionDto = coinTransactionDtoList.get(i);

                     coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(coinTransactionDto,
                                                                                            realmId,
                                                                                            userId,
                                                                                            coinId,
                                                                                            CoinTransactionType.REDEEM));

                     UserCoin userCoin = userCoinRepository.findById(new UserCoinPK(userId, coinId))
                                                           .orElseGet(() -> new UserCoin(userId, coinId, 0));
                     updateUserCoins(userCoin, coinTransactionDto.getCoinCount(), CoinTransactionType.ADD);
                 });
    }

    @Override
    @Transactional
    public void createTransactionAndRedeemUserCoins(String realmName, String userName, List<CoinTransactionDto> coinTransactionDtoList)
    {
        String realmId = realmService.getRealm(realmName).getId();
        String userId = userService.getUserByNameAndRealmId(userName, realmId).getId();
        List<String> coinIds = coinTransactionDtoList.stream()
                                                     .map(CoinTransactionDto::getCoinName)
                                                     .map(coinName -> coinRepository.findByRealmIdAndName(realmId, coinName))
                                                     .map(Coin::getId)
                                                     .collect(Collectors.toList());

        IntStream.range(0, coinIds.size())
                 .forEach(i -> {
                     String coinId = coinIds.get(i);
                     CoinTransactionDto coinTransactionDto = coinTransactionDtoList.get(i);

                     userCoinRepository.findById(new UserCoinPK(userId, coinId))
                                       .filter(userCoin -> userCoin.getBalance() > coinTransactionDto.getCoinCount())
                                       .map(userCoin -> {
                                           coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(coinTransactionDto,
                                                                                                                  realmId,
                                                                                                                  userId,
                                                                                                                  coinId,
                                                                                                                  CoinTransactionType.REDEEM));

                                           updateUserCoins(userCoin, coinTransactionDto.getCoinCount(), CoinTransactionType.REDEEM);
                                           return userCoin;
                                       })
                                       .orElseThrow(() -> new CoinException("Not enough coins!"));
                 });
    }

    private void updateUserCoins(UserCoin userCoin, int coinCountInTransaction, CoinTransactionType transactionType)
    {
        int updatedCoinBalance = CoinTransactionType.ADD.equals(transactionType) ? userCoin.getBalance() + coinCountInTransaction :
                userCoin.getBalance() - coinCountInTransaction;
        userCoin.setBalance(updatedCoinBalance);
        userCoinRepository.save(userCoin);
    }
}
