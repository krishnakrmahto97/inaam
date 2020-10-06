package io.inaam.main.controller;

import io.inaam.main.dto.CoinDto;
import io.inaam.main.dto.UserCoinDto;
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

    @PutMapping("/{userName}/add")
    public List<UserCoinDto> addUserCoins(@PathVariable String realmName, @PathVariable String userName, @RequestBody List<UserCoinDto> addCoinsDetails)
    {
        return coinService.createTransactionAndAddUserCoins(realmName, userName, addCoinsDetails);
    }

    @PutMapping ("/{userName}/redeem")
    public List<UserCoinDto> redeemUserCoins(@PathVariable String realmName, @PathVariable String userName, @RequestBody List<UserCoinDto> redeemCoinsDetails)
    {
        return coinService.createTransactionAndRedeemUserCoins(realmName, userName, redeemCoinsDetails);
    }
}
