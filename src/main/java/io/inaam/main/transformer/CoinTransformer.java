package io.inaam.main.transformer;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.UserCoinDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.entity.CoinTransaction;
import io.inaam.main.util.CoinTransactionType;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CoinTransformer
{
    Coin toCoinEntity(CoinDto coinDto, String realmId);

    List<CoinDto> toListOfCoinDto(List<Coin> coinEntity);

    CoinTransaction toCoinTransactionEntity(UserCoinDto userCoinDto,
                                            String realmName,
                                            String userName,
                                            String coinId,
                                            CoinTransactionType type);

    UserCoinDto toUserCoinDto(String coinName, int coinCount, BigDecimal conversionRate);
}
