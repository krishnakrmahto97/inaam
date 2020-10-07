package io.inaam.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class TokenDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;
}
