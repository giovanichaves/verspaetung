package com.mobimeo.verspaetung.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Delay {

    private final String lineName;
    private final int delayInMinutes;

    public Delay(
            @JsonProperty("line_name") String lineName,
            @JsonProperty("delay") int delayInMinutes
    ) {
        this.lineName = lineName;
        this.delayInMinutes = delayInMinutes;
    }
}
