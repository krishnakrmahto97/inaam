package io.inaam.main.exception;

public class UserCoinException extends RuntimeException
{
    public static final String INSUFFICIENT_COINS_MESSAGE = "User has insufficient coins!";

    public UserCoinException(String message) { super(message); }
}
