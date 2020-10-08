package io.inaam.main.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoinDto
{
    private String name;
    private BigDecimal conversionRate;
}