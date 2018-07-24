package com.mobimeo.verspaetung.service;

import com.google.common.collect.Lists;
import com.mobimeo.verspaetung.VerspaetungApplication;
import com.mobimeo.verspaetung.datasource.db.entities.Stop;
import com.mobimeo.verspaetung.datasource.db.repository.StopsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VerspaetungApplication.class)
@Transactional
public class StopsServiceTest {

    @Autowired
    private StopsRepository repository;
    private StopsService stopsService;

    @Before
    public void setUp() {
        repository.saveAll(
            Lists.newArrayList(
                new Stop(4, 4, 4),
                new Stop(2, 2, 2),
                new Stop(3, 3, 3)
            )
        );
        stopsService = new StopsService(repository);
    }

    @Test
    public void shouldReturnStopIdAtXAndY() {
        Stop stopExpected = new Stop(4, 4, 4);
        Optional<Stop> stopAtPoint = stopsService.findStopAtPoint(new Point(1, 0));

        assertTrue(stopAtPoint.isPresent());
        assertTrue(stopExpected.equals(stopAtPoint.get()));
    }

    @Test
    public void shouldReturnEmptyOnNonExistingStop() {
        Optional<Stop> stopAtPoint = stopsService.findStopAtPoint(new Point(10, 10));

        assertFalse(stopAtPoint.isPresent());
    }

}