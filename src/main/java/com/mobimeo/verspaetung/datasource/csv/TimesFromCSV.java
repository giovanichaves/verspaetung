package com.mobimeo.verspaetung.datasource.csv;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.entities.Time;
import com.mobimeo.verspaetung.datasource.db.entities.TimePK;
import com.mobimeo.verspaetung.datasource.db.repository.LinesRepository;
import com.mobimeo.verspaetung.datasource.db.repository.StopsRepository;
import com.mobimeo.verspaetung.datasource.db.repository.TimesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Consumer;


@Getter
@RequiredArgsConstructor
public class TimesFromCSV {

    @Value("${csv.times}")
    private final String csvFileLocation;
    private final TimesRepository timesRepository;
    private final LinesRepository linesRepository;
    private final StopsRepository stopRepository;

    public Consumer<CSVRecord> consumer() {
        return ((csvRecord) -> {
                Optional<Line> line = linesRepository.findById(Integer.valueOf(csvRecord.get("line_id")));
                if (!line.isPresent()) {
                    throw new LineNotFoundException("Line " + csvRecord.get("line_name") + " doesn't exist");
                }

                Optional<Stop> stop = stopRepository.findById(Integer.valueOf(csvRecord.get("stop_id")));
                if (!stop.isPresent()) {
                    throw new StopNotFoundException("Stop " + csvRecord.get("line_name") + " doesn't exist");
                }

                timesRepository.save(
                        new Time(
                            new TimePK(
                                line.get(),
                                stop.get(),
                                LocalTime.parse(csvRecord.get("timestamp"))
                            )
                        )
                );
        });
    }
}

