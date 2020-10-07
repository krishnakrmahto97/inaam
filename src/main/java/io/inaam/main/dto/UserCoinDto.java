package io.inaam.main.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserCoinDto
{
    private String coinName;
    private int coinCount;
    private BigDecimal conversionRate;
}
