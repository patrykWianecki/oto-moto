package com.app.service;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.configuration.JacksonConfiguration;
import com.app.model.Generation.Audi.A_6;
import com.app.model.Model.Audi;
import com.app.model.VehicleDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.app.data.MockDataForTests.*;
import static com.app.model.Colour.*;
import static com.app.model.Condition.*;
import static com.app.model.Gearbox.*;
import static com.app.model.Make.*;
import static com.app.model.Type.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

public class VehicleServiceTest {

  private static final int OK_STATUS_CODE = 200;
  private static final VehicleDto vehicleDto = createVehicleDto();

  private final String PATH_VEHICLES = "/vehicles";
  private final String PATH_VEHICLE_ID = "?vehicleId=" + VEHICLE_DTO_ID;
  private final String PATH_ALL = "/all";
  private final MockWebServer mockWebServer = new MockWebServer();
  private final String PATH = "http://localhost:" + mockWebServer.getPort() + PATH_VEHICLES;
  private final JacksonConfiguration jacksonConfiguration = new JacksonConfiguration();

  @AfterEach
  void tearDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  public void should_add_vehicle() throws JsonProcessingException {
    // given
    VehicleService vehicleService = new VehicleService(
        WebClient.create(mockWebServer.url(PATH).toString())
    );
    mockWebServer.enqueue(createMockResponseWithBody());

    // when
    Mono<ResponseEntity<VehicleDto>> response = vehicleService.addVehicle(createVehicleDto());

    // then
    StepVerifier.create(response)
        .expectNextMatches(vehicleResponse -> OK.equals(vehicleResponse.getStatusCode()) &&
            vehicleDto.equals(vehicleResponse.getBody()));
  }

  @Test
  public void should_update_vehicle() throws JsonProcessingException {
    // given
    VehicleService vehicleService = new VehicleService(
        WebClient.create(mockWebServer.url(PATH).toString())
    );
    mockWebServer.enqueue(createMockResponseWithBody());

    // when
    Mono<ResponseEntity<VehicleDto>> response =
        vehicleService.updateVehicle(VEHICLE_DTO_ID, createVehicleDto());

    // then
    StepVerifier
        .create(response)
        .expectNextMatches(vehicleResponse -> OK.equals(vehicleResponse.getStatusCode()) &&
            vehicleDto.equals(vehicleResponse.getBody())
        );
  }

  @Test
  public void should_find_vehicle_by_id() throws JsonProcessingException {
    // given
    VehicleService vehicleService = new VehicleService(
        WebClient.create(mockWebServer.url(PATH).toString())
    );
    mockWebServer.enqueue(createMockResponseWithBody());

    // when
    Mono<ResponseEntity<VehicleDto>> response = vehicleService.findVehicleById(VEHICLE_DTO_ID);

    // then
    StepVerifier
        .create(response)
        .expectNextMatches(vehicleResponse -> OK.equals(vehicleResponse.getStatusCode()) &&
            vehicleDto.equals(vehicleResponse.getBody()));
  }

  @Test
  public void should_find_all_vehicles() throws JsonProcessingException {
    // given
    VehicleService vehicleService = new VehicleService(
        WebClient.create(mockWebServer.url(PATH).toString())
    );
    mockWebServer.enqueue(
        new MockResponse()
            .setResponseCode(OK_STATUS_CODE)
            .setHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .setBody("[" + jacksonConfiguration.objectMapper().writeValueAsString(vehicleDto) + "]")
    );

    // when
    Flux<VehicleDto> response = vehicleService.findAllVehicles();

    // then
    StepVerifier
        .create(response)
        .expectNextMatches(vehicleDto::equals);
  }

  @Test
  public void should_remove_vehicle_by_id() {
    // given
    VehicleService vehicleService = new VehicleService(
        WebClient.create(mockWebServer.url(PATH).toString())
    );
    mockWebServer.enqueue(createMockResponseWithoutBody());

    // when
    Mono<ResponseEntity<Void>> response = vehicleService.removeVehicleById(VEHICLE_DTO_ID);

    // then
    StepVerifier
        .create(response)
        .expectNextMatches(vehicleResponse -> OK.equals(vehicleResponse.getStatusCode()));
  }

  @Test
  public void should_remove_all_vehicles() {
    // given
    VehicleService vehicleService = new VehicleService(
        WebClient.create(mockWebServer.url(PATH).toString())
    );
    mockWebServer.enqueue(createMockResponseWithoutBody());

    // when
    Mono<ResponseEntity<Void>> response = vehicleService.removeAllVehicles();

    // then
    StepVerifier.create(response)
        .expectNextMatches(vehicleResponse -> OK.equals(vehicleResponse.getStatusCode()));
  }

  private MockResponse createMockResponseWithBody() throws JsonProcessingException {
    return new MockResponse()
        .setResponseCode(OK_STATUS_CODE)
        .setHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        .setBody(jacksonConfiguration.objectMapper().writeValueAsString(vehicleDto));
  }

  private static MockResponse createMockResponseWithoutBody() {
    return new MockResponse()
        .setResponseCode(OK_STATUS_CODE)
        .setHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE);
  }

  private static void assertVehicle(VehicleDto vehicleDto) {
    assertNotNull(vehicleDto);
    assertEquals(VEHICLE_DTO_ID, vehicleDto.getId());
    assertEquals(BLACK, vehicleDto.getColour());
    assertEquals(NEW, vehicleDto.getCondition());
    assertEquals(CURRENCY_PLN, vehicleDto.getCurrency());
    assertEquals(DATE_OF_PRODUCTION, vehicleDto.getDateOfProduction());
    assertEquals(DATE_OF_FIRST_REGISTRATION, vehicleDto.getFirstRegistration());
    assertEquals(AUTOMATIC, vehicleDto.getGearbox());
    assertEquals(A_6.C8.name(), vehicleDto.getGeneration());
    assertTrue(vehicleDto.isAccidentFree());
    assertFalse(vehicleDto.isDamaged());
    assertFalse(vehicleDto.isPriceNegotiable());
    assertEquals(LOCATION_WARSAW, vehicleDto.getLocation());
    assertEquals(AUDI, vehicleDto.getMake());
    assertEquals(MILEAGE, vehicleDto.getMileage());
    assertEquals(Audi.A_6.name(), vehicleDto.getModel());
    assertEquals(NUMBER_OF_SEATS, vehicleDto.getNumberOfSeats());
    assertEquals(NUMBER_OF_VEHICLE_OWNERS, vehicleDto.getNumberOfVehicleOwners());
    assertEquals(PRICE, vehicleDto.getPrice());
    assertEquals(SEDAN, vehicleDto.getType());
    assertEquals(VIN, vehicleDto.getVin());
  }
}