package io.inaam.main.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController
{
    @PostMapping("/{{realm}}/group")
    private void createGroup(@PathVariable String realm)
    {

    }
    @GetMapping("/{{realm}}/group")
    private void listGroup(@PathVariable String realm)
    {

    }

    @PostMapping("/{{realm}}/group/{{group_name}}/")
    private void addUser(@PathVariable String realm, @PathVariable String group_name)
    {

    }

    @DeleteMapping("/{{realm}}/group/{{group_name}}/")
    private void removeUser(@PathVariable String realm, @PathVariable String group_name)
    {

    }

}
