package io.inaam.main.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AdminDto {
    @NonNull
    private String name;
    @NonNull
    private String secret;
}
