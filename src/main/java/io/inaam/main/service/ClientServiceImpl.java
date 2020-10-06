package io.inaam.main.service;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import io.inaam.main.repository.ClientRepository;
import io.inaam.main.transformer.ClientTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private ClientRepository clientRepository;

    private RealmService realmService ;

    private ClientTransformer clientTransformer;

    public String createClient(String realm, ClientDto clientDto) {
        clientDto.setSecret(UUID.randomUUID().toString());
        clientDto.setRealmId(realmService.getRealm(realm).getId());
        Client client = clientTransformer.toClient(clientDto);
        return clientRepository.save(client).getSecret();
    }

    public List<Client> getClients(String realm) {
        return clientRepository.findByRealmId(realmService.getRealm(realm).getId());
    }

    public Client getClient(String realm, String clientName){
        return clientRepository.findByNameAndRealmId(clientName, realmService.getRealm(realm).getId());
    }

    public String getClientSecret(String realm, String client_name){
        return clientRepository.findByNameAndRealmId(client_name,realmService.getRealm(realm).getId()).getSecret();
    }
}
