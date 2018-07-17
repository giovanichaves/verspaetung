package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.model.Time;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class TimesFromCSV extends CsvDatasource<Time> {
    private TimesFromCSV(@Value("${csv.times}") String csvFileLocation) throws FileNotFoundException {
        super(csvFileLocation, Time.class);
    }

}
