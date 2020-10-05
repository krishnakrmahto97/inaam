package io.inaam.main.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CoinController
{

    @PostMapping("/{{realm}}/coin/")
    public void createCoin(@PathVariable String realm)
    {

    }

    @GetMapping("/{{realm}}/coin/{{username}}")
    public void getCoinType(@PathVariable String realm, @PathVariable String username)
    {

    }

    @GetMapping("/{{realm}}/coin/balance")
    public void getCoinBalance(@PathVariable String realm)
    {

    }

    @PostMapping("/{{realm}}/coin/{{username}}/add")
    public void addCoin(@PathVariable String realm, @PathVariable String username)
    {

    }

    @PutMapping("/{{realm}}/coin/{{username}}/deduct")
    public void deductCoin(@PathVariable String realm, @PathVariable String username)
    {

    }
}
