package io.inaam.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoinDto
{
    private String name;

    @JsonProperty("conversion_rate")
    private BigDecimal conversionRate;
}