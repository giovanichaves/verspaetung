package com.mobimeo.verspaetung.datasource.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class CsvDatasource<T> {

    private final File csvFile;
    private final Class<T> classType;

    CsvDatasource(String csvFileLocation, Class<T> classType) throws FileNotFoundException {
        checkNotNull(csvFileLocation);
        checkNotNull(classType);

        this.csvFile = getFile(csvFileLocation);
        this.classType = classType;
    }

    public List<T> fetchAll() {
        List<T> recordList = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();

        ObjectReader objectReader = mapper
                .enable(CsvParser.Feature.TRIM_SPACES)
                .registerModule(new JavaTimeModule())
                .readerFor(classType)
                .with(CsvSchema.emptySchema().withHeader());

        try (Reader reader = new FileReader(csvFile)) {
            MappingIterator<T> mi = objectReader.readValues(reader);
            while (mi.hasNext()) {
                recordList.add(mi.next());
            }
        } catch (IOException e) {
            System.out.println("There was an error reading the datasource file");
        }

        return recordList;
    }

    private File getFile(String csvFileLocation) throws FileNotFoundException {
        URL fileUrl = CsvDatasource.class.getClassLoader().getResource(csvFileLocation);
        if (fileUrl == null) {
            throw new FileNotFoundException("The specified datasource file " + csvFileLocation + " was not found");
        }

        return new File(fileUrl.getFile());
    }
}
