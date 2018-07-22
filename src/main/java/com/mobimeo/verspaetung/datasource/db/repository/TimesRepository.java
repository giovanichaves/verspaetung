package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.entities.Time;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimesRepository extends CrudRepository<Time, Integer> {

    List<Time> findByStopIdAndTime(Stop stopId, LocalTime timestamp);

}