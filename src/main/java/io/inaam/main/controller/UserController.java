package io.inaam.main.controller;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.StatusDto;
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

    @GetMapping("{realm}/user/{userName}")
    public UserDto getUser(@PathVariable String realm, @PathVariable String userName)
    {
        return userService.getUser(userName, realm);
    }

    @PostMapping("{realm}/user/{userName}/attribute")
    public void addAttribute(@PathVariable String realm,
                             @PathVariable String userName,
                             @RequestBody AttributeDto attribute)
    {
        userService.addAttribute(realm, userName, attribute);
    }

    @DeleteMapping("{realm}/user/{userName}/attribute")
    public void deleteAttribute(@PathVariable String realm,
                                @PathVariable String userName,
                                @RequestBody AttributeDto attribute)
    {
        userService.removeAttribute(realm, userName, attribute);
    }


    @PostMapping("/{realm}/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@PathVariable String realm, @RequestBody UserDto userDto)
    {
        userService.createUser(realm, userDto);
    }

    @PostMapping("{realm}/user/{userName}/status")
    public void updateStatus(@PathVariable String realm,
                             @PathVariable String userName,
                             @RequestBody StatusDto status)
    {
        userService.updateStatus(realm, userName, status.getCurrent(), status.getUpdateTo());
    }

}
