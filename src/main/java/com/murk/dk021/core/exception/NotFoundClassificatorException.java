package com.murk.dk021.core.exception;

public class NotFoundClassificatorException extends RuntimeException {

    public NotFoundClassificatorException(String message) {
        super(message);
    }

    public NotFoundClassificatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
