package io.inaam.main.controller;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import io.inaam.main.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController
{
    @Autowired
    ClientService clientService;
    @PostMapping("/{realm}/client")
    private String createClient(@PathVariable String realm, @RequestBody ClientDto clientDto)
    {
        return clientService.createClient(realm,clientDto);
    }

    @GetMapping("/{realm}/client")
    private List<Client> getClients(@PathVariable String realm)
    {
        return clientService.getClients(realm);
    }

    @GetMapping("/{realm}/client/{client_name}/secret")
    private String getClientSecret(@PathVariable String realm, @PathVariable String client_name)
    {
        return clientService.getClientSecret(realm, client_name);
    }

}
