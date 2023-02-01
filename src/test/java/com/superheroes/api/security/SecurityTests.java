package com.superheroes.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superheroes.security.AuthenticationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postLoginRequestShouldRetrieveOk() throws Exception {

        var obj = new AuthenticationRequest("ldoglioli", "luciano");
        var requestJson = new ObjectMapper().writeValueAsString(obj);
        mockMvc.perform(post("/login/authenticate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))
                    .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void getRequestShouldRetrieveUnauthorized() throws Exception {
            mockMvc.perform(get("/super-heroes").contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("ldoglioli")
    public void getRequestShouldRetrieveOk() throws Exception {
        mockMvc.perform(get("/super-heroes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
