package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "delays")
public class Delay {
    public Delay(Line line, int delayInMinutes) {
        this.line = line;
        this.delayInMinutes = delayInMinutes;
    }

    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Line line;

    private int delayInMinutes;

}
