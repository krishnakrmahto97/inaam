package io.inaam.main.service;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import io.inaam.main.repository.ClientRepository;
import io.inaam.main.repository.RealmRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RealmRepository realmRepository;

    public String createClient(String realm, ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getClient_name());
        client.setSecret(UUID.randomUUID().toString());
        client.setRealmId(realmRepository.findByName(realm).getId());
        return clientRepository.save(client).getSecret();
    }

    public List<Client> getClients(String realm){
        return clientRepository.findByRealmIdEquals("dddd");
    }

    public String getClientSecret(String realm, String client_name){
        return clientRepository.findByNameEqualsAndRealmIdEquals(client_name,realmRepository.findByName(realm).getId()).get(0).getSecret();
    }
}
