package com.superheroes.api.controller;

import com.superheroes.controller.SuperHeroesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SuperHeroesController.class)
public class SuperHeroesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRequestShouldRetrieveOk() throws Exception {
            mockMvc.perform(get("/super-heroes").contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk());
    }
}
