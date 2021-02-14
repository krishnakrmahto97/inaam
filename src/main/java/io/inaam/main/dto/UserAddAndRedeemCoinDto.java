package io.inaam.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddAndRedeemCoinDto
{
    List<UserCoinDto> userCoinDtoList;
    private BigDecimal discountApplicable;
}
