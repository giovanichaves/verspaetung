package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.model.Stop;
import com.mobimeo.verspaetung.repository.StopsRepository;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StopsServiceTest {
    private StopsService stopsService;

    @Before
    public void setUp() {
        StopsRepository repository = mock(StopsRepository.class);
        when(repository.getData()).thenReturn(
            ImmutableList.of(
                new Stop(1, 1, 1),
                new Stop(2, 2, 2),
                new Stop(3, 3, 3)
            )
        );
        stopsService = new StopsService(repository);
    }

    @Test
    public void shouldReturnStopIdAtXAndY() {
        Stop stopExpected = new Stop(1, 1, 1);
        Optional<Stop> stopAtPoint = stopsService.findStopAtPoint(new Point(1, 1));

        assertTrue(stopAtPoint.isPresent());
        assertThat(stopExpected, equalTo(stopAtPoint.get()));
    }

    @Test
    public void shouldReturnEmptyOnNonExistingStop() {
        Optional<Stop> stopAtPoint = stopsService.findStopAtPoint(new Point(10, 10));

        assertFalse(stopAtPoint.isPresent());
    }

}