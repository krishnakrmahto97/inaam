package io.inaam.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @GetMapping("{{realm}}/user")
    private void getUsers(@PathVariable String realm)
    {

    }

    @PostMapping("/{{realm}}/user")
    private void createUser(@PathVariable String realm)
    {

    }
}
