package io.inaam.main.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto
{
    private String name;
    private List<AttributeDto> attributes;
    private List<String> groups;
}
