package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.repository.LinesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class LinesFromCSV {

    @Value("${csv.lines}")
    private final String csvFileLocation;
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
