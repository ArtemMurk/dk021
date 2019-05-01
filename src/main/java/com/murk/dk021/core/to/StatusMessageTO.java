package com.murk.dk021.core.to;

import lombok.Data;
import lombok.NonNull;

@Data
abstract class StatusMessageTO {
    private @NonNull STATUS status;

    StatusMessageTO(@NonNull STATUS status) {
        this.status = status;
    }

}
