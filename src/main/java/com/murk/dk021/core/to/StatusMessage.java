package com.murk.dk021.core.to;

import lombok.Data;
import lombok.NonNull;

@Data
abstract class StatusMessage {
    private @NonNull STATUS status;

    StatusMessage(@NonNull STATUS status) {
        this.status = status;
    }

}
