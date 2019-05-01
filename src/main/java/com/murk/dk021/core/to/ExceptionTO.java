package com.murk.dk021.core.to;

import lombok.NonNull;
import lombok.Value;

@Value
public class ExceptionTO extends StatusMessage {
    private @NonNull String cause;

    public ExceptionTO(@NonNull String cause) {
        super(STATUS.FAIL);
        this.cause = cause;
    }
}
