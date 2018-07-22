package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "delays")
public class Delay {

    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private final Line line;

    private final int delayInMinutes;

}
