package io.inaam.main.exception;

public class UserException extends RuntimeException
{
    public static String NO_ACTIVE_USER ="No Active User Present";
    public UserException(String message)
    {
        super(message);
    }
}
