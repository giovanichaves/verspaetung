package com.mobimeo.verspaetung.service;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.entities.Time;
import com.mobimeo.verspaetung.datasource.db.repository.TimesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimesService {

    private final TimesRepository timesRepository;

    public List<Line> findLinesAtStopAndTimestamp(Stop stop, LocalTime timestamp) {
        return timesRepository.findByStopIdAndTime(stop.getId(), timestamp).stream()
                .map(Time::getLine)
                .collect(Collectors.toList());
    }
}
