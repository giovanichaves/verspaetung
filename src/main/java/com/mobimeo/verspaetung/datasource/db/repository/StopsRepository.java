package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StopsRepository extends CrudRepository<Stop, Integer> {

    Optional<Stop> findByXandY(int x, int y);

}
