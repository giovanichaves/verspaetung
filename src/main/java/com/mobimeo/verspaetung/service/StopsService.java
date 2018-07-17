package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.model.Stop;
import com.mobimeo.verspaetung.repository.StopsRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
public class StopsService {

    private final ImmutableList<Stop> stopsList;

    public StopsService(StopsRepository stopsRepository) {
        this.stopsList = stopsRepository.getData();
    }

    public Optional<Stop> findStopAtPoint(Point coord) {
        return stopsList.stream().filter(stop -> stop.getCoord().equals(coord)).findFirst();
    }

}
