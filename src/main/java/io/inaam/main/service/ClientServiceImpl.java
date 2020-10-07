package io.inaam.main.service;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import io.inaam.main.repository.ClientRepository;
import io.inaam.main.transformer.ClientTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final RealmService realmService;

    private final ClientTransformer clientTransformer;

    public String createClient(String realm, ClientDto clientDto) {
        String realmId = realmService.getRealmId(realm);
        Client client = clientTransformer.toClient(clientDto, realmId );
        return clientRepository.save(client).getSecret();
    }

    public List<Client> getClients(String realm) {
        return clientRepository.findByRealmId(realmService.getRealmId(realm));
    }

    public Client getClient(String realm, String clientName) {
        return clientRepository.findByNameAndRealmId(clientName, realmService.getRealmId(realm));
    }

    public String getClientSecret(String realm, String clientName) {
        return clientRepository.findByNameAndRealmId(clientName, realmService.getRealmId(realm)).getSecret();
    }
}
