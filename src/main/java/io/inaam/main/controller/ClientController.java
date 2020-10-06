package io.inaam.main.controller;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import io.inaam.main.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/{realm}/client")
    public String createClient(@PathVariable String realm, @RequestBody ClientDto clientDto) {
        return clientService.createClient(realm, clientDto);
    }

    @GetMapping("/{realm}/client")
    public List<Client> getClients(@PathVariable String realm) {
        return clientService.getClients(realm);
    }

    @GetMapping("/{realm}/client/{clientName}")
    public Client getClients(@PathVariable String realm, @PathVariable String clientName){
        return clientService.getClient(realm, clientName);
    }

    @GetMapping("/{realm}/client/{clientName}/secret")
    public String getClientSecret(@PathVariable String realm, @PathVariable String clientName) {
        return clientService.getClientSecret(realm, clientName);
    }
}
