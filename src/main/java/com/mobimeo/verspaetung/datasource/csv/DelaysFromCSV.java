package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.datasource.db.entities.Delay;
import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.repository.DelaysRepository;
import com.mobimeo.verspaetung.datasource.db.repository.LinesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@Component
public class DelaysFromCSV {

    @Value("${csv.delays}")
    private final String csvFileLocation;
    private final DelaysRepository delaysRepository;
    private final LinesRepository linesRepository;

    public Consumer<CSVRecord> consumer() {
        return ((csvRecord) -> {
            Optional<Line> line = linesRepository.findByName(csvRecord.get("line_name"));
            if (!line.isPresent()) {
                throw new LineNotFoundException("Line " + csvRecord.get("line_name") + " doesn't exist");
            }

            delaysRepository.save(
                    new Delay(
                            line.get(),
                            Integer.parseInt(csvRecord.get("delay"))
                    )
            );
        });
    }
}
