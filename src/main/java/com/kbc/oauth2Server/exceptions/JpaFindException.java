package com.kbc.oauth2Server.exceptions;

public class JpaFindException extends RuntimeException {

    public JpaFindException(String message) {
        super(message);
    }

    public JpaFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
