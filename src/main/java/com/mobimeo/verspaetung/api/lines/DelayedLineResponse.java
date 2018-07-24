package com.mobimeo.verspaetung.api.lines;

import lombok.Getter;

@Getter
public class DelayedLineResponse {

    private boolean delayed;

    public DelayedLineResponse(boolean delayed) {
        this.delayed = delayed;
    }
}
