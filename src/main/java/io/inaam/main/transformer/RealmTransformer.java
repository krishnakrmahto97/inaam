package io.inaam.main.transformer;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.RealmDto;
import io.inaam.main.entity.Realm;
import io.inaam.main.entity.RealmAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RealmTransformer
{
    @Mapping(target = "attributes", ignore = true)
    Realm toRealm(RealmDto realmDto);

    RealmDto toRealmDto(Realm realmDto);

    RealmAttribute toRealmAttribute(AttributeDto attributeDto, String realmId);

    AttributeDto toAttributeDto(RealmAttribute attributeDto);

    List<AttributeDto> toAttributeDtoList(List<RealmAttribute> attributes);

    List<RealmDto> toRealmDto(List<Realm> realmDto);

}
