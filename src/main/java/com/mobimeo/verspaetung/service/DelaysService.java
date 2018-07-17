package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.model.Delay;
import com.mobimeo.verspaetung.repository.DelaysRepository;
import org.springframework.stereotype.Service;

@Service
public class DelaysService {

    private final ImmutableList<Delay> delaysList;

    public DelaysService(DelaysRepository delaysRepository) {
        this.delaysList = delaysRepository.getData();
    }

    public boolean isLineDelayed(String lineName) {
        return delaysList.stream().anyMatch(delay -> delay.getLineName().equals(lineName) && delay.getDelayInMinutes() > 0);
    }
}
