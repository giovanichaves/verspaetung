package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.model.Stop;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class StopsFromCSV extends CsvDatasource<Stop> {
    private StopsFromCSV(@Value("${csv.stops}") String csvFileLocation) throws FileNotFoundException {
        super(csvFileLocation, Stop.class);
    }
}
