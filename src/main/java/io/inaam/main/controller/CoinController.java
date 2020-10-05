package io.inaam.main.controller;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.service.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CoinController
{
    private final CoinService coinService;

    @PostMapping("/{realmName}/coin")
    public void createCoin(@PathVariable String realmName, @RequestBody CoinDto coin)
    {
        coinService.createCoin(coin, realmName);
    }

    @GetMapping("/{realmName}/coin")
    public List<CoinDto> getCoins(@PathVariable String realmName)
    {
        return coinService.getCoins(realmName);
    }
}
