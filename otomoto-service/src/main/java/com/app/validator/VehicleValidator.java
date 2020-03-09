package com.app.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.Colour;
import com.app.model.Condition;
import com.app.model.Drive;
import com.app.model.EmmisionClass;
import com.app.model.Feature;
import com.app.model.Fuel;
import com.app.model.Gearbox;
import com.app.model.Make;
import com.app.model.Type;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;

import static java.math.BigDecimal.*;

public class VehicleValidator implements Validator {

  @Override
  public boolean supports(final Class<?> aClass) {
    return aClass.equals(VehicleDto.class);
  }

  @Override
  public void validate(final Object o, final Errors errors) {
    VehicleDto vehicleDto = (VehicleDto) o;

    if (!StringUtils.isNotBlank(vehicleDto.getId())) {
      throw new IllegalArgumentException("Vehicle id is missing");
    }
    if (!EnumUtils.isValidEnum(Colour.class, vehicleDto.getColour().name())) {
      throw new IllegalArgumentException("Vehicle colour is missing");
    }
    if (!EnumUtils.isValidEnum(Condition.class, vehicleDto.getCondition().name())) {
      throw new IllegalArgumentException("Vehicle condition does not exist");
    }
    if (!StringUtils.isNotBlank(vehicleDto.getCurrency())) {
      throw new IllegalArgumentException("Vehicle currency is missing");
    }
    if (!EnumUtils.isValidEnum(Drive.class, vehicleDto.getDrive().name())) {
      throw new IllegalArgumentException("Vehicle drive does not exist");
    }

    validateFeatures(vehicleDto.getFeatures());

    LocalDate dateOfProduction = vehicleDto.getDateOfProduction();
    if (Objects.isNull(dateOfProduction)) {
      throw new IllegalArgumentException("Production date has incorrect value");
    }

    LocalDate firstRegistration = vehicleDto.getFirstRegistration();
    if (Objects.isNull(firstRegistration) || firstRegistration.isBefore(dateOfProduction)) {
      throw new IllegalArgumentException("First registration date has incorrect value");
    }

    validateEngine(vehicleDto.getEngineDto());

    if (!EnumUtils.isValidEnum(Gearbox.class, vehicleDto.getGearbox().name())) {
      throw new IllegalArgumentException("Vehicle gearbox is missing");
    }

    if (StringUtils.isEmpty(vehicleDto.getLocation())) {
      throw new IllegalArgumentException("Vehicle location is missing");
    }
    if (!EnumUtils.isValidEnum(Make.class, vehicleDto.getMake().name())) {
      throw new IllegalArgumentException("Vehicle make is missing");
    }

    Long mileage = vehicleDto.getMileage();
    if (Objects.isNull(mileage) || mileage < 0) {
      throw new IllegalArgumentException("Mileage has incorrect value");
    }

    if (Objects.isNull(vehicleDto.getModel())) {
      throw new IllegalArgumentException("Vehicle model is missing");
    }

    Integer numberOfSeats = vehicleDto.getNumberOfSeats();
    if (Objects.isNull(numberOfSeats) || numberOfSeats < 1) {
      throw new IllegalArgumentException("Number of seats has incorrect value");
    }

    Integer numberOfVehicleOwners = vehicleDto.getNumberOfVehicleOwners();
    if (Objects.isNull(numberOfVehicleOwners) || numberOfVehicleOwners < 0) {
      throw new IllegalArgumentException("Number of vehicle owners has incorrect valueo");
    }

    BigDecimal price = vehicleDto.getPrice();
    if (Objects.isNull(price) || price.compareTo(ONE) <= 0) {
      throw new IllegalArgumentException("Price has incorrect value");
    }
    if (!EnumUtils.isValidEnum(Type.class, vehicleDto.getType().name())) {
      throw new IllegalArgumentException("Vehicle is missing");
    }
  }

  private static void validateFeatures(Set<Feature> features) {
    if (CollectionUtils.isNotEmpty(features)) {
      throw new IllegalArgumentException("Vehicle has no features");
    }

    if (!areFeaturesValid(features)) {
      throw new IllegalArgumentException("Features contains invalid value");
    }
  }

  private static boolean areFeaturesValid(Set<Feature> features) {
    return Arrays
        .stream(Feature.values())
        .collect(Collectors.toSet())
        .containsAll(features);
  }

  private static void validateEngine(EngineDto engineDto) {
    if (Objects.isNull(engineDto)) {
      throw new IllegalArgumentException("Missing vehicle engine");
    }

    Double capacity = engineDto.getCapacity();
    if (Objects.isNull(capacity) || capacity <= 0.0) {
      throw new IllegalArgumentException("Engine capacity has incorrect value");
    }
    if (!EnumUtils.isValidEnum(EmmisionClass.class, engineDto.getEmmisionClass().name())) {
      throw new IllegalArgumentException("Emission class does not exist");
    }
    if (!EnumUtils.isValidEnum(Fuel.class, engineDto.getFuel().name())) {
      throw new IllegalArgumentException("Fuel type does not exist");
    }

    Double fuelConsumption = engineDto.getFuelConsumption();
    if (Objects.isNull(fuelConsumption) || fuelConsumption <= 0.0) {
      throw new IllegalArgumentException("Engine fuel consumption has incorrect value");
    }

    Integer power = engineDto.getPower();
    if (Objects.isNull(power) || power <= 0) {
      throw new IllegalArgumentException("Engine power has incorrect value");
    }
  }
}
