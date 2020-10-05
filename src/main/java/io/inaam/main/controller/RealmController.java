package io.inaam.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealmController
{
    @PostMapping("/realm")
    private void createRealm()
    {
// Response: 201 CREATED
    }

    @GetMapping("/realm")
    private void listRealm()
    {
// Response: 201 CREATED
    }
}
