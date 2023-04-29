package org.original.name.teamify.exception;

public class TeamifyException extends RuntimeException{
    public TeamifyException() {
    }

    public TeamifyException(String message) {
        super(message);
    }

    public TeamifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public TeamifyException(Throwable cause) {
        super(cause);
    }
}
