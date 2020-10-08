package io.inaam.main.controller;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.RealmDto;
import io.inaam.main.dto.StatusDto;
import io.inaam.main.service.RealmService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/realms")
@AllArgsConstructor
public class RealmController
{
    private final RealmService realmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create a new Realm")
    public void createRealm(@RequestBody RealmDto realmDto)
    {
        realmService.createRealm(realmDto);
    }

    @GetMapping
    @ApiOperation("List All Realms")
    public List<RealmDto> listRealm()
    {
        return realmService.listRealm();
    }

    @GetMapping("/{realmName}")
    @ApiOperation("Get Realm Details")
    public RealmDto getRealm(@PathVariable String realmName)
    {
        return realmService.getRealm(realmName);
    }

    @PostMapping("/{realmName}/status")
    @ApiOperation("Activate or Deactivate a Realm")
    public void deactivateRealm(@PathVariable String realmName, @RequestBody StatusDto status)
    {
        realmService.updateStatus(realmName,status.getCurrent(),status.getUpdateTo());
    }

    @PostMapping("/{realmName}/attribute")
    @ApiOperation("Add attributes to a Realm")
    public void addAttribute(@PathVariable String realmName, @RequestBody AttributeDto attribute)
    {
        realmService.addAttribute(realmName,attribute);
    }

    @DeleteMapping("/{realmName}/attribute")
    @ApiOperation("Removing attributes from a Realm")
    public void removeAttribute(@PathVariable String realmName,@RequestBody AttributeDto attribute)
    {
        realmService.removeAttribute(realmName,attribute);
    }
}