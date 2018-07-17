package com.mobimeo.verspaetung.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.*;

@Getter
@EqualsAndHashCode
public class Stop {

    private final int id;
    private final Point coord;

    public Stop(
            @JsonProperty("stop_id") int id,
            @JsonProperty("x") int posX,
            @JsonProperty("y") int posY
    ) {
        this.id = id;
        this.coord = new Point(posX, posY);
    }
}
