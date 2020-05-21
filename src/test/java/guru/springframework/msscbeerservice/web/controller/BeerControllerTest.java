package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(BeerController.BASE_URL + "/" + UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        BeerDto beerDto = BeerDto.builder().id(UUID.randomUUID()).build();
        mockMvc.perform(post(BeerController.BASE_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        BeerDto beerDto = BeerDto.builder().build();
        mockMvc.perform(put(BeerController.BASE_URL + "/" + UUID.randomUUID())
                    .content(objectMapper.writeValueAsString(beerDto))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}