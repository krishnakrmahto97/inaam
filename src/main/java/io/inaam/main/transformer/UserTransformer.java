package io.inaam.main.transformer;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.UserDto;
import io.inaam.main.entity.UserAttribute;
import io.inaam.main.entity.UserDetails;
import io.inaam.main.entity.UserGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserTransformer
{

    UserDto toUserDto(UserDetails user);

    UserDetails toUser(UserDto userDto);

    UserAttribute toUserAttribute(AttributeDto attributeDto);

    AttributeDto toAttributeDto(UserAttribute userAttribute);

    default String toGroupName(UserGroup userGroup)
    {
        return userGroup.getGroupDetails().getName();
    }
//
//    List<String> toGroupName(List<UserGroup> userGroups);
}
