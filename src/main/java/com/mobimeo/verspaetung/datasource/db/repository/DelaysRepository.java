package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Delay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DelaysRepository extends JpaRepository<Delay, Integer> {

    Optional<Delay> findByLineName(String lineName);
}
