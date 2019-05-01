package com.murk.dk021.core.to;

import lombok.NonNull;
import lombok.Value;

@Value
public class UpdateInfoTO  extends StatusMessageTO{
    private @NonNull String message;

    public UpdateInfoTO(@NonNull STATUS status, @NonNull String message) {
        super(status);
        this.message = message;
    }
}
