package io.inaam.main.service;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientService {

    String createClient(String realm, ClientDto clientDto);

    String getClientSecret(String realm, String clientName);

    List<Client> getClients(String realm);

    Client getClient(String realm, String clientName);

}
