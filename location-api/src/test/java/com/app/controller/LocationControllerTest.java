package com.app.controller;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.app.model.LocationResponse;
import com.app.service.LocationService;
import com.app.validator.LocationValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.app.data.MockDataForTests.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class LocationControllerTest {

  @MockBean
  private LocationService locationService;
  @MockBean
  private LocationValidator locationValidator;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_get_valid_currency_response() throws Exception {
    // given
    when(locationService.createRequest(any(LocationResponse.class))).thenReturn(createLocalitiesDto());
    doNothing().when(locationValidator).validateLocationResponse(any());

    // when + then
    mockMvc
        .perform(
            get("/localities")
                .content(toJson(createValidLocationResponse()))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(handler().methodName("getAvailableLocalities"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", equalTo("11-11-11")))
        .andExpect(jsonPath("$[0].name", equalTo(STALOWA_WOLA_LOCALITY)))
        .andExpect(jsonPath("$[0].countyId", equalTo("11-11")))
        .andExpect(jsonPath("$[0].longitude", equalTo(10.0)))
        .andExpect(jsonPath("$[0].latitude", equalTo(10.0)))
        .andExpect(jsonPath("$[0].distance", equalTo(0)))
        .andExpect(jsonPath("$[1].id", equalTo("11-11-12")))
        .andExpect(jsonPath("$[1].name", equalTo(PYSZNICA_LOCALITY)))
        .andExpect(jsonPath("$[1].countyId", equalTo("11-11")))
        .andExpect(jsonPath("$[1].longitude", equalTo(10.1)))
        .andExpect(jsonPath("$[1].latitude", equalTo(10.1)))
        .andExpect(jsonPath("$[1].distance", equalTo(16)))
        .andExpect(jsonPath("$[2].id", equalTo("11-11-13")))
        .andExpect(jsonPath("$[2].name", equalTo(ZAKLIKOW_LOCALITY)))
        .andExpect(jsonPath("$[2].countyId", equalTo("11-11")))
        .andExpect(jsonPath("$[2].longitude", equalTo(10.11)))
        .andExpect(jsonPath("$[2].latitude", equalTo(10.11)))
        .andExpect(jsonPath("$[2].distance", equalTo(17)))
        .andReturn();
  }

  @Test
  void should_get_no_content_response() throws Exception {
    // given
    when(locationService.createRequest(any(LocationResponse.class))).thenReturn(Collections.emptyList());
    doNothing().when(locationValidator).validateLocationResponse(any());

    // when + then
    mockMvc
        .perform(
            get("/localities")
                .content(toJson(createValidLocationResponse()))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(handler().methodName("getAvailableLocalities"))
        .andExpect(status().isNoContent());
  }

  private static String toJson(LocationResponse locationResponse) {
    try {
      return new ObjectMapper().writeValueAsString(locationResponse);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Failed to create json object", e);
    }
  }
}