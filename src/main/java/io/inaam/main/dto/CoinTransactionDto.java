package io.inaam.main.dto;

import io.inaam.main.util.CoinTransactionType;
import lombok.Data;

@Data
public class CoinTransactionDto
{
    private String id;
    private CoinTransactionType type;
    private int coinsTransacted;
}
