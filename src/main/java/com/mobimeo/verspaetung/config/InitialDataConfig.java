package com.mobimeo.verspaetung.config;

import com.mobimeo.verspaetung.datasource.csv.DelaysFromCSV;
import com.mobimeo.verspaetung.datasource.csv.LinesFromCSV;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
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
        Reader in = new FileReader(getFile(csvFileLocation));
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            consumer.accept(record);
        }
    }

    private File getFile(String csvFileLocation) throws FileNotFoundException {
        URL fileUrl = this.getClass().getClassLoader().getResource(csvFileLocation);
        if (fileUrl == null) {
            throw new FileNotFoundException("The specified datasource file " + csvFileLocation + " was not found");
        }

        return new File(fileUrl.getFile());
    }
 }
