package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.repository.LinesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@Component
public class LinesFromCSV {

    private @Value("${csv.lines}") String csvFileLocation;
    private final LinesRepository repository;

    public Consumer<CSVRecord> consumer() {
        return ((csvRecord) -> repository.save(
            new Line(
                    Integer.valueOf(csvRecord.get("line_id")),
                    csvRecord.get("line_name")
            )
        ));
    }
}
