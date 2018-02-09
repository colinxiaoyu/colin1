package com.colin.colin1.hotUpdate.exception;

public class UnknownException extends RuntimeException {

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownException(String message) {
        super(message);
    }
}