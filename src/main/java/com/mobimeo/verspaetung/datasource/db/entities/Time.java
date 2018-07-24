package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(
    name = "times",
    uniqueConstraints={@UniqueConstraint(columnNames={"line_id", "stop_id", "time"})}
)

public class Time {
    public Time(Line line, Stop stop, LocalTime time) {
        this.line = line;
        this.stop = stop;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(
            targetEntity = Line.class,
            fetch = FetchType.LAZY
    )
    private Line line;

    @ManyToOne(
            targetEntity = Stop.class,
            fetch = FetchType.LAZY
    )
    private Stop stop;

    private LocalTime time;

}
