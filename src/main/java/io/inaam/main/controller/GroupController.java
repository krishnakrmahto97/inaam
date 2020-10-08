package io.inaam.main.controller;

import io.inaam.main.dto.GroupDto;
import io.inaam.main.dto.NameDto;
import io.inaam.main.service.GroupService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/{realm}/groups")
public class GroupController
{
    private final GroupService groupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a Group")
    public void createGroup(@PathVariable String realm, @RequestBody NameDto groupDto)
    {
        groupService.createGroup(realm, groupDto.getName());
    }

    @GetMapping
    @ApiOperation("List all Group")
    public List<GroupDto> listGroups(@PathVariable String realm)
    {
        return groupService.getGroups(realm);
    }

    @PostMapping("/{groupName}/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Add user to Group")
    public void addUser(@PathVariable String realm, @PathVariable String groupName, @RequestBody NameDto nameDto)
    {
        groupService.addUser(realm, groupName, nameDto.getName());
    }

    @GetMapping("/{groupName}/users")
    @ApiOperation("Get usernames of group members")
    public List<String> getUsers(@PathVariable String realm, @PathVariable String groupName)
    {
        return groupService.getGroupUsers(realm, groupName);
    }

    @DeleteMapping("/{groupName}/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Remove user from group")
    public void removeUser(@PathVariable String realm,
                           @PathVariable String groupName,
                           @RequestBody NameDto nameDto)
    {
        groupService.removeUser(realm, groupName, nameDto.getName());
    }

}
