package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.repository.StopsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class StopsFromCSV {

    @Value("${csv.stops}")
    private final String csvFileLocation;
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
