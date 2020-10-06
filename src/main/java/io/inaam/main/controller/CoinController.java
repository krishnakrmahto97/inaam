package io.inaam.main.controller;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.service.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/{realmName}/coin")
public class CoinController
{
    private final CoinService coinService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCoin(@PathVariable String realmName, @RequestBody CoinDto coin)
    {
        coinService.createCoin(coin, realmName);
    }

    @GetMapping("/")
    public List<CoinDto> getCoins(@PathVariable String realmName)
    {
        return coinService.getCoins(realmName);
    }

    @PostMapping("/{userName}/add")
    public void addUserCoins(@PathVariable String realmName, @PathVariable String userName, @RequestBody List<CoinTransactionDto> addCoinsDetails)
    {
        coinService.addUserCoins(realmName, userName, addCoinsDetails);
    }

    @PostMapping("/{userName}/redeem")
    public void redeemUserCoins(@PathVariable String realmName, @PathVariable String userName, @RequestBody List<CoinTransactionDto> redeemCoinsDetails)
    {
        coinService.redeemUserCoins(realmName, userName, redeemCoinsDetails);
    }
}
