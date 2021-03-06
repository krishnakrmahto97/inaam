package io.inaam.main.transformer;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserCoinDto;
import io.inaam.main.entity.Coin;
import io.inaam.main.entity.CoinTransaction;
import io.inaam.main.util.CoinTransactionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoinTransformer
{
    Coin toCoinEntity(CoinDto coinDto, String realmId);

    CoinDto toCoinDto(Coin coinEntity);

    List<CoinDto> toListOfCoinDto(List<Coin> coinEntity);

    @Mapping(source = "coinEntity.id", target = "coinId")
    @Mapping(source = "userCoinDto.coinCount", target = "coinsTransacted")
    CoinTransaction toCoinTransactionEntity(UserCoinDto userCoinDto,
                                            Coin coinEntity,
                                            String realmId,
                                            String userId,
                                            CoinTransactionType type);

    List<CoinTransactionDto> toCoinTransactionDtoList (List<CoinTransaction> coinTransactions);
}
