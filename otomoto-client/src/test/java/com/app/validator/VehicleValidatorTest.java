package com.app.validator;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.app.model.EngineDto;
import com.app.model.VehicleDto;

import static com.app.data.MockDataForTests.*;
import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VehicleValidatorTest {

  @InjectMocks
  private VehicleValidator vehicleValidator;

  private VehicleDto vehicleDto = createVehicleDto();
  private EngineDto engineDto = createEngineDto();

  @Test
  void should_valid_vehicle() {
    // given
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    vehicleValidator.validate(vehicleDto, errors);
  }

  @Test
  void should_throw_exception_when_colour_is_null() {
    // given
    vehicleDto.setColour(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_condition_is_null() {
    // given
    vehicleDto.setCondition(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_currency_is_null() {
    // given
    vehicleDto.setCurrency(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_date_of_production_is_null() {
    // given
    vehicleDto.setDateOfProduction(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_drive_is_null() {
    // given
    vehicleDto.setDrive(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_is_null() {
    // given
    vehicleDto.setEngineDto(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_capacity_is_null() {
    // given
    engineDto.setCapacity(null);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_capacity_is_less_than_zero() {
    // given
    engineDto.setCapacity(-1.0D);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_emission_class_is_null() {
    // given
    engineDto.setEmmisionClass(null);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_fuel_is_null() {
    // given
    engineDto.setFuel(null);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_fuel_consumption_is_null() {
    // given
    engineDto.setFuelConsumption(null);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_fuel_consumption_is_less_than_zero() {
    // given
    engineDto.setFuelConsumption(-1.0D);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_power_is_null() {
    // given
    engineDto.setPower(null);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_engine_power_is_less_than_one() {
    // given
    engineDto.setPower(0);
    vehicleDto.setEngineDto(engineDto);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_features_are_null() {
    // given
    vehicleDto.setFeatures(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_features_are_empty() {
    // given
    vehicleDto.setFeatures(Set.of());
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_first_registration_is_null() {
    // given
    vehicleDto.setFirstRegistration(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_first_registration_is_before_date_of_production() {
    // given
    vehicleDto.setFirstRegistration(vehicleDto.getDateOfProduction().minus(1, DAYS));
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_gearbox_is_null() {
    // given
    vehicleDto.setGearbox(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_generation_is_null() {
    // given
    vehicleDto.setGeneration(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_generation_is_empty() {
    // given
    vehicleDto.setGeneration("");
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_generation_has_incorrect_value() {
    // given
    vehicleDto.setGeneration("Wrong generation");
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_location_is_null() {
    // given
    vehicleDto.setLocation(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_location_is_empty() {
    // given
    vehicleDto.setLocation("");
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_make_is_null() {
    // given
    vehicleDto.setMake(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_mileage_is_null() {
    // given
    vehicleDto.setMileage(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_mileage_is_less_than_zero() {
    // given
    vehicleDto.setMileage(-1L);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_model_is_null() {
    // given
    vehicleDto.setModel(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_model_is_empty() {
    // given
    vehicleDto.setModel("");
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_model_has_incorrect_value() {
    // given
    vehicleDto.setModel("Wrong model");
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_number_of_seats_is_null() {
    // given
    vehicleDto.setNumberOfSeats(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_number_of_seats_is_less_than_zero() {
    // given
    vehicleDto.setNumberOfSeats(0);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_number_of_vehicle_owners_is_null() {
    // given
    vehicleDto.setNumberOfVehicleOwners(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_number_of_vehicle_owners_is_less_than_zero() {
    // given
    vehicleDto.setNumberOfVehicleOwners(-1);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_price_is_null() {
    // given
    vehicleDto.setPrice(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_price_is_less_than_zero() {
    // given
    vehicleDto.setPrice(BigDecimal.valueOf(-1L));
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_type_is_null() {
    // given
    vehicleDto.setType(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_vin_is_null() {
    // given
    vehicleDto.setVin(null);
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_vin_is_empty() {
    // given
    vehicleDto.setVin("");
    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    assertThrows(IllegalArgumentException.class,
        () -> vehicleValidator.validate(vehicleDto, errors));
  }

  @Test
  void should_throw_exception_when_vin_has_incorrect_format() {
    // given
    //    vehicleDto.setVin("");
    //    Errors errors = new BeanPropertyBindingResult(vehicleDto, "validUser");

    // when + then
    //    assertThrows(IllegalArgumentException.class,
    //        () -> vehicleValidator.validate(vehicleDto, errors));
  }
}