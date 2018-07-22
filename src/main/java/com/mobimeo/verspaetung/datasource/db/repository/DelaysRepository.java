package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Delay;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DelaysRepository extends CrudRepository<Delay, Integer>{

    Optional<Delay> findByLineName(String lineName);
}
