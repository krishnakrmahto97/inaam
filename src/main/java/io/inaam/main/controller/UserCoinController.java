package io.inaam.main.controller;

import io.inaam.main.dto.CoinTransactionDto;
import io.inaam.main.dto.UserCoinDto;
import io.inaam.main.service.UserCoinService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/{realmName}/users/{userName}/coins")
public class UserCoinController
{
    private final UserCoinService userCoinService;

    @GetMapping
    @ApiOperation(value = "Get user coins")
    public List<UserCoinDto> getUserCoins(@PathVariable String realmName, @PathVariable String userName)
    {
        return userCoinService.getUserCoinDtoList(realmName, userName);
    }

    @GetMapping("/transactions")
    @ApiOperation(value = "Get coin transactions of a user")
    public List<CoinTransactionDto> getCoinTransactions(@PathVariable String realmName, @PathVariable String userName)
    {
        return userCoinService.getUserCoinTransactionDtoList(realmName, userName);
    }

    @PutMapping("/addCoins")
    @ApiOperation(value = "Add coins under a user")
    public List<UserCoinDto> addUserCoins(@PathVariable String realmName,
                                          @PathVariable String userName,
                                          @RequestBody List<UserCoinDto> addCoinsDetails)
    {
        return userCoinService.createTransactionAndAddUserCoins(realmName, userName, addCoinsDetails);
    }

    @PutMapping ("/redeemCoins")
    @ApiOperation(value = "Redeem user coins")
    public List<UserCoinDto> redeemUserCoins(@PathVariable String realmName,
                                             @PathVariable String userName,
                                             @RequestBody List<UserCoinDto> redeemCoinsDetails)
    {
        return userCoinService.createTransactionAndRedeemUserCoins(realmName, userName, redeemCoinsDetails);
    }

    @PostMapping("/addAndRedeemCoins")
    @ApiOperation(value = "Add and redeem user coins based on purchase amount")
    public UserCoinDto addAndRedeemCoins(@PathVariable String realmName,
                                         @PathVariable String userName,
                                         @RequestBody BigDecimal purchaseAmount)
    {
        return userCoinService.addAndRedeemCoins(realmName, userName, purchaseAmount);
    }
}
