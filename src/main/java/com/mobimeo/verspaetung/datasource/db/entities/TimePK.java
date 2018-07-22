package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalTime;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Embeddable
public class TimePK implements Serializable {

    @ManyToOne(
            targetEntity = Line.class,
            fetch = FetchType.LAZY
    )
    private final Line line;

    @ManyToOne(
            targetEntity = Stop.class,
            fetch = FetchType.LAZY
    )
    private final Stop stop;

    private final LocalTime time;
}
