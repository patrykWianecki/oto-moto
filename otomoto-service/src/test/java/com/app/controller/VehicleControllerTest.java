package com.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.app.model.Vehicle;
import com.app.model.dto.VehicleDto;
import com.app.repository.VehicleRepository;
import com.app.service.VehicleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.app.data.MockData.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = VehicleController.class)
@Import(VehicleService.class)
class VehicleControllerTest {

  @MockBean
  private VehicleRepository vehicleRepository;

  @Autowired
  private WebTestClient webTestClient;

  private Vehicle vehicle = createVehicle();
  private VehicleDto vehicleDto = createVehicleDto();

  @Test
  void should_find_all_vehicles() {
    // given
    when(vehicleRepository.findAll()).thenReturn(Flux.just(vehicle));

    // when
    webTestClient
        .get()
        .uri("/vehicles/all")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json("[" + createValidResponse() + "]");

    // then
    verify(vehicleRepository, times(1)).findAll();
  }

  @Test
  void should_find_vehicle_by_id() {
    // given
    when(vehicleRepository.findById(anyString())).thenReturn(Mono.just(vehicle));

    // when
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/vehicles")
            .queryParam("vehicleId", "71623v12hvk1j2")
            .build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(createValidResponse());

    // then
    verify(vehicleRepository, times(1)).findById(anyString());
  }

  @Test
  void should_add_new_vehicle() {
    // given
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.just(vehicle));

    // when
    webTestClient
        .post()
        .uri("/vehicles")
        .body(BodyInserters.fromValue(vehicleDto))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(createValidResponse());

    // then
    verify(vehicleRepository, times(1)).save(any(Vehicle.class));
  }

  @Test
  void should_update_existing_vehicle() {
    // given
    when(vehicleRepository.findById(anyString())).thenReturn(Mono.just(vehicle));
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.just(vehicle));

    // when
    webTestClient
        .put()
        .uri(uriBuilder -> uriBuilder
            .path("/vehicles")
            .queryParam("vehicleId", "71623v12hvk1j2")
            .build())
        .body(BodyInserters.fromValue(vehicleDto))
        .exchange()
        //          .expectStatus()
        //          .isOk()
        .expectBody();

    // then
    verify(vehicleRepository, times(1)).findById(anyString());
    verify(vehicleRepository, times(1)).save(any(Vehicle.class));
  }

  @Test
  void should_remove_vehicle_by_id() {
    // given
    when(vehicleRepository.deleteById(anyString())).thenReturn(Mono.empty());

    // when
    webTestClient
        .delete()
        .uri(uriBuilder -> uriBuilder
            .path("/vehicles")
            .queryParam("vehicleId", "71623v12hvk1j2")
            .build())
        .exchange()
        .expectStatus()
        .isOk();

    // then
    verify(vehicleRepository, times(1)).deleteById(anyString());
  }

  @Test
  void should_remove_all_vehicles() {
    // given
    when(vehicleRepository.deleteAll()).thenReturn(Mono.empty());

    // when
    webTestClient
        .delete()
        .uri("/vehicles/all")
        .exchange()
        .expectStatus()
        .isOk();

    // then
    verify(vehicleRepository, times(1)).deleteAll();
  }

  private static String createValidResponse() {
    return """
        {
          "id": "71623v12hvk1j2",
          "colour": "BLACK",
          "condition": "NEW",
          "currency": "PLN",
          "drive": "ALL_WHEEL_DRIVE",
          "features": [
            "ASR",
            "ESP",
            "ABS"
          ],
          "firstRegistration": "2020-01-01",
          "engineDto": {
            "capacity": 4.0,
            "emmisionClass": "EURO_6",
            "fuel": "PETROL",
            "fuelConsumption": 15.0,
            "power": 612
          },
          "gearbox": "AUTOMATIC",
          "location": "Warsaw",
          "make": "BMW",
          "mileage": 0,
          "model": "E-Class",
          "numberOfSeats": 4,
          "numberOfVehicleOwners": 0,
          "price": 900000,
          "type": "SEDAN",
          "accidentFree": true,
          "damaged": false
        }""";
  }
}