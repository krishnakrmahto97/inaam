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
import java.util.stream.Collectors;

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
        return userCoinRepository.findAllByUserId(userService.getUserId(realmService.getRealmId(realmName),
                                                                 userName))
                                 .stream()
                                 .map(userCoin -> {
                                     String coinName = coinRepository.findById(userCoin.getCoinId())
                                                                     .map(Coin::getName)
                                                                     .orElseThrow(() -> new CoinException(CoinException.COIN_TYPE_NOT_FOUND_MESSAGE));
                                     return new UserCoinDto(coinName, userCoin.getBalance());
                                 })
                                 .collect(Collectors.toList());
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

            userCoin.setBalance(userCoin.getBalance().add(userCoinDto.getCoinCount()));
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

                                  userCoin.setBalance(userCoin.getBalance().subtract(userCoinDto.getCoinCount()));
                                  updatedTransactionDtoList.add(new UserCoinDto(userCoinDto.getCoinName(),
                                                                                userCoin.getBalance()));

                                  return userCoin;
                              })
                              .orElseThrow(() -> new UserCoinException(UserCoinException.INSUFFICIENT_COINS_MESSAGE));
        });

        return updatedTransactionDtoList;
    }

    @Override
    @Transactional
    public UserAddAndRedeemCoinDto addAndRedeemCoins(String realmName, String userName, BigDecimal purchasePrice)
    {
        String realmId = realmService.getRealmId(realmName);
        String userId = userService.getUserId(userName, realmId);

        List<UserCoin> userCoins = userCoinRepository.findAllByUserIdAAndBalanceNotZeroOrderByCreationTime(userId);

        BigInteger totalCoinsRedeemed = BigInteger.ZERO;
        BigDecimal totalDiscountApplicable = BigDecimal.ZERO;
        List<UserCoinDto> userCoinDtoList = new ArrayList<>();
        for(UserCoin userCoin: userCoins)
        {
            String coinId = userCoin.getCoinId();
            Coin coinEntity = coinRepository.findById(coinId)
                                            .orElseThrow(() -> new CoinException(CoinException.COIN_TYPE_NOT_FOUND_MESSAGE));

            BigInteger coinEquivalentOfPurchasePrice = purchasePrice.divide(coinEntity.getMonetaryAmountToEarnOneCoin(), RoundingMode.FLOOR)
                                                                    .toBigInteger();

            UserCoinDto userCoinDto = userCoinTransformer.toUserCoinDto(coinEntity, userCoin);
            userCoinDtoList.add(userCoinDto);
            if (userCoin.getBalance().compareTo(coinEquivalentOfPurchasePrice) >= 0)
            {
                totalCoinsRedeemed = totalCoinsRedeemed.add(coinEquivalentOfPurchasePrice);
                totalDiscountApplicable = totalDiscountApplicable.add(purchasePrice);
                coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(userCoinDto,
                                                                                       coinEntity,
                                                                                       realmId,
                                                                                       userId,
                                                                                       CoinTransactionType.REDEEM));
                userCoin.setBalance(userCoin.getBalance().subtract(coinEquivalentOfPurchasePrice));
            }

            else
            {
                totalCoinsRedeemed = totalCoinsRedeemed.add(userCoin.getBalance());

                BigDecimal discountApplicableForRedeemedCoins =
                        new BigDecimal(userCoin.getBalance()).multiply(coinEntity.getMonetaryValuePerCoin());

                totalDiscountApplicable = totalDiscountApplicable.add(discountApplicableForRedeemedCoins);
                purchasePrice = purchasePrice.subtract(discountApplicableForRedeemedCoins);
                coinTransactionRepository.save(coinTransformer.toCoinTransactionEntity(userCoinDto,
                                                                                       coinEntity,
                                                                                       realmId,
                                                                                       userId,
                                                                                       CoinTransactionType.REDEEM));
                userCoin.setBalance(BigInteger.ZERO);
            }
        }

        return new UserAddAndRedeemCoinDto(userCoinDtoList, totalDiscountApplicable);
    }
}
