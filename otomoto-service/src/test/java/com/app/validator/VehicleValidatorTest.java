package com.app.validator;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;

import static com.app.data.MockDataForTests.*;
import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VehicleValidatorTest {

  @InjectMocks
  private VehicleValidator vehicleValidator;

  private VehicleDto vehicleDto = createVehicleDto();
  private EngineDto engineDto = createEngineDto();
  private Errors errors;

  @BeforeEach
  void setUp() {
    errors = new BeanPropertyBindingResult(vehicleDto, "validUser");
  }

  @Test
  void should_valid_vehicle() {
    // given

    // when + then
    vehicleValidator.validate(vehicleDto, errors);
  }

  @Test
  void should_throw_exception_when_colour_is_null() {
    // given
    vehicleDto.setColour(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle colour is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_condition_is_null() {
    // given
    vehicleDto.setCondition(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle condition does not exist", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_currency_is_null() {
    // given
    vehicleDto.setCurrency(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle currency is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_date_of_production_is_null() {
    // given
    vehicleDto.setDateOfProduction(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Production date has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_drive_is_null() {
    // given
    vehicleDto.setDrive(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle drive does not exist", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_engine_is_null() {
    // given
    vehicleDto.setEngineDto(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Missing vehicle engine", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_engine_capacity_is_less_than_zero() {
    // given
    engineDto.setCapacity(-1.0D);
    vehicleDto.setEngineDto(engineDto);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Engine capacity has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_engine_emission_class_is_null() {
    // given
    engineDto.setEmmisionClass(null);
    vehicleDto.setEngineDto(engineDto);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Emission class does not exist", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_engine_fuel_is_null() {
    // given
    engineDto.setFuel(null);
    vehicleDto.setEngineDto(engineDto);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Fuel type does not exist", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_engine_fuel_consumption_is_less_than_zero() {
    // given
    engineDto.setFuelConsumption(-1.0D);
    vehicleDto.setEngineDto(engineDto);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Engine fuel consumption has incorrect value",
        illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_engine_power_is_less_than_one() {
    // given
    engineDto.setPower(0);
    vehicleDto.setEngineDto(engineDto);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Engine power has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_features_are_null() {
    // given
    vehicleDto.setFeatures(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle has no features", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_features_are_empty() {
    // given
    vehicleDto.setFeatures(Collections.emptySet());

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle has no features", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_first_registration_is_null() {
    // given
    vehicleDto.setFirstRegistration(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("First registration date has incorrect value",
        illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_first_registration_is_before_date_of_production() {
    // given
    vehicleDto.setFirstRegistration(vehicleDto.getDateOfProduction().minus(1, DAYS));

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("First registration date has incorrect value",
        illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_gearbox_is_null() {
    // given
    vehicleDto.setGearbox(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle gearbox is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_generation_is_null() {
    // given
    vehicleDto.setGeneration(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Generation is not valid", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_generation_is_blank() {
    // given
    vehicleDto.setGeneration(BLANK);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Generation is not valid", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_generation_has_incorrect_value() {
    // given
    vehicleDto.setGeneration("Wrong generation");

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Given generation does not exist", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_location_is_null() {
    // given
    vehicleDto.setLocation(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle location is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_location_is_blank() {
    // given
    vehicleDto.setLocation(BLANK);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle location is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_make_is_null() {
    // given
    vehicleDto.setMake(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle make is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_mileage_is_less_than_zero() {
    // given
    vehicleDto.setMileage(-1L);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Mileage has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_model_is_null() {
    // given
    vehicleDto.setModel(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle model is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_model_is_blank() {
    // given
    vehicleDto.setModel(BLANK);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle model is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_model_has_incorrect_value() {
    // given
    vehicleDto.setModel("Wrong model");

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Given model does not exist", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_number_of_seats_is_less_than_zero() {
    // given
    vehicleDto.setNumberOfSeats(0);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Number of seats has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_number_of_vehicle_owners_is_less_than_zero() {
    // given
    vehicleDto.setNumberOfVehicleOwners(-1);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Number of vehicle owners has incorrect value",
        illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_price_is_null() {
    // given
    vehicleDto.setPrice(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Price has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_price_is_less_than_zero() {
    // given
    vehicleDto.setPrice(BigDecimal.valueOf(-1L));

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Price has incorrect value", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_type_is_null() {
    // given
    vehicleDto.setType(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_vin_is_null() {
    // given
    vehicleDto.setVin(null);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle vin is missing", illegalArgumentException.getMessage());
  }

  @Test
  void should_throw_exception_when_vin_is_blank() {
    // given
    vehicleDto.setVin(BLANK);

    // when
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));

    // then
    assertEquals("Vehicle vin is missing", illegalArgumentException.getMessage());
  }
}