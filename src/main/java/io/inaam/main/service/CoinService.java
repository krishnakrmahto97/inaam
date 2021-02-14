package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserCoinDto;

import java.util.List;

public interface CoinService
{
    void createCoin(CoinDto coinDto, String realmName);

    List<CoinDto> getCoins(String realmName);

    CoinDto updateCoin(String realmName, String coinName, CoinDto coin);
}
