package com.mobimeo.verspaetung.service;

import com.google.common.collect.Lists;
import com.mobimeo.verspaetung.datasource.db.entities.Line;
import com.mobimeo.verspaetung.datasource.db.repository.LinesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LinesServiceTest {

    @Autowired
    private LinesRepository repository;
    private LinesService linesService;

    @Before
    public void setUp() {
        repository.saveAll(
            Lists.newArrayList(
                new Line(1, "line1"),
                new Line(2, "line2"),
                new Line(3, "line3"),
                new Line(4, "line4")
            )
        );
        linesService = new LinesService(repository);
    }

    @Test
    public void shouldReturnTrueIfLineIsPresent() {
        assertTrue(linesService.lineExists("line1"));
    }

    @Test
    public void shouldReturnFalseIfLineIsNotFound() {
        assertFalse(linesService.lineExists("line-not-existent"));
    }

}