package io.inaam.main.transformer;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.RealmDto;
import io.inaam.main.entity.Realm;
import io.inaam.main.entity.RealmAttribute;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RealmTransformer
{
    Realm toRealm(RealmDto realmDto);

    RealmDto toRealmDto(Realm realmDto);

    RealmAttribute toRealmAttribute(AttributeDto attributeDto);

    AttributeDto toAttributeDto(RealmAttribute attributeDto);

    RealmAttribute toRealmAttribute(AttributeDto attributeDto,String realmId);

    List<RealmAttribute> toRealmAttributeList(List<AttributeDto> attributes);

    List<AttributeDto> toAttributeDtoList(List<RealmAttribute> attributes);
}
