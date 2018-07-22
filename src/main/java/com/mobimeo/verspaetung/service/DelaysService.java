package com.mobimeo.verspaetung.service;

import com.mobimeo.verspaetung.datasource.db.repository.DelaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DelaysService {

    private final DelaysRepository delaysRepository;

    public boolean isLineDelayed(String lineName) {
        return delaysRepository.findByLineName(lineName)
                .map(delay -> delay.getDelayInMinutes() > 0)
                .orElse(false);
    }
}
