package com.murk.dk021.core.to;

import lombok.NonNull;
import lombok.Value;

@Value
public class UpdateInfoTO {
    private @NonNull STATUS status;
    private @NonNull String message;
}
