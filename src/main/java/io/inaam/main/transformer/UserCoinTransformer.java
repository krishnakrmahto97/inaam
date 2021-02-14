package io.inaam.main.transformer;

import io.inaam.main.dto.UserCoinDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.entity.UserCoin;
import org.mapstruct.Mapping;

public interface UserCoinTransformer
{
    @Mapping(source = "coinEntity.name", target = "coinName")
    @Mapping(source = "userCoinEntity.balance", target = "coinCount")
    UserCoinDto toUserCoinDto(Coin coinEntity, UserCoin userCoinEntity);
}
