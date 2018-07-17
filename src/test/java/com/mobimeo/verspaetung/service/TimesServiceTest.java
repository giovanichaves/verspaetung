package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mobimeo.verspaetung.model.Stop;
import com.mobimeo.verspaetung.model.Time;
import com.mobimeo.verspaetung.repository.TimesRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimesServiceTest {
    private TimesService timesService;

    @Before
    public void setUp() {
        TimesRepository repository = mock(TimesRepository.class);
        when(repository.getData()).thenReturn(
            ImmutableList.of(
                new Time(1, 1, LocalTime.parse("10:00:00")),
                new Time(2, 1, LocalTime.parse("10:00:00")),
                new Time(1, 2, LocalTime.parse("11:00:00")),
                new Time(3, 4, LocalTime.parse("10:00:00"))
            )
        );
        timesService = new TimesService(repository);
    }

    @Test
    public void shouldReturnListOfLineIdsBySearchedStopAndTimestamp() {
        List<Integer> lineIdsExpected = Lists.newArrayList(1, 2);

        Stop stop = new Stop(1, 1, 1);
        LocalTime timestamp = LocalTime.parse("10:00:00");

        assertThat(timesService.findLineIdsAtStopAndTimestamp(stop, timestamp), is(lineIdsExpected));
    }

    @Test
    public void shouldReturnEmptyListWhenSearchedStopAndTimestampNotFound() {
        Stop stop = new Stop(1, 1, 1);
        LocalTime timestamp = LocalTime.parse("12:00:00");

        assertTrue(timesService.findLineIdsAtStopAndTimestamp(stop, timestamp).isEmpty());
    }

}