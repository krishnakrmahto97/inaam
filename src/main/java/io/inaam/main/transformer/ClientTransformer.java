package io.inaam.main.transformer;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientTransformer {

    Client toClient(ClientDto clientDto);

    @Mapping(target = "realmId", source = "realmId")
    @Mapping(target = "secret", defaultValue = "secret")
    Client toClient(ClientDto clientDto, String realmId, String secret);

    ClientDto toClientDto(Client client);

}

