package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;

import java.util.List;

public interface CoinService
{
    void createCoin(CoinDto coinDto, String realmName);

    List<CoinDto> getCoins(String realmName);

    void createTransactionAndAddUserCoins(String realmName, String userName,
                                          List<CoinTransactionDto> addCoinsDetails);

    void createTransactionAndRedeemUserCoins(String realmName, String userName,
                                             List<CoinTransactionDto> redeemCoinsDetails);
}
