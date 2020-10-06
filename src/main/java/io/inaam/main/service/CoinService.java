package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.UserCoinDto;

import java.util.List;

public interface CoinService
{
    void createCoin(CoinDto coinDto, String realmName);

    List<CoinDto> getCoins(String realmName);

    List<UserCoinDto> createTransactionAndAddUserCoins(String realmName, String userName,
                                                       List<UserCoinDto> addCoinsDetails);

    List<UserCoinDto> createTransactionAndRedeemUserCoins(String realmName, String userName,
                                                          List<UserCoinDto> redeemCoinsDetails);
}
