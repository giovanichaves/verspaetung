package com.mobimeo.verspaetung.service;

import com.google.common.collect.Lists;
import com.mobimeo.verspaetung.VerspaetungApplication;
import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.entities.Time;
import com.mobimeo.verspaetung.datasource.db.repository.TimesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VerspaetungApplication.class)
@Transactional
public class TimesServiceTest {

    @Autowired
    private TimesRepository repository;
    private TimesService timesService;

    @Before
    public void setUp() {
        repository.saveAll(
            Lists.newArrayList(
                    new Time(
                        new Line(1, "line1"),
                        new Stop(1, 1, 1),
                        LocalTime.parse("10:00:00")
                    ),
                    new Time(
                        new Line(2, "line2"),
                        new Stop(1, 1, 1),
                        LocalTime.parse("10:00:00")
                    ),
                    new Time(
                        new Line(1, "line1"),
                        new Stop(2, 2, 2),
                        LocalTime.parse("11:00:00")
                    ),
                    new Time(
                        new Line(3, "line3"),
                        new Stop(4, 4, 4),
                        LocalTime.parse("10:00:00")
                    )
            )
        );

        timesService = new TimesService(repository);
    }

    @Test
    public void shouldReturnListOfLineIdsBySearchedStopAndTimestamp() {
        List<Integer> lineIdsExpected = Lists.newArrayList(1, 2);

        Stop stop = new Stop(1, 1, 1);
        LocalTime timestamp = LocalTime.parse("10:00:00");

        assertThat(timesService.findLinesAtStopAndTimestamp(stop, timestamp), is(lineIdsExpected));
    }

    @Test
    public void shouldReturnEmptyListWhenSearchedStopAndTimestampNotFound() {
        Stop stop = new Stop(1, 1, 1);
        LocalTime timestamp = LocalTime.parse("12:00:00");

        assertTrue(timesService.findLinesAtStopAndTimestamp(stop, timestamp).isEmpty());
    }

}