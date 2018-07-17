package com.mobimeo.verspaetung.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@EqualsAndHashCode
public class Time {

    private final int lineId;
    private final int stopId;
    private final LocalTime time;

    public Time(
            @JsonProperty("line_id") int lineId,
            @JsonProperty("stop_id") int stopId,
            @JsonProperty("time") LocalTime time
    ) {
        checkNotNull(time);
        this.lineId = lineId;
        this.stopId = stopId;
        this.time = time;
    }
}
