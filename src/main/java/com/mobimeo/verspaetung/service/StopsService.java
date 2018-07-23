package com.mobimeo.verspaetung.service;

import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.repository.StopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StopsService {

    private final StopsRepository stopsRepository;

    public Optional<Stop> findStopAtPoint(Point coord) {
        return stopsRepository.findByXAndY((int)coord.getX(), (int)coord.getY());
    }

}
