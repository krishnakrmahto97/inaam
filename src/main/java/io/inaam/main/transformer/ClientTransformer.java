package io.inaam.main.transformer;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientTransformer {

    Client toClient(ClientDto clientDto);

    @Mapping(target = "realmId", source = "realmId")
    @Mapping(target = "secret", expression = "java(java.util.UUID.randomUUID().toString())")
    Client toClient(ClientDto clientDto, String realmId);

    ClientDto toClientDto(Client client);

}

