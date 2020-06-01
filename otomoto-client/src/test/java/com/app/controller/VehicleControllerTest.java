package com.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.app.model.VehicleDto;
import com.app.service.VehicleService;
import com.app.validator.VehicleValidator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.app.data.MockDataForTests.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = VehicleController.class)
class VehicleControllerTest {

  private static final String VEHICLES_MAPPING = "/vehicles";
  private static final String ID_QUERY = "vehicleId";
  private static final String ALL = "/all";

  @MockBean
  private VehicleValidator vehicleValidator;
  @MockBean
  private VehicleService vehicleService;

  @Autowired
  private WebTestClient webTestClient;

  private final VehicleDto vehicleDto = createVehicleDto();

  @BeforeEach
  public void setUp() {
    when(vehicleValidator.supports(eq(VehicleDto.class))).thenReturn(true);
  }

  @Test
  void should_find_all_vehicles() {
    // given
    when(vehicleService.findAllVehicles()).thenReturn(Flux.just(vehicleDto));

    // when
    webTestClient
        .get()
        .uri(VEHICLES_MAPPING + ALL)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json("[" + createValidResponse() + "]");

    // then
    verify(vehicleService, times(1)).findAllVehicles();
  }

  @Test
  void should_find_vehicle_by_id() {
    // given
    when(vehicleService.findVehicleById(anyString()))
        .thenReturn(Mono.just(ResponseEntity.ok(vehicleDto)));

    // when
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path(VEHICLES_MAPPING)
            .queryParam(ID_QUERY, VEHICLE_DTO_ID)
            .build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(createValidResponse());

    // then
    verify(vehicleService, times(1)).findVehicleById(anyString());
  }

  @Test
  void should_add_new_vehicle() {
    // given
    when(vehicleService.addVehicle(any(VehicleDto.class)))
        .thenReturn(Mono.just(ResponseEntity.ok(vehicleDto)));

    // when
    webTestClient
        .post()
        .uri(VEHICLES_MAPPING)
        .body(BodyInserters.fromValue(vehicleDto))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(createValidResponse());

    // then
    verify(vehicleService, times(1)).addVehicle(any(VehicleDto.class));
  }

  @Test
  void should_update_existing_vehicle() {
    // given
    when(vehicleService.updateVehicle(anyString(), any(VehicleDto.class)))
        .thenReturn(Mono.just(ResponseEntity.ok(vehicleDto)));

    // when
    webTestClient
        .put()
        .uri(uriBuilder -> uriBuilder
            .path(VEHICLES_MAPPING)
            .queryParam(ID_QUERY, VEHICLE_DTO_ID)
            .build())
        .body(BodyInserters.fromValue(vehicleDto))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody();

    // then
    verify(vehicleService, times(1))
        .updateVehicle(anyString(), any(VehicleDto.class));
  }

  @Test
  void should_remove_vehicle_by_id() {
    // given
    when(vehicleService.removeVehicleById(anyString())).thenReturn(Mono.empty());

    // when
    webTestClient
        .delete()
        .uri(uriBuilder -> uriBuilder
            .path(VEHICLES_MAPPING)
            .queryParam(ID_QUERY, VEHICLE_DTO_ID)
            .build())
        .exchange()
        .expectStatus()
        .isOk();

    // then
    verify(vehicleService, times(1)).removeVehicleById(anyString());
  }

  @Test
  void should_remove_all_vehicles() {
    // given
    when(vehicleService.removeAllVehicles()).thenReturn(Mono.empty());

    // when
    webTestClient
        .delete()
        .uri(VEHICLES_MAPPING + ALL)
        .exchange()
        .expectStatus()
        .isOk();

    // then
    verify(vehicleService, times(1)).removeAllVehicles();
  }
}