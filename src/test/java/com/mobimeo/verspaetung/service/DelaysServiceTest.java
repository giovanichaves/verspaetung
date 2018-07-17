package com.mobimeo.verspaetung.service;

import com.google.common.collect.ImmutableList;
import com.mobimeo.verspaetung.model.Delay;
import com.mobimeo.verspaetung.repository.DelaysRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DelaysServiceTest {

    private DelaysService delayService;

    @Before
    public void setUp() {
        DelaysRepository repository = mock(DelaysRepository.class);
        when(repository.getData()).thenReturn(
            ImmutableList.of(
                new Delay("line0", 0),
                new Delay("line1", 1),
                new Delay("line2", 2)
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