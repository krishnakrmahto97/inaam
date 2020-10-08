package io.inaam.main.controller;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.StatusDto;
import io.inaam.main.dto.UserDto;
import io.inaam.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{realm}/users")
@AllArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping
    @ApiOperation(value = "Get list of all users in a realm")
    public List<UserDto> getUsers(@PathVariable String realm)
    {
        return userService.getUsers(realm);
    }

    @GetMapping("/{userName}")
    @ApiOperation(value = "Get details of a user")
    public UserDto getUser(@PathVariable String realm, @PathVariable String userName)
    {
        return userService.getUser(userName, realm);
    }

    @PostMapping("/{userName}/attribute")
    @ApiOperation("Add Attributes for Account")
    public void addAttribute(@PathVariable String realm,
                             @PathVariable String userName,
                             @RequestBody AttributeDto attribute)
    {
        userService.addAttribute(realm, userName, attribute);
    }

    @DeleteMapping("/{userName}/attribute")
    @ApiOperation("Delete Attributes for Account")
    public void deleteAttribute(@PathVariable String realm,
                                @PathVariable String userName,
                                @RequestBody AttributeDto attribute)
    {
        userService.removeAttribute(realm, userName, attribute);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Add an user Account")
    public void createUser(@PathVariable String realm, @RequestBody UserDto userDto)
    {
        userService.createUser(realm, userDto);
    }

    @PostMapping("/{userName}/status")
    @ApiOperation("Activate or Deactivate a user Account")
    public void updateStatus(@PathVariable String realm,
                             @PathVariable String userName,
                             @RequestBody StatusDto status)
    {
        userService.updateStatus(realm, userName, status.getCurrent(), status.getUpdateTo());
    }

}
