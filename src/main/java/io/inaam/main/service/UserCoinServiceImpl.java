package io.inaam.main.service;

import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserAddAndRedeemCoinDto;
import io.inaam.main.dto.UserCoinDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.entity.UserCoin;
import io.inaam.main.entity.UserCoinPK;
import io.inaam.main.exception.CoinException;
import io.inaam.main.exception.UserCoinException;
import io.inaam.main.repository.CoinRepository;
import io.inaam.main.repository.CoinTransactionRepository;
import io.inaam.main.repository.UserCoinRepository;
import io.inaam.main.transformer.CoinTransformer;
import io.inaam.main.transformer.UserCoinTransformer;
import io.inaam.main.util.CoinTransactionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserCoinServiceImpl implements UserCoinService
{
    private final RealmService realmService;
    private final CoinRepository coinRepository;
    private final UserService userService;
    private final CoinTransactionRepository coinTransactionRepository;
    private final UserCoinRepository userCoinRepository;
    private final CoinTransformer coinTransformer;
    private final UserCoinTransformer userCoinTransformer;

    @Override
    public List<UserCoinDto> getUserCoinDtoList(String realmName, String userName)
    {
        return null;
    }

    @Override
    public List<CoinTransactionDto> getUserCoinTransactionDtoList(String realmName, String userName)
    {
        String realmId = realmService.getRealmId(realmName);
        String userId = userService.getUserId(userName, realmId);

        return coinTransformer.toCoinTransactionDtoList(coinTransactionRepository.findAllByRealmIdAndUserId(realmId,
                                                                                                            userId));
    }

    @Override
    @Transactional
    public List<UserCoinDto> createTransactionAndAddUserCoins(String realmName,
                                                              String userName,
                                                              List<UserCoinDto> userCoinDtoList)
    {
        String realmId = realmService.getRealmId(realmName);
        String userId = userService.getUserId(userName, realmId);

        List<UserCoinDto> updatedTransactionDtoList = new ArrayList<>();

        userCoinDtoList.forEach(userCoinDto -> {
            Coin coinEntity = coinRepository.findByRealmIdAndName(realmId, userCoinDto.getCoinName())
                                            .orElseThrow(() -> new CoinException(CoinException.COIN_TYPE_NOT_FOUND_MESSAGE));

            coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(userCoinDto,
                                                                                   coinEntity,
                                                                                   realmId,
                                                                                   userId,
                                                                                   CoinTransactionType.ADD));

            UserCoin userCoin = userCoinRepository.findById(new UserCoinPK(userId, coinEntity.getId()))
                                                  .orElseGet(() -> new UserCoin(userId, coinEntity.getId()));

            updateUserCoins(userCoin, userCoinDto.getCoinCount(), CoinTransactionType.ADD);
            updatedTransactionDtoList.add(new UserCoinDto(userCoinDto.getCoinName(), userCoin.getBalance()));
        });
        return updatedTransactionDtoList;
    }

    @Override
    @Transactional
    public List<UserCoinDto> createTransactionAndRedeemUserCoins(String realmName,
                                                                 String userName,
                                                                 List<UserCoinDto> userCoinDtoList)
    {
        String realmId = realmService.getRealmId(realmName);
        String userId = userService.getUserId(userName, realmId);

        List<UserCoinDto> updatedTransactionDtoList = new ArrayList<>();

        userCoinDtoList.forEach(userCoinDto -> {
            Coin coinEntity = coinRepository.findByRealmIdAndName(realmId, userCoinDto.getCoinName())
                                            .orElseThrow(() -> new CoinException(CoinException.COIN_TYPE_NOT_FOUND_MESSAGE));

            userCoinRepository.findById(new UserCoinPK(userId, coinEntity.getId()))
                              .filter(userCoin -> userCoin.getBalance().compareTo(userCoinDto.getCoinCount()) >= 0)
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
                                                                           .build());

                                  return userCoin;
                              })
                              .orElseThrow(() -> new UserCoinException(UserCoinException.INSUFFICIENT_COINS_MESSAGE));
        });

        return updatedTransactionDtoList;
    }

    private void updateUserCoins(UserCoin userCoin, BigInteger coinCountInTransaction, CoinTransactionType transactionType)
    {
        BigInteger updatedCoinBalance = CoinTransactionType.ADD.equals(transactionType)
                                 ? userCoin.getBalance().add(coinCountInTransaction)
                                 : userCoin.getBalance().subtract(coinCountInTransaction);
        userCoin.setBalance(updatedCoinBalance);
        userCoinRepository.save(userCoin);
    }

    @Override
    @Transactional
    public UserAddAndRedeemCoinDto addAndRedeemCoins(String realmName, String userName, BigDecimal purchasePrice)
    {
        String realmId = realmService.getRealmId(realmName);
        String userId = userService.getUserId(userName, realmId);

        List<UserCoin> userCoins = userCoinRepository.findAllByUserId(userId);

        BigInteger coinsRedeemed = BigInteger.ZERO;
        BigDecimal discountApplicable = BigDecimal.ZERO;
        List<UserCoinDto> userCoinDtoList = new ArrayList<>();
        for(UserCoin userCoin: userCoins)
        {
            String coinId = userCoin.getCoinId();
            Coin coinEntity = coinRepository.findById(coinId)
                                            .orElseThrow(() -> new UserCoinException(UserCoinException.COIN_TYPE_NOT_FOUND_MESSAGE));

            BigInteger coinEquivalentOfPurchasePrice = purchasePrice.divide(coinEntity.getMonetaryAmountToEarnOneCoin(), RoundingMode.FLOOR)
                                                                    .toBigInteger();

            userCoinDtoList.add(userCoinTransformer.toUserCoinDto(coinEntity, userCoin));
            if (userCoin.getBalance().compareTo(coinEquivalentOfPurchasePrice) >= 0)
            {
                coinsRedeemed = coinsRedeemed.add(coinEquivalentOfPurchasePrice);
                discountApplicable = discountApplicable.add(purchasePrice);
                userCoin.setBalance(userCoin.getBalance().subtract(coinEquivalentOfPurchasePrice));
            }

            else
            {
                coinsRedeemed = coinsRedeemed.add(userCoin.getBalance());
                discountApplicable = discountApplicable.add(new BigDecimal(userCoin.getBalance()).multiply(coinEntity.getMonetaryValuePerCoin()));
                purchasePrice = purchasePrice.subtract(discountApplicable);
                userCoin.setBalance(BigInteger.ZERO);
            }
        }

        return new UserAddAndRedeemCoinDto(userCoinDtoList, discountApplicable);
    }
}
