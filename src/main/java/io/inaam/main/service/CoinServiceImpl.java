package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.entity.UserCoin;
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
    public void addUserCoins(String realmName, String userName, List<CoinTransactionDto> coinTransactionDtoList)
    {
        createTransactionAndUpdateUserCoins(realmName, userName, coinTransactionDtoList, CoinTransactionType.ADD);
    }

    @Override
    @Transactional
    public void redeemUserCoins(String realmName, String userName, List<CoinTransactionDto> coinTransactionDtoList)
    {
        createTransactionAndUpdateUserCoins(realmName, userName, coinTransactionDtoList, CoinTransactionType.REDEEM);
    }

    private void createTransactionAndUpdateUserCoins(String realmName, String userName,
                                                     List<CoinTransactionDto> coinTransactionDtoList,
                                                     CoinTransactionType transactionType)
    {
        String realmId = realmService.getRealm(realmName).getId();

        List<String> coinIds = coinTransactionDtoList.stream()
                                                     .map(CoinTransactionDto::getCoinName)
                                                     .map(coinName -> coinRepository.findByRealmIdAndName(realmId, coinName))
                                                     .map(Coin::getId)
                                                     .collect(Collectors.toList());

        IntStream.range(0, coinIds.size())
                 .forEach(i -> {
                     String coinId = coinIds.get(i);
                     CoinTransactionDto coinTransactionDto = coinTransactionDtoList.get(i);

                     userCoinRepository.findById(coinId).ifPresent(userCoin -> {
                         coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(coinTransactionDto,
                                                                                                realmId,
                                                                                                userService.getUserByNameAndRealmId(userName, realmId).getId(),
                                                                                                coinId,
                                                                                                CoinTransactionType.REDEEM));
                         updateUserCoins(userCoin, coinTransactionDto.getCoinCount(), transactionType);
                     });
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
