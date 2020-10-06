package io.inaam.main.service;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.repository.CoinRepository;
import io.inaam.main.transformer.CoinTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements CoinService
{
    private final CoinRepository coinRepository;
    private final CoinTransformer coinTransformer;
    private final RealmService realmService;

    @Override
    public void createCoin(CoinDto coinDto, String realmName)
    {
        Coin coinEntity = coinTransformer.toCoinEntity(coinDto, realmService.getRealmId(realmName));
        coinRepository.save(coinEntity);
    }

    @Override
    public List<CoinDto> getCoins(String realmName)
    {
        String realmId = realmService.getRealmId(realmName);
        return coinTransformer.toListOfCoinDto(coinRepository.findAllByRealmId(realmId));
    }
}