package com.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.app.service.ImporterService;

import static com.app.data.MockDataForTests.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ImporterControllerTest {

  @MockBean
  private ImporterService jsonImporterService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_add_voidodeships() throws Exception {
    // given
    when(jsonImporterService.createVoivodeships(anyString())).thenReturn(createVoivodeships());

    // when
    mockMvc
        .perform(
            post("/importer/voivodeships")
                .content(createVoivodeshipsJSON())
                .contentType(APPLICATION_JSON)
        )
        .andExpect(handler().methodName("createVoivodeships"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", equalTo(VOIVODESHIP_ID_1)))
        .andExpect(jsonPath("$[0].name", equalTo(VOIVODESHIP_NAME_1)))
        .andExpect(jsonPath("$[1].id", equalTo(VOIVODESHIP_ID_2)))
        .andExpect(jsonPath("$[1].name", equalTo(VOIVODESHIP_NAME_2)))
        .andReturn();

    // then
    verify(jsonImporterService, times(1)).createVoivodeships(anyString());
  }

  @Test
  void should_add_counties() throws Exception {
    // given
    when(jsonImporterService.createCounties(anyString())).thenReturn(createCounties());

    // when
    mockMvc
        .perform(
            post("/importer/counties")
                .content(createCountiesJSON())
                .contentType(APPLICATION_JSON)
        )
        .andExpect(handler().methodName("createCounties"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", equalTo(COUNTY_ID_1)))
        .andExpect(jsonPath("$[0].name", equalTo(COUNTY_NAME_1)))
        .andExpect(jsonPath("$[0].voivodeshipId", equalTo(VOIVODESHIP_ID_1)))
        .andExpect(jsonPath("$[1].id", equalTo(COUNTY_ID_2)))
        .andExpect(jsonPath("$[1].name", equalTo(COUNTY_NAME_2)))
        .andExpect(jsonPath("$[1].voivodeshipId", equalTo(VOIVODESHIP_ID_2)))
        .andReturn();

    // then
    verify(jsonImporterService, times(1)).createCounties(anyString());
  }

  @Test
  void should_add_localities() throws Exception {
    // given
    when(jsonImporterService.createLocalities(anyString())).thenReturn(createLocalities());

    // when
    mockMvc
        .perform(
            post("/importer/localities")
                .content(createLocalitiesJSON())
                .contentType(APPLICATION_JSON)
        )
        .andExpect(handler().methodName("createLocalities"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id", equalTo(LOCALITY_ID_1)))
        .andExpect(jsonPath("$[0].countyId", equalTo(COUNTY_ID_1)))
        .andExpect(jsonPath("$[0].distance", equalTo(LOCALITY_DISTANCE)))
        .andExpect(jsonPath("$[0].latitude", equalTo(LOCALITY_LATITUDE_1)))
        .andExpect(jsonPath("$[0].longitude", equalTo(LOCALITY_LONGITUDE_1)))
        .andExpect(jsonPath("$[0].name", equalTo(LOCALITY_NAME_1)))
        .andExpect(jsonPath("$[1].id", equalTo(LOCALITY_ID_2)))
        .andExpect(jsonPath("$[1].countyId", equalTo(COUNTY_ID_1)))
        .andExpect(jsonPath("$[1].distance", equalTo(LOCALITY_DISTANCE)))
        .andExpect(jsonPath("$[1].latitude", equalTo(LOCALITY_LATITUDE_2)))
        .andExpect(jsonPath("$[1].longitude", equalTo(LOCALITY_LONGITUDE_2)))
        .andExpect(jsonPath("$[1].name", equalTo(LOCALITY_NAME_2)))
        .andReturn();

    // then
    verify(jsonImporterService, times(1)).createLocalities(anyString());
  }
}