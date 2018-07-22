package com.mobimeo.verspaetung.service;

import com.google.common.collect.Lists;
import com.mobimeo.verspaetung.VerspaetungApplication;
import com.mobimeo.verspaetung.datasource.db.entities.Delay;
import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.repository.DelaysRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VerspaetungApplication.class)
public class DelaysServiceTest {

    @Autowired
    private DelaysRepository repository;
    private DelaysService delayService;

    @Before
    public void setUp() {
        repository.saveAll(
            Lists.newArrayList(
                new Delay(new Line(1, "line1"), 0),
                new Delay(new Line(2, "line2"), 1),
                new Delay(new Line(3, "line3"), 2)
            )
        );

        delayService = new DelaysService(repository);
    }

    @Test
    public void shouldReturnTrueIfLineDelayed() {
        assertTrue(delayService.isLineDelayed("line1"));
    }

    @Test
    public void shouldReturnFalseIfLineNotPresent() {
        assertFalse(delayService.isLineDelayed("line-not-present"));
    }

    @Test
    public void shouldReturnFalseIfLineDelayedZero() {
        assertFalse(delayService.isLineDelayed("line0"));
    }

}