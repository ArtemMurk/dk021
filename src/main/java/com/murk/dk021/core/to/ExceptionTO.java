package com.murk.dk021.core.to;

import lombok.Value;

@Value
public class ExceptionTO extends StatusMessageTO{
    private String cause;

    public ExceptionTO(String cause) {
        super(STATUS.FAIL);
        this.cause = cause;
    }
}
