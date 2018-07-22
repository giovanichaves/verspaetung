package com.mobimeo.verspaetung.config;

import com.mobimeo.verspaetung.datasource.csv.DelaysFromCSV;
import com.mobimeo.verspaetung.datasource.csv.LinesFromCSV;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.Reader;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class InitialDataConfig {

    private final LinesFromCSV linesFromCSV;
    private final DelaysFromCSV delaysFromCSV;

    @PostConstruct
    public void init() throws Exception {
        loadCSV(linesFromCSV.getCsvFileLocation(), linesFromCSV.consumer());
        loadCSV(delaysFromCSV.getCsvFileLocation(), delaysFromCSV.consumer());
    }


    private void loadCSV(String csvFileLocation, Consumer<CSVRecord> consumer) throws Exception {
        Reader in = new FileReader(csvFileLocation);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            consumer.accept(record);
        }
    }
 }
