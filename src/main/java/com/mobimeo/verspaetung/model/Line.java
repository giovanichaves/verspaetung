package com.mobimeo.verspaetung.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@EqualsAndHashCode
public class Line {

    private final int id;
    private final String name;

    public Line(
            @JsonProperty("line_id") int id,
            @JsonProperty("line_name") String name
    ) {
        checkNotNull(name);
        this.id = id;
        this.name = name;
    }
}
