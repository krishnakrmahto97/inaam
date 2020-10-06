package io.inaam.main.transformer;

import io.inaam.main.entity.GroupDetails;
import io.inaam.main.entity.UserGroup;
import io.inaam.main.entity.UserGroupPK;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupTransformer
{
    GroupDetails toGroupDetails(String name, String realmId);

    default String toGroupName(GroupDetails groupDetails)
    {
        return groupDetails.getName();
    }

    List<String> toGroupNames(List<GroupDetails> groupDetails);

    UserGroup toUserGroup(String userId, String groupId);

    UserGroupPK toUserGroupPK(String userId,String groupId);
}
