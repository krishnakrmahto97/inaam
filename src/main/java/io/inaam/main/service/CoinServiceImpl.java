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

    @Override
    @Transactional
    public CoinDto updateCoin(String realmName, String coinName, CoinDto coinDto)
    {
        Coin coinEntity = coinRepository.findByRealmIdAndName(realmService.getRealmId(realmName), coinName)
                                        .orElseThrow(() -> new CoinException(CoinException.COIN_TYPE_NOT_FOUND));
        coinEntity.setName(coinDto.getName());
        coinEntity.setMonetaryValuePerCoin(coinDto.getConversionRate());
        return coinTransformer.toCoinDto(coinRepository.save(coinEntity));
    }
}
