package com.mobimeo.verspaetung.api.lines;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LinesControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void retrieveLines() throws Exception {
        mockMvc.perform(
                get("/lines")
                    .param("timestamp", "10:08:00")
                    .param("x", "2")
                    .param("y", "9")
            ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.vehicles", Matchers.containsInAnyOrder("200", "S75")));
    }

    @Test
    public void delayedLine() throws Exception {
        mockMvc.perform(get("/lines/M4"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.delayed", Matchers.is(true)));

    }

}