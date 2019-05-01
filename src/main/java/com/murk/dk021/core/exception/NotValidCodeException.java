package com.murk.dk021.core.exception;

public class NotValidCodeException extends RuntimeException {


    public NotValidCodeException(String message) {
        super(message);
    }

    public NotValidCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
