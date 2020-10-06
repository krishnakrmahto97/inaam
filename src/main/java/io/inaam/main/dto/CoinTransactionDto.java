package io.inaam.main.dto;

import lombok.Data;

@Data
public class CoinTransactionDto
{
    private final String coinName;
    private final int coinCount;
}
