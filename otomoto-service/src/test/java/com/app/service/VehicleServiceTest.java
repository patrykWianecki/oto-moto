package com.app.service;

import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.Generation.Mercedes.SClass;
import com.app.model.Model;
import com.app.model.Vehicle;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;
import com.app.repository.VehicleRepository;

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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
public class VehicleServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private VehicleService vehicleService;

  private static Vehicle vehicle = createVehicle();
  private static VehicleDto vehicleDto = createVehicleDto();

  @Test
  public void should_add_vehicle() {
    // given
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.just(vehicle));

    // when
    Mono<ResponseEntity<VehicleDto>> actualVehicle = vehicleService.addVehicle(vehicleDto);

    // then
    StepVerifier
        .create(actualVehicle)
        .expectNextMatches(VehicleServiceTest::isResponseValid)
        .expectComplete()
        .verify();
  }

  @Test
  public void should_return_bad_request_when_add_vehicle_fails() {
    // given
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.empty());

    // when
    Mono<ResponseEntity<VehicleDto>> actualResponse = vehicleService.addVehicle(vehicleDto);

    // then
    StepVerifier
        .create(actualResponse)
        .expectNextMatches(response -> BAD_REQUEST.equals(response.getStatusCode()))
        .expectComplete()
        .verify();
  }

  @Test
  public void should_update_vehicle() {
    // given
    when(vehicleRepository.findById(anyString())).thenReturn(Mono.just(vehicle));
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.just(vehicle));

    // when
    Mono<ResponseEntity<VehicleDto>> actualResponse
        = vehicleService.updateVehicle(VEHICLE_DTO_ID, vehicleDto);

    // then
    StepVerifier
        .create(actualResponse)
        .expectNextMatches(VehicleServiceTest::isResponseValid)
        .expectComplete()
        .verify();
  }

  @Test
  public void should_return_bad_request_when_update_vehicle_fails() {
    // given
    when(vehicleRepository.findById(anyString())).thenReturn(Mono.empty());
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.empty());

    // when
    Mono<ResponseEntity<VehicleDto>> actualResponse
        = vehicleService.updateVehicle(VEHICLE_DTO_ID, vehicleDto);

    // then
    StepVerifier
        .create(actualResponse)
        .expectNextMatches(response -> BAD_REQUEST.equals(response.getStatusCode()))
        .expectComplete()
        .verify();
  }

  @Test
  public void should_find_vehicle_by_id() {
    // given
    when(vehicleRepository.findById(anyString())).thenReturn(Mono.just(vehicle));

    // when
    Mono<ResponseEntity<VehicleDto>> actualResponse
        = vehicleService.findVehicleById(VEHICLE_DTO_ID);

    // then
    StepVerifier
        .create(actualResponse)
        .expectNextMatches(VehicleServiceTest::isResponseValid)
        .expectComplete()
        .verify();
  }

  @Test
  public void should_return_bad_request_when_find_vehicle_by_id_fails() {
    // given
    when(vehicleRepository.findById(anyString())).thenReturn(Mono.empty());

    // when
    Mono<ResponseEntity<VehicleDto>> actualResponse
        = vehicleService.findVehicleById(VEHICLE_DTO_ID);

    // then
    StepVerifier
        .create(actualResponse)
        .expectNextMatches(response -> BAD_REQUEST.equals(response.getStatusCode()))
        .expectComplete()
        .verify();
  }

  @Test
  public void should_find_all_vehicles() {
    // given
    when(vehicleRepository.findAll()).thenReturn(Flux.just(vehicle));

    // when
    Flux<VehicleDto> actualVehicles = vehicleService.findAllVehicles();

    // then
    StepVerifier
        .create(actualVehicles)
        .expectNextMatches(VehicleServiceTest::isVehicleValid)
        .expectComplete()
        .verify();
  }

  @Test
  public void should_remove_vehicle_by_id() {
    // given
    when(vehicleRepository.deleteById(anyString())).thenReturn(Mono.empty());

    // when
    Mono<ResponseEntity<Void>> actualResponse = vehicleService.removeVehicleById(VEHICLE_DTO_ID);

    // then
    StepVerifier
        .create(actualResponse)
        .verifyComplete();
  }

  @Test
  public void should_remove_all_vehicles() {
    // given
    when(vehicleRepository.deleteAll()).thenReturn(Mono.empty());

    // when
    Mono<ResponseEntity<Void>> actualResponse = vehicleService.removeAllVehicles();

    // then
    StepVerifier
        .create(actualResponse)
        .verifyComplete();
  }

  private static boolean isResponseValid(final ResponseEntity<VehicleDto> response) {
    VehicleDto vehicleDto = response.getBody();
    return Objects.nonNull(vehicleDto) && isVehicleValid(vehicleDto);
  }

  private static boolean isVehicleValid(VehicleDto vehicleDto) {
    return BLACK.equals(vehicleDto.getColour()) &&
        NEW.equals(vehicleDto.getCondition()) &&
        CURRENCY_PLN.equals(vehicleDto.getCurrency()) &&
        dateOfProduction.isEqual(vehicleDto.getDateOfProduction()) &&
        ALL_WHEEL_DRIVE.equals(vehicleDto.getDrive()) &&
        isEngineValid(vehicleDto.getEngineDto()) &&
        Set.of(ABS, ASR, ESP).containsAll(vehicleDto.getFeatures()) &&
        dateOfFirstRegistration.isEqual(vehicleDto.getFirstRegistration()) &&
        AUTOMATIC.equals(vehicleDto.getGearbox()) &&
        SClass.W222.name().equals(vehicleDto.getGeneration()) &&
        vehicleDto.isAccidentFree() &&
        !vehicleDto.isDamaged() &&
        !vehicleDto.isPriceNegotiable() &&
        LOCATION_WARSAW.equals(vehicleDto.getLocation()) &&
        MERCEDES.equals(vehicleDto.getMake()) &&
        MILEAGE == vehicleDto.getMileage() &&
        Model.Mercedes.SCLASS.name().equals(vehicleDto.getModel()) &&
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