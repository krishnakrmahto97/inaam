package io.inaam.main.controller;

import io.inaam.main.dto.RealmDto;
import io.inaam.main.service.RealmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/realm")
public class RealmController
{
    private final RealmService realmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRealm(@RequestBody RealmDto realmDto)
    {
        realmService.createRealm(realmDto);
    }

    @GetMapping
    public List<RealmDto> listRealm()
    {
        return realmService.listRealm();
    }
}