package io.inaam.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RealmDto
{
    private String name;
    private String currencySymbol;
    private List<AttributeDto> attributes;
}