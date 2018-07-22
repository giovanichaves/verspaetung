package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "delays")
public class Delay implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private final Line line;

    private final int delayInMinutes;

}
