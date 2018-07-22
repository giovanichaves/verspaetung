package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "stops")
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;
    private final int x;
    private final int y;
}
