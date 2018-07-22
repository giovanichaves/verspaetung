package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "lines")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;
    private final String name;

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "line"
    )
    private Delay delay;
}
