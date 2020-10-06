package io.inaam.main.controller;

import io.inaam.main.dto.UserDto;
import io.inaam.main.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping("{realm}/user")
    public List<UserDto> getUsers(@PathVariable String realm)
    {
        return userService.getUsers(realm);
    }

    @PostMapping("/{realm}/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@PathVariable String realm, @RequestBody UserDto userDto)
    {
        userService.createUser(realm, userDto);
    }
}
