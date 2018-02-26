package com.kbc.oauth2Server.exceptions;

public class DeleteAdminException extends RuntimeException {

    public DeleteAdminException(String message) {
        super(message);
    }

    public DeleteAdminException(String message, Throwable cause) {
        super(message, cause);
    }
}
