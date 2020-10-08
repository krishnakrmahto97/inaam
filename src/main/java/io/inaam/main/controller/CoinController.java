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
    
    @GetMapping("/{userName}")
    @ApiOperation(value = "Get user coins")
    public List<UserCoinDto> getUserCoins(@PathVariable String realmName, @PathVariable String userName)
    {
        return coinService.getUserCoinDtoList(realmName, userName);
    }

    @GetMapping("/{userName}/transaction")
    @ApiOperation(value = "Get coin transactions of a user")
    public List<CoinTransactionDto> getCoinTransactions(@PathVariable String realmName, @PathVariable String userName)
    {
        return coinService.getUserCoinTransactionDtoList(realmName, userName);
    }

    @PutMapping("/{userName}/add")
    @ApiOperation(value = "Add coins under a user")
    public List<UserCoinDto> addUserCoins(@PathVariable String realmName, @PathVariable String userName, @RequestBody List<UserCoinDto> addCoinsDetails)
    {
        return coinService.createTransactionAndAddUserCoins(realmName, userName, addCoinsDetails);
    }

    @PutMapping ("/{userName}/redeem")
    @ApiOperation(value = "Redeem user coins")
    public List<UserCoinDto> redeemUserCoins(@PathVariable String realmName, @PathVariable String userName, @RequestBody List<UserCoinDto> redeemCoinsDetails)
    {
        return coinService.createTransactionAndRedeemUserCoins(realmName, userName, redeemCoinsDetails);
    }
}
