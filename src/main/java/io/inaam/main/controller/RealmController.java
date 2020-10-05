package io.inaam.main.controller;

import io.inaam.main.dto.RealmDto;
import io.inaam.main.service.RealmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RealmController
{
    private final RealmService realmService;

    @PostMapping("/realm")
    private ResponseEntity<RealmDto> createRealm(@RequestBody RealmDto realmDto)
    {
        return new ResponseEntity(realmService.createRealm(realmDto), HttpStatus.CREATED);
    }

    @GetMapping("/realm")
    private List<RealmDto> listRealm()
    {
        return realmService.listRealm();
    }
}