package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.model.Stop;
import com.mobimeo.verspaetung.model.Time;
import com.mobimeo.verspaetung.repository.TimesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesService {

    private final ImmutableList<Time> timesList;

    public TimesService(TimesRepository timesRepository) {
        this.timesList = timesRepository.getData();
    }

    public List<Integer> findLineIdsAtStopAndTimestamp(Stop stop, LocalTime timestamp) {
        return timesList.stream()
                .filter(time -> time.getStopId() == stop.getId() && time.getTime().equals(timestamp))
                .map(Time::getLineId)
                .collect(Collectors.toList());
    }
}
