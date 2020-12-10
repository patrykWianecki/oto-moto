package com.app.service;

import java.util.List;
import java.util.Optional;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
class VehicleServiceTest {

  @Mock
  private VehicleRepository vehicleRepository;

  @InjectMocks
  private VehicleService vehicleService;

  private final Vehicle vehicle = createVehicle();
  private final VehicleDto vehicleDto = createVehicleDto();

  @Test
  void should_add_vehicle() {
    // given
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

    // when
    ResponseEntity<VehicleDto> response = vehicleService.addVehicle(vehicleDto);

    // then
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertVehicle(response.getBody());

    verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_update_vehicle() {
    // given
    when(vehicleRepository.findById(anyLong())).thenReturn(Optional.of(vehicle));
    when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

    // when
    ResponseEntity<VehicleDto> response = vehicleService.updateVehicle(VEHICLE_ID, vehicleDto);

    // then
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertVehicle(response.getBody());

    verify(vehicleRepository, times(1)).findById(anyLong());
    verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_return_not_found_status_when_vehicle_with_id_does_not_exist() {
    // given
    when(vehicleRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    ResponseEntity<VehicleDto> response = vehicleService.updateVehicle(VEHICLE_ID, vehicleDto);

    // then
    assertNotNull(response);
    assertEquals(NOT_FOUND, response.getStatusCode());

    verify(vehicleRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_find_vehicle_by_id() {
    // given
    when(vehicleRepository.findById(anyLong())).thenReturn(Optional.of(vehicle));

    // when
    ResponseEntity<VehicleDto> response = vehicleService.findVehicleById(VEHICLE_ID);

    // then
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertVehicle(response.getBody());

    verify(vehicleRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_return_not_found_status_when_vehicle_with_given_id_does_not_exist() {
    // given
    when(vehicleRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    ResponseEntity<VehicleDto> response = vehicleService.findVehicleById(VEHICLE_ID);

    // then
    assertNotNull(response);
    assertEquals(NOT_FOUND, response.getStatusCode());

    verify(vehicleRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_find_all_vehicles() {
    // given
    when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

    // when
    List<VehicleDto> vehicles = vehicleService.findAllVehicles();

    // then
    assertNotNull(vehicles);
    assertEquals(1, vehicles.size());
    assertVehicle(vehicles.get(0));

    verify(vehicleRepository, times(1)).findAll();
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_remove_vehicle_by_id() {
    // given
    when(vehicleRepository.deleteVehicleById(anyLong())).thenReturn(Optional.of(vehicle));

    // when
    ResponseEntity<VehicleDto> response = vehicleService.removeVehicleById(VEHICLE_ID);

    // then
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());
    assertVehicle(response.getBody());

    verify(vehicleRepository, times(1)).deleteVehicleById(anyLong());
    verifyNoMoreInteractions(vehicleRepository);
  }

  @Test
  void should_return_not_found_status_when_vehicle_with_id_to_remove_does_not_exist() {
    // given
    when(vehicleRepository.deleteVehicleById(anyLong())).thenReturn(Optional.empty());

    // when
    ResponseEntity<VehicleDto> response = vehicleService.removeVehicleById(VEHICLE_ID);

    // then
    assertNotNull(response);
    assertEquals(NOT_FOUND, response.getStatusCode());

    verify(vehicleRepository, times(1)).deleteVehicleById(anyLong());
    verifyNoMoreInteractions(vehicleRepository);
  }

  private static void assertVehicle(VehicleDto vehicleDto) {
    assertNotNull(vehicleDto);
    assertEquals(VEHICLE_ID, vehicleDto.getId());
    assertEquals(BLACK, vehicleDto.getColour());
    assertEquals(NEW, vehicleDto.getCondition());
    assertEquals(CURRENCY_PLN, vehicleDto.getCurrency());
    assertEquals(dateOfProduction, vehicleDto.getDateOfProduction());
    assertEquals(ALL_WHEEL_DRIVE, vehicleDto.getDrive());

    assertEngine(vehicleDto.getEngineDto());

    assertTrue(Set.of(ABS, ASR, ESP).containsAll(vehicleDto.getFeatures()));
    assertEquals(dateOfFirstRegistration, vehicleDto.getFirstRegistration());
    assertEquals(AUTOMATIC, vehicleDto.getGearbox());
    assertEquals(SClass.W222.name(), vehicleDto.getGeneration());
    assertTrue(vehicleDto.isAccidentFree());
    assertFalse(vehicleDto.isDamaged());
    assertFalse(vehicleDto.isPriceNegotiable());
    assertEquals(LOCATION_WARSAW, vehicleDto.getLocation());
    assertEquals(MERCEDES, vehicleDto.getMake());
    assertEquals(MILEAGE, vehicleDto.getMileage());
    assertEquals(Model.Mercedes.SCLASS.name(), vehicleDto.getModel());
    assertEquals(NUMBER_OF_SEATS, vehicleDto.getNumberOfSeats());
    assertEquals(NUMBER_OF_VEHICLE_OWNERS, vehicleDto.getNumberOfVehicleOwners());
    assertEquals(PRICE, vehicleDto.getPrice());
    assertEquals(SEDAN, vehicleDto.getType());
    assertEquals(VIN, vehicleDto.getVin());
  }

  private static void assertEngine(EngineDto engineDto) {
    assertNotNull(engineDto);
    assertEquals(ENGINE_ID, engineDto.getId());
    assertEquals(CAPACITY, engineDto.getCapacity());
    assertEquals(EURO_6, engineDto.getEmmisionClass());
    assertEquals(PETROL, engineDto.getFuel());
    assertEquals(FUEL_CONSUMPTION, engineDto.getFuelConsumption());
    assertEquals(POWER, engineDto.getPower());
  }
}