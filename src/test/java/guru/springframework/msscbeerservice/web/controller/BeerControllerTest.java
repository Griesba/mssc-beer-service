package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {

        BDDMockito.given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

        mockMvc.perform(get(BeerController.BASE_URL + "/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        BeerDto beerDto = getValidBeerDto();

        BDDMockito.given(beerService.saveNewBeer(any())).willReturn(beerDto);

        mockMvc.perform(post(BeerController.BASE_URL + "/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        BeerDto beerDto = getValidBeerDto();

        BDDMockito.given(beerService.update(any(), any()))
                .willReturn(getValidBeerDto());

        mockMvc.perform(put(BeerController.BASE_URL + "/beer/" + UUID.randomUUID())
                .content(objectMapper.writeValueAsString(beerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc("0631234200036")
                .build();
    }
}