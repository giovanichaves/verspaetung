package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "stops")
public class Stop {
    public Stop(int stop_id, int x, int y) {
        this.stop_id = stop_id;
        this.x = x;
        this.y = y;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int stop_id;
    private int x;
    private int y;
}
