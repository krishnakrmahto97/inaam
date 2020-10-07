package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserCoinDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserCoinDto> createTransactionAndAddUserCoins(String realmName, String userName, List<UserCoinDto> userCoinDtoList)
    {
        String realmId = realmService.getRealm(realmName).getId();
        String userId = userService.getUserByNameAndRealmId(userName, realmId).getId();

        List<UserCoinDto> updatedTransactionDtoList = new ArrayList<>();

        userCoinDtoList.forEach(userCoinDto -> {
            Coin coinEntity = coinRepository.findByRealmIdAndName(realmId, userCoinDto.getCoinName());

            coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(userCoinDto,
                                                                                   coinEntity,
                                                                                   realmId,
                                                                                   userId,
                                                                                   CoinTransactionType.ADD));

            UserCoin userCoin = userCoinRepository.findById(new UserCoinPK(userId, coinEntity.getId()))
                                                  .orElseGet(() -> new UserCoin(userId, coinEntity.getId(), 0));

            updateUserCoins(userCoin, userCoinDto.getCoinCount(), CoinTransactionType.ADD);
            updatedTransactionDtoList.add(UserCoinDto.builder()
                                                     .coinName(userCoinDto.getCoinName())
                                                     .coinCount(userCoin.getBalance())
                                                     .conversionRate(coinEntity.getConversionRate())
                                                     .build());
        });
        return updatedTransactionDtoList;
    }

    @Override
    @Transactional
    public List<UserCoinDto> createTransactionAndRedeemUserCoins(String realmName, String userName, List<UserCoinDto> userCoinDtoList)
    {
        String realmId = realmService.getRealm(realmName).getId();
        String userId = userService.getUserByNameAndRealmId(userName, realmId).getId();

        List<UserCoinDto> updatedTransactionDtoList = new ArrayList<>();

        userCoinDtoList.forEach(userCoinDto -> {
            Coin coinEntity = coinRepository.findByRealmIdAndName(realmId, userCoinDto.getCoinName());

            userCoinRepository.findById(new UserCoinPK(userId, coinEntity.getId()))
                              .filter(userCoin -> userCoin.getBalance() > userCoinDto.getCoinCount())
                              .map(userCoin -> {
                                  coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(userCoinDto,
                                                                                                         coinEntity,
                                                                                                         realmId,
                                                                                                         userId,
                                                                                                         CoinTransactionType.REDEEM));

                                  updateUserCoins(userCoin, userCoinDto.getCoinCount(), CoinTransactionType.REDEEM);
                                  updatedTransactionDtoList.add(UserCoinDto.builder()
                                                                           .coinName(userCoinDto.getCoinName())
                                                                           .coinCount(userCoin.getBalance())
                                                                           .conversionRate(coinEntity.getConversionRate())
                                                                           .build());

                                  return userCoin;
                              })
                              .orElseThrow(() -> new CoinException("Not enough coins!"));
        });

        return updatedTransactionDtoList;
    }

    @Override
    public List<UserCoinDto> getUserCoinDtoList(String realmName, String userName)
    {
        String realmId = realmService.getRealm(realmName).getId();
        String userId = userService.getUserByNameAndRealmId(userName, realmId).getId();

        return userCoinRepository.findAllByUserId(userId).stream()
                                 .map(userCoinEntity -> coinRepository.findById(userCoinEntity.getCoinId())
                                                                       .map(coinEntity -> coinTransformer.toUserCoinDto(coinEntity, userCoinEntity))
                                                                       .orElseThrow(() -> new CoinException("Coin not created by realm!")))
                                 .collect(Collectors.toList());
    }

    private void updateUserCoins(UserCoin userCoin, int coinCountInTransaction, CoinTransactionType transactionType)
    {
        int updatedCoinBalance = CoinTransactionType.ADD.equals(transactionType) ? userCoin.getBalance() + coinCountInTransaction :
                                 userCoin.getBalance() - coinCountInTransaction;
        userCoin.setBalance(updatedCoinBalance);
        userCoinRepository.save(userCoin);
    }

    @Override
    public List<CoinTransactionDto> getUserCoinTransactionDtoList(String realmName, String userName)
    {
        String realmId = realmService.getRealm(realmName).getId();
        String userId = userService.getUserByNameAndRealmId(userName, realmId).getId();

        return coinTransformer.toCoinTransactionDtoList(coinTransactionRepository.findAllByRealmIdAndUserId(realmId,
                                                                                                            userId));
    }
}
