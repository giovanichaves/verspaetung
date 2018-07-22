package com.mobimeo.verspaetung.datasource.db.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "times")
public class Time {

    @EmbeddedId
    private final TimePK timePK;

}
