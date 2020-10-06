package io.inaam.main.controller;

import io.inaam.main.dto.RealmDto;
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

    @GetMapping("/realm")
    public List<RealmDto> listRealm()
    {
        return realmService.listRealm();
    }
}