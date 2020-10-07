package io.inaam.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCoinDto
{
    private String coinName;
    private int coinCount;
    private BigDecimal conversionRate;
}
