package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinesRepository extends JpaRepository<Line, Integer> {

    Optional<Line> findByName(String name);

    List<Line> findByIdIn(List<Integer> ids);

}
