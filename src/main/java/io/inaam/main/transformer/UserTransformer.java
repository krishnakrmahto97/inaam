package io.inaam.main.transformer;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.GroupDetails;
import io.inaam.main.entity.UserAttribute;
import io.inaam.main.entity.UserDetails;
import io.inaam.main.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTransformer
{
    UserDto toUserDto(UserDetails user);

    List<UserDto> toUserDto(List<UserDetails> user);

    @Mapping(target = "attributes", ignore = true)
    UserDetails toUser(UserDto userDto, String realmId);

    UserAttribute toUserAttribute(AttributeDto attributeDto, String userId);

    AttributeDto toAttributeDto(UserAttribute userAttribute);

    default String toGroupName(UserGroup userGroup)
    {
        return userGroup.getGroupDetails().getName();
    }

    default UserGroup toUserGroup(String userGroup)
    {
        return UserGroup.builder()
                        .groupDetails(GroupDetails.builder().name(userGroup).build())
                        .build();
    }

    default String toUsername(UserDetails userDetails)
    {
        return userDetails.getName();
    }

    List<String> toUserNames(List<UserDetails> userDetails);

    List<String> toGroupName(List<UserGroup> userGroups);
}
