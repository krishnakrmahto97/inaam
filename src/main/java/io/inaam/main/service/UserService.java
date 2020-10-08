package io.inaam.main.service;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.Status;

import java.util.List;

public interface UserService
{
    String getUserId(String name, String realmId);

    UserDto getUser(String name, String realmName);

    List<UserDto> getUsers(String realmName);

    void createUser(String realm, UserDto userDto);

    List<String> getUsernames(List<String> userIds);

    void addAttribute(String realm, String userName, AttributeDto attribute);

    void removeAttribute(String realm, String userName, AttributeDto attribute);

    void updateStatus(String realm, String userName, Status current, Status updateTo);
}
