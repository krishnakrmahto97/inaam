package io.inaam.main.controller;

import io.inaam.main.dto.ClientDto;
import io.inaam.main.entity.Client;
import io.inaam.main.service.ClientService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/{realm}/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ApiOperation(value = "Create a new client under the realm")
    public String createClient(@PathVariable String realm, @RequestBody ClientDto clientDto) {
        return clientService.createClient(realm, clientDto);
    }

    @GetMapping
    @ApiOperation(value = "Get list of all clients in a realm")
    public List<Client> getClients(@PathVariable String realm) {
        return clientService.getClients(realm);
    }

    @GetMapping("/{clientName}")
    @ApiOperation(value = "Get detail of a client in a realm")
    public Client getClient(@PathVariable String realm, @PathVariable String clientName) {
        return clientService.getClient(realm, clientName);
    }

    @GetMapping("/{clientName}/secret")
    @ApiOperation(value = "Get secret for a client in a realm")
    public String getClientSecret(@PathVariable String realm, @PathVariable String clientName) {
        return clientService.getClientSecret(realm, clientName);
    }
}
