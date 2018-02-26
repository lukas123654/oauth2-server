package com.kbc.oauth2Server.exceptions;

public class JpaCreateException extends RuntimeException {

    public JpaCreateException(String message) {
        super(message);
    }

    public JpaCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
