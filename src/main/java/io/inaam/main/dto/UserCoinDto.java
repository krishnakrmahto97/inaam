package io.inaam.main.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCoinDto
{
    private String coinName;
    private int coinCount;
    private BigDecimal conversionRate;
}
