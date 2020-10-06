package io.inaam.main.service;

import io.inaam.main.entity.GroupDetails;
import io.inaam.main.entity.UserGroup;
import io.inaam.main.entity.UserGroupPK;
import io.inaam.main.repository.GroupRepository;
import io.inaam.main.repository.UserGroupRepository;
import io.inaam.main.transformer.GroupTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService
{
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserService userService;
    private final RealmService realmService;
    private final GroupTransformer groupTransformer;

    @Override
    public void createGroup(String realm, String group)
    {
        GroupDetails groupDetails = groupTransformer.toGroupDetails(group, realmService.getRealmId(realm));
        groupRepository.save(groupDetails);
    }

    @Override
    public List<String> getGroups(String realm)
    {
        List<GroupDetails> groups = groupRepository.findByRealmId(realmService.getRealmId(realm));
        return groupTransformer.toGroupNames(groups);
    }

    @Override
    public List<String> getGroupUsers(String realm, String group)
    {
        String realmId = realmService.getRealmId(realm);
        String groupId = groupRepository.findByNameAndRealmId(group, realmId).getId();

        List<String> userIds = userGroupRepository.findAllByGroupId(groupId)
                                                  .stream()
                                                  .map(UserGroup::getUserId)
                                                  .collect(Collectors.toList());

        return userService.getUsernames(userIds);
    }

    @Override
    public void addUser(String realm, String group, String user)
    {
        String realmId = realmService.getRealmId(realm);
        String groupId = groupRepository.findByNameAndRealmId(group, realmId).getId();
        String userId = userService.getUser(user, realmId).getId();

        UserGroup userGroup = groupTransformer.toUserGroup(userId, groupId);
        userGroupRepository.save(userGroup);
    }

    @Override
    public void removeUser(String realm, String group, String user)
    {
        String realmId = realmService.getRealmId(realm);
        String groupId = groupRepository.findByNameAndRealmId(group, realmId).getId();
        String userId = userService.getUser(user, realmId).getId();

        UserGroupPK userGroupPK = groupTransformer.toUserGroupPK(userId, groupId);
        userGroupRepository.deleteById(userGroupPK);
    }
}
