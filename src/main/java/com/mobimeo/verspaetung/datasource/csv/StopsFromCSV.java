package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.repository.StopsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@Component
public class StopsFromCSV {

    @Value("${csv.stops}")
    private String csvFileLocation;
    private final StopsRepository repository;

    public Consumer<CSVRecord> consumer() {
        return ((csvRecord) -> repository.save(
                new Stop(
                        Integer.valueOf(csvRecord.get("stop_id")),
                        Integer.valueOf(csvRecord.get("x")),
                        Integer.valueOf(csvRecord.get("y"))
                )
        ));
    }
}
