package com.mobimeo.verspaetung.config;

import com.mobimeo.verspaetung.datasource.db.entities.Delay;
import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.entities.Time;
import com.mobimeo.verspaetung.datasource.db.repository.TimesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class InitialDataConfig {

    private @Value("${csv.lines}") String linesCSVFileLocation;
    private @Value("${csv.delays}") String delaysCSVFileLocation;
    private @Value("${csv.stops}") String stopsCSVFileLocation;
    private @Value("${csv.times}") String timesCSVFileLocation;

    private List<Delay> delayList = new ArrayList<>();
    private List<Line> lineList = new ArrayList<>();
    private List<Stop> stopList = new ArrayList<>();
    private List<Time> timeList = new ArrayList<>();
    private final TimesRepository timesRepository;

    @PostConstruct
    public void init() throws Exception {
        loadCSV(linesCSVFileLocation, csvToLines());
        loadCSV(delaysCSVFileLocation, csvToDelays());
        loadCSV(stopsCSVFileLocation, csvToStops());
        loadCSV(timesCSVFileLocation, csvToTimes());


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

    private Consumer<CSVRecord> csvToLines() {
        return ((csvRecord) -> lineList.add(
                new Line(
                        Integer.valueOf(csvRecord.get("line_id")),
                        csvRecord.get("line_name")
                )
        ));
    }

    private Consumer<CSVRecord> csvToDelays() {
        return ((csvRecord) -> {
            Optional<Line> line = findLineByName(csvRecord.get("line_name"));
            if (!line.isPresent()) {
                throw new LineNotFoundException("Line " + csvRecord.get("line_name") + " doesn't exist");
            }

            delayList.add(
                new Delay(
                    line.get(),
                    Integer.parseInt(csvRecord.get("delay"))
                )
            );
        });
    }

    private Consumer<CSVRecord> csvToStops() {
        return ((csvRecord) -> stopList.add(
            new Stop(
                    Integer.valueOf(csvRecord.get("stop_id")),
                    Integer.valueOf(csvRecord.get("x")),
                    Integer.valueOf(csvRecord.get("y"))
            )
        ));
    }

    private Consumer<CSVRecord> csvToTimes() {
        return ((csvRecord) -> {
            Optional<Line> line = findLineById(Integer.valueOf(csvRecord.get("line_id")));
            if (!line.isPresent()) {
                throw new LineNotFoundException("Line " + csvRecord.get("line_name") + " doesn't exist");
            }

            Optional<Stop> stop = findStopById(Integer.valueOf(csvRecord.get("stop_id")));
            if (!stop.isPresent()) {
                throw new StopNotFoundException("Stop " + csvRecord.get("stop_id") + " doesn't exist");
            }

            timeList.add(
                new Time(
                        line.get(),
                        stop.get(),
                        LocalTime.parse(csvRecord.get("time"))
                )
            );
        });
    }

    private Optional<Stop> findStopById(Integer stopId) {
        return stopList.stream()
                .filter(stop -> stop.getStopId() == stopId)               .findFirst()
                .findFirst();
    }

    private Optional<Line> findLineById(Integer lineId) {
        return lineList.stream()
                .filter(line -> line.getLineId() == lineId)
                .findFirst();
    }

    private Optional<Line> findLineByName(String lineName) {
        return lineList.stream()
                .filter(line -> line.getName().equals(lineName))
                .findFirst();
    }

    private void saveTimes() {
        timeList.forEach(timesRepository::save);
    }
}
