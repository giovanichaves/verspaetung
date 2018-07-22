package com.mobimeo.verspaetung.service;

import com.mobimeo.verspaetung.datasource.db.repository.LinesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinesService {

    private final LinesRepository linesRepository;

    public boolean lineExists(String lineName) {
        return linesRepository.findByName(lineName).isPresent();
    }

}
