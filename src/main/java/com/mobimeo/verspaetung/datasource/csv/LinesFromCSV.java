package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.model.Line;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class LinesFromCSV extends CsvDatasource<Line> {
    private LinesFromCSV(@Value("${csv.lines}") String csvFileLocation) throws FileNotFoundException {
        super(csvFileLocation, Line.class);
    }

}
