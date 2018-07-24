package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LinesRepository extends CrudRepository<Line, Integer> {

    Optional<Line> findByName(String name);

}
