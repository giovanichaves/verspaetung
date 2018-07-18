package com.mobimeo.verspaetung.api.lines;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Getter
public class DelayedLineResponse {

    private boolean delayed;

    public DelayedLineResponse(boolean delayed) {
        this.delayed = delayed;
    }
}
