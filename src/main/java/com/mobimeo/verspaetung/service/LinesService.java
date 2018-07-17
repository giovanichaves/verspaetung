package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.model.Line;
import com.mobimeo.verspaetung.repository.LinesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinesService {

    private final ImmutableList<Line> linesList;

    public LinesService(LinesRepository linesRepository) {
        this.linesList = linesRepository.getData();
    }

    public boolean lineExists(String lineName) {
        return linesList.stream()
                .anyMatch(line -> line.getName().equals(lineName));
    }

    public List<String> findLineNamesByIds(List<Integer> ids) {
        return linesList.stream()
                .filter(line -> ids.contains(line.getId()))
                .map(Line::getName)
                .collect(Collectors.toList());
    }

}
