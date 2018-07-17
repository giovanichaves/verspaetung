package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.model.Delay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class DelaysFromCSV extends CsvDatasource<Delay> {
    private DelaysFromCSV(@Value("${csv.delays}") String csvFileLocation) throws FileNotFoundException {
        super(csvFileLocation, Delay.class);
    }

}
