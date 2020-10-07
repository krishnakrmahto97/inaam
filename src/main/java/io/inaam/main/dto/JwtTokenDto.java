package io.inaam.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtTokenDto {
    private String accessToken;
//    private String refreshToken;
}
