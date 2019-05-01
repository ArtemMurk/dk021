package com.murk.dk021.core.to;

import lombok.NonNull;
import lombok.Value;

@Value
public class ClassificatorTO extends StatusMessage {
    private @NonNull String code;
    private @NonNull String name;

    public ClassificatorTO(@NonNull String code,@NonNull String name) {
        super(STATUS.SUCCESS);
        this.code = code;
        this.name = name;
    }
}
