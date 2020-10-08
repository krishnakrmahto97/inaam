package io.inaam.main.exception;

public class CoinException extends RuntimeException
{
    public static final String COIN_TYPE_NOT_FOUND = "Coin type not found!";

    public CoinException(String message) { super(message); }
}
