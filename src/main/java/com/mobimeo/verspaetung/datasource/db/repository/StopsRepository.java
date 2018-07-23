package com.mobimeo.verspaetung.datasource.db.repository;

import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StopsRepository extends JpaRepository<Stop, Integer> {

    Optional<Stop> findByXAndY(int x, int y);

}
