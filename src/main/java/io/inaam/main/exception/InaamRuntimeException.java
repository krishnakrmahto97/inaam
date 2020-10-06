package io.inaam.main.exception;

public class InaamRuntimeException extends RuntimeException {
    public InaamRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InaamRuntimeException(String message) {
        super(message);
    }
}
