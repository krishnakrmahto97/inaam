package io.inaam.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController
{
    @PostMapping("/{{realm}}/client")
    private void createClient(@PathVariable String realm)
    {

    }

    @GetMapping("/{{realm}}/client")
    private void getClient(@PathVariable String realm)
    {

    }

    @GetMapping("/{{realm}}/client/{{client_name}}/secret")
    private void getClientSecret(@PathVariable String realm, @PathVariable String client_name)
    {

    }

}
