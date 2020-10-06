package io.inaam.main.transformer;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientTransformer {

    Client toClient(ClientDto clientDto);

    ClientDto toClientDto(Client client);

}

