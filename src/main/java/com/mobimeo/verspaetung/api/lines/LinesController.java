package com.mobimeo.verspaetung.api.lines;

import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.service.DelaysService;
import com.mobimeo.verspaetung.service.LinesService;
import com.mobimeo.verspaetung.service.StopsService;
import com.mobimeo.verspaetung.service.TimesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lines")
@RequiredArgsConstructor
public class LinesController {

    private final DelaysService delaysService;
    private final LinesService linesService;
    private final TimesService timesService;
    private final StopsService stopsService;

    @GetMapping
    public ResponseEntity retrieveLines(
            @RequestParam("timestamp") LocalTime timestamp,
            @RequestParam("x") int posX,
            @RequestParam("y") int posY
    ) {
        Optional<Stop> stop = stopsService.findStopAtPoint(new Point(posX, posY));

        if (!stop.isPresent()) {
            return new ResponseEntity<>(String.format("There's no stop at %d,%d", posX, posY),  HttpStatus.NOT_FOUND);
        }

        List<Line> lines = timesService.findLinesAtStopAndTimestamp(stop.get(), timestamp);

        VehiclesResponse vehiclesResponse = new VehiclesResponse(
                lines.stream()
                    .map(Line::getName)
                    .collect(Collectors.toList())
        );

        return new ResponseEntity<>(vehiclesResponse, HttpStatus.OK);
    }


    @GetMapping(value = "/{name}")
    public ResponseEntity delayedLine(@PathVariable("name") String lineName) {
        if (!linesService.lineExists(lineName)) {
            return new ResponseEntity<>("The line " + lineName + " doesn't exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DelayedLineResponse(delaysService.isLineDelayed(lineName)), HttpStatus.OK);
    }

}
