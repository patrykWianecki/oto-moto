package com.app.service;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.configuration.JacksonConfiguration;
import com.app.model.EngineDto;
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
import static com.app.model.Drive.*;
import static com.app.model.EmmisionClass.*;
import static com.app.model.Feature.*;
import static com.app.model.Fuel.*;
import static com.app.model.Gearbox.*;
import static com.app.model.Make.*;
import static com.app.model.Type.*;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

public class VehicleServiceTest {

  private static final int OK_STATUS_CODE = 200;
  private static final VehicleDto vehicleDto = createVehicleDto();

  private final String PATH_VEHICLES = "/vehicles";
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
            isVehicleValid(Objects.requireNonNull(vehicleResponse.getBody()))
        );
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
            isVehicleValid(Objects.requireNonNull(vehicleResponse.getBody()))
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
            isVehicleValid(Objects.requireNonNull(vehicleResponse.getBody()))
        );
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
        .expectNextMatches(
            vehicleResponse -> isVehicleValid(Objects.requireNonNull(vehicleResponse))
        );
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

  private static boolean isVehicleValid(VehicleDto vehicleDto) {
    return VEHICLE_DTO_ID.equals(vehicleDto.getId()) &&
        BLACK.equals(vehicleDto.getColour()) &&
        NEW.equals(vehicleDto.getCondition()) &&
        CURRENCY_PLN.equals(vehicleDto.getCurrency()) &&
        DATE_OF_PRODUCTION.isEqual(vehicleDto.getDateOfProduction()) &&
        ALL_WHEEL_DRIVE.equals(vehicleDto.getDrive()) &&
        isEngineValid(vehicleDto.getEngineDto()) &&
        DATE_OF_FIRST_REGISTRATION.isEqual(vehicleDto.getFirstRegistration()) &&
        Set.of(ABS, ASR, ESP).containsAll(vehicleDto.getFeatures()) &&
        AUTOMATIC.equals(vehicleDto.getGearbox()) &&
        A_6.C8.name().equals(vehicleDto.getGeneration()) &&
        vehicleDto.isAccidentFree() &&
        !vehicleDto.isDamaged() &&
        !vehicleDto.isPriceNegotiable() &&
        LOCATION_WARSAW.equals(vehicleDto.getLocation()) &&
        AUDI.equals(vehicleDto.getMake()) &&
        MILEAGE == vehicleDto.getMileage() &&
        Audi.A_6.name().equals(vehicleDto.getModel()) &&
        NUMBER_OF_SEATS == vehicleDto.getNumberOfSeats() &&
        NUMBER_OF_VEHICLE_OWNERS == vehicleDto.getNumberOfVehicleOwners() &&
        PRICE.equals(vehicleDto.getPrice()) &&
        SEDAN.equals(vehicleDto.getType()) &&
        VIN.equals(vehicleDto.getVin());
  }

  private static boolean isEngineValid(final EngineDto engineDto) {
    return Objects.nonNull(engineDto) &&
        CAPACITY == engineDto.getCapacity() &&
        EURO_6.equals(engineDto.getEmmisionClass()) &&
        PETROL.equals(engineDto.getFuel()) &&
        FUEL_CONSUMPTION == engineDto.getFuelConsumption() &&
        POWER == engineDto.getPower();
  }
}