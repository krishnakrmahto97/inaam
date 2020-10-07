package io.inaam.main.service;

import java.util.List;

public interface GroupService
{
    void createGroup(String realm, String group);

    List<String> getGroups(String realm);

    List<String> getGroupUsers(String realmName,String groupName);

    void addUser(String realm, String group, String user);

    void removeUser(String realm, String group, String user);
}
