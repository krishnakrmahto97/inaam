package io.inaam.main.service;

import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserAddAndRedeemCoinDto;
import io.inaam.main.dto.UserCoinDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserCoinService
{
    List<UserCoinDto> getUserCoinDtoList(String realmName, String userName);

    List<CoinTransactionDto> getUserCoinTransactionDtoList(String realmName, String userName);

    List<UserCoinDto> createTransactionAndAddUserCoins(String realmName, String userName,
                                                       List<UserCoinDto> addCoinsDetails);

    List<UserCoinDto> createTransactionAndRedeemUserCoins(String realmName, String userName,
                                                          List<UserCoinDto> redeemCoinsDetails);

    UserAddAndRedeemCoinDto addAndRedeemCoins(String realmName, String userName, BigDecimal purchaseAmount);
}
