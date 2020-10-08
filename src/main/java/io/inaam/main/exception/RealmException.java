package io.inaam.main.exception;

public class RealmException extends RuntimeException
{
    public static final String NO_REALM_FOUND ="No Active Realm Found";

    public RealmException(String message)
    {
        super(message);
    }
}
