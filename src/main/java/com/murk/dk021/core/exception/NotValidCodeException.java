package com.murk.dk021.core.exception;

public class NotValidCodeException extends RuntimeException {
    public NotValidCodeException() {
    }

    public NotValidCodeException(String message) {
        super(message);
    }

    public NotValidCodeException(Throwable cause) {
        super(cause);
    }
}
