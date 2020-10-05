package io.inaam.main.service;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;

import java.util.List;

public interface ClientService {

    String createClient(String realm, ClientDto clientDto);

    String getClientSecret(String realm, String client_name);

    List<Client> getClients(String realm);
}
