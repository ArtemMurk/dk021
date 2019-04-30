package com.murk.dk021.core.to;

import lombok.NonNull;
import lombok.Value;

@Value
public class ClassificatorTO {
    private @NonNull String code;
    private @NonNull String name;

    public ClassificatorTO(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
