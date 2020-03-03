package com.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Vehicle;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;
import com.app.repository.VehicleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.app.data.MockData.*;
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

@SpringBootTest
@RunWith(SpringRunner.class)
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
        "PLN".equals(vehicleDto.getCurrency()) &&
        ALL_WHEEL_DRIVE.equals(vehicleDto.getDrive()) &&
        isEngineValid(vehicleDto.getEngineDto()) &&
        Set.of(ABS, ASR, ESP).containsAll(vehicleDto.getFeatures()) &&
        LocalDate.of(2020, 1, 1).isEqual(vehicleDto.getFirstRegistration()) &&
        AUTOMATIC.equals(vehicleDto.getGearbox()) &&
        vehicleDto.isAccidentFree() &&
        !vehicleDto.isDamaged() &&
        "Warsaw".equals(vehicleDto.getLocation()) &&
        BMW.equals(vehicleDto.getMake()) &&
        0 == vehicleDto.getMileage() &&
        "E-Class".equals(vehicleDto.getModel()) &&
        4 == vehicleDto.getNumberOfSeats() &&
        0 == vehicleDto.getNumberOfVehicleOwners() &&
        BigDecimal.valueOf(900000).equals(vehicleDto.getPrice()) &&
        SEDAN.equals(vehicleDto.getType());
  }

  private static boolean isEngineValid(final EngineDto engineDto) {
    return Objects.nonNull(engineDto) &&
        4.0 == engineDto.getCapacity() &&
        EURO_6.equals(engineDto.getEmmisionClass()) &&
        PETROL.equals(engineDto.getFuel()) &&
        15.0 == engineDto.getFuelConsumption() &&
        612 == engineDto.getPower();
  }
}