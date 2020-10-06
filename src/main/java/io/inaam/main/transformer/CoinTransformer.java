package io.inaam.main.transformer;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.entity.Coin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoinTransformer
{
    Coin toCoinEntity(CoinDto coinDto, String realmId);

    List<CoinDto> toListOfCoinDto(List<Coin> coinEntity);
}