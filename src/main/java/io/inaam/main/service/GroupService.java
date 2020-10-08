package io.inaam.main.service;

import io.inaam.main.dto.GroupDto;

import java.util.List;

public interface GroupService
{
    void createGroup(String realm, String group);

    List<GroupDto> getGroups(String realm);

    List<String> getGroupUsers(String realm,String group);

    void addUser(String realm, String group, String user);

    void removeUser(String realm, String group, String user);
}
