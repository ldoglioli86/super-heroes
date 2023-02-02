package com.superheroes.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SuperHeroesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("ldoglioli")
    public void getRequestShouldRetrieveOk() throws Exception {
            mockMvc.perform(get("/super-heroes").contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk());
    }
}
