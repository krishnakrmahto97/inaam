package io.inaam.main.controller;

import io.inaam.main.dto.GroupDto;
import io.inaam.main.dto.NameDto;
import io.inaam.main.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GroupController
{
    private final GroupService groupService;

    @PostMapping("/{realm}/group")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGroup(@PathVariable String realm, @RequestBody NameDto groupDto)
    {
        groupService.createGroup(realm, groupDto.getName());
    }

    @GetMapping("/{realm}/group")
    public List<GroupDto> listGroups(@PathVariable String realm)
    {
        return groupService.getGroups(realm);
    }

    @PostMapping("/{realm}/group/{groupName}/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addUser(@PathVariable String realm, @PathVariable String groupName, @RequestBody NameDto nameDto)
    {
        groupService.addUser(realm, groupName, nameDto.getName());
    }

    @GetMapping("/{realm}/group/{groupName}")
    public List<String> getUsers(@PathVariable String realm, @PathVariable String groupName)
    {
        return groupService.getGroupUsers(realm, groupName);
    }


    @DeleteMapping("/{realm}/group/{groupName}/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeUser(@PathVariable String realm,
                           @PathVariable String groupName,
                           @RequestBody NameDto nameDto)
    {
        groupService.removeUser(realm, groupName, nameDto.getName());
    }


}
