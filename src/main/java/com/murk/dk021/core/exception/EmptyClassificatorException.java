package com.murk.dk021.core.exception;

public class EmptyClassificatorException extends RuntimeException {
    public EmptyClassificatorException() {
    }

    public EmptyClassificatorException(String message) {
        super(message);
    }

    public EmptyClassificatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
