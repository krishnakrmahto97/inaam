package io.inaam.main.controller;

import io.inaam.main.dto.AttributeDto;
import io.inaam.main.dto.RealmDto;
import io.inaam.main.dto.StatusDto;
import io.inaam.main.service.RealmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RealmController
{
    private final RealmService realmService;

    @PostMapping("/realm")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRealm(@RequestBody RealmDto realmDto)
    {
        realmService.createRealm(realmDto);
    }

    @GetMapping("/realms")
    public List<RealmDto> listRealm()
    {
        return realmService.listRealm();
    }

    @GetMapping("/realm/{realmName}")
    public RealmDto getRealm(@PathVariable String realmName)
    {
        return realmService.getRealm(realmName);
    }

    @PostMapping("/realm/{realmName}/status")
    public void deactivateRealm(@PathVariable String realmName, @RequestBody StatusDto status)
    {
        realmService.updateStatus(realmName,status.getCurrent(),status.getUpdateTo());
    }

    @PostMapping("/realm/{realmName}/attribute")
    public void addAttribute(@PathVariable String realmName, @RequestBody AttributeDto attribute)
    {
        realmService.addAttribute(realmName,attribute);
    }

    @DeleteMapping("/realm/{realmName}/attribute")
    public void removeAttribute(@PathVariable String realmName,@RequestBody AttributeDto attribute)
    {
        realmService.removeAttribute(realmName,attribute);
    }
}