package io.inaam.main.controller;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserCoinDto;
import io.inaam.main.service.CoinService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/{realmName}/coins")
public class CoinController
{
    private final CoinService coinService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new coin type", notes = "Pass coin data according to CoinDto model")
    public void createCoin(@PathVariable String realmName, @RequestBody CoinDto coin)
    {
        coinService.createCoin(coin, realmName);
    }

    @GetMapping
    @ApiOperation(value = "Get all coins")
    public List<CoinDto> getCoins(@PathVariable String realmName)
    {
        return coinService.getCoins(realmName);
    }

    @PutMapping("/{coinName}")
    @ApiOperation(value = "Update coin", notes = "Both coin name and conversion rate are updatable.")
    public CoinDto updateCoin(@PathVariable String realmName, @PathVariable String coinName, @RequestBody CoinDto coin)
    {
        return coinService.updateCoin(realmName, coinName, coin);
    }
}
