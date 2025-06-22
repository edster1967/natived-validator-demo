package com.natived.nativedvalidatordemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natived.nativedvalidatordemo.dto.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DemoController.class)
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private ClientRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new ClientRequest();
        validRequest.setUserName("john");
        validRequest.setRole("user");
        validRequest.setAppId("app123");
        validRequest.setLocation("NY");
    }

    @Test
    void validRequestReturnsOk() throws Exception {
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").exists())
                .andExpect(jsonPath("$.status").value("active"));
    }

    @Test
    void missingRequiredFieldReturnsBadRequest() throws Exception {
        validRequest.setUserName("");
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void adminRoleWithInvalidSpeciesReturnsBadRequest() throws Exception {
        validRequest.setRole("admin");
        validRequest.setSpecies("cat");
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void adminRoleWithValidSpeciesReturnsOk() throws Exception {
        validRequest.setRole("admin");
        validRequest.setSpecies("puppy");
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").exists());
    }
}

