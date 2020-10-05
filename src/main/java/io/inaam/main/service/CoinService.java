package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;

import java.util.List;

public interface CoinService
{
    void createCoin(CoinDto coinDto, String realmName);
    List<CoinDto> getCoins(String realmName);
}