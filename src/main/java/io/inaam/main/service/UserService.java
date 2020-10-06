package io.inaam.main.service;

import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.UserDetails;

import java.util.List;

public interface UserService
{
    UserDetails getUser(String name, String realmId);

    List<UserDto> getUsers(String realmName);

    void createUser(String realm, UserDto userDto);

    List<String> getUsernames(List<String> userIds);
}
