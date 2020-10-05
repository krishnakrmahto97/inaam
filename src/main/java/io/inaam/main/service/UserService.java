package io.inaam.main.service;

import io.inaam.main.dto.UserDto;

import java.util.List;

public interface UserService
{
    List<UserDto> getUsers(String realmName);
    UserDto createUser(UserDto userDto);
}
