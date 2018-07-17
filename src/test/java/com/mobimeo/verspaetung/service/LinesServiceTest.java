package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mobimeo.verspaetung.model.Line;
import com.mobimeo.verspaetung.repository.LinesRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinesServiceTest {

    private LinesService linesService;

    @Before
    public void setUp() {
        LinesRepository linesRepository = mock(LinesRepository.class);
        when(linesRepository.getData()).thenReturn(
            ImmutableList.of(
                new Line(1, "line1"),
                new Line(2, "line2"),
                new Line(3, "line3"),
                new Line(4, "line4")
            )
        );
        linesService = new LinesService(linesRepository);
    }

    @Test
    public void shouldReturnTrueIfLineIsPresent() {
        assertTrue(linesService.lineExists("line1"));
    }

    @Test
    public void shouldReturnFalseIfLineIsNotFound() {
        assertFalse(linesService.lineExists("line-not-existent"));
    }

    @Test
    public void shouldReturnListOfSearchedLines() {
        List<String> linesExpected = Lists.newArrayList("line2","line4");

        assertThat(linesService.findLineNamesByIds(Lists.newArrayList(2, 4)), is(linesExpected));
    }

    @Test
    public void shoudlReturnEmptyIfSeachIsEmpty() {
        assertTrue(linesService.findLineNamesByIds(Collections.emptyList()).isEmpty());
    }
}