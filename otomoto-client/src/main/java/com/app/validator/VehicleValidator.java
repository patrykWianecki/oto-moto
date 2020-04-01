package com.app.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.Colour;
import com.app.model.Condition;
import com.app.model.Drive;
import com.app.model.EmmisionClass;
import com.app.model.EngineDto;
import com.app.model.Feature;
import com.app.model.Fuel;
import com.app.model.Gearbox;
import com.app.model.Generation;
import com.app.model.Make;
import com.app.model.Model;
import com.app.model.Type;
import com.app.model.VehicleDto;

import static java.math.BigDecimal.*;
import static java.util.Objects.*;
import static org.apache.commons.lang3.EnumUtils.*;
import static org.apache.commons.lang3.StringUtils.*;

@Component
public class VehicleValidator implements Validator {

  @Override
  public boolean supports(final Class<?> aClass) {
    return aClass.equals(VehicleDto.class);
  }

  @Override
  public void validate(final @NonNull Object o, final @NonNull Errors errors) {
    VehicleDto vehicleDto = (VehicleDto) o;

    Colour colour = vehicleDto.getColour();
    if (isNull(colour) || !isValidEnum(Colour.class, colour.name())) {
      throw new IllegalArgumentException("Vehicle colour is missing");
    }

    Condition condition = vehicleDto.getCondition();
    if (isNull(condition) || !isValidEnum(Condition.class, condition.name())) {
      throw new IllegalArgumentException("Vehicle condition does not exist");
    }

    if (!isNotBlank(vehicleDto.getCurrency())) {
      throw new IllegalArgumentException("Vehicle currency is missing");
    }

    LocalDate dateOfProduction = vehicleDto.getDateOfProduction();
    if (isNull(dateOfProduction)) {
      throw new IllegalArgumentException("Production date has incorrect value");
    }

    Drive drive = vehicleDto.getDrive();
    if (isNull(drive) || !isValidEnum(Drive.class, drive.name())) {
      throw new IllegalArgumentException("Vehicle drive does not exist");
    }

    validateEngine(vehicleDto.getEngineDto());
    validateFeatures(vehicleDto.getFeatures());

    LocalDate firstRegistration = vehicleDto.getFirstRegistration();
    if (isNull(firstRegistration) || firstRegistration.isBefore(dateOfProduction)) {
      throw new IllegalArgumentException("First registration date has incorrect value");
    }

    Gearbox gearbox = vehicleDto.getGearbox();
    if (isNull(gearbox) || !isValidEnum(Gearbox.class, gearbox.name())) {
      throw new IllegalArgumentException("Vehicle gearbox is missing");
    }

    Make make = vehicleDto.getMake();
    if (isNull(make) || !isValidEnum(Make.class, make.name())) {
      throw new IllegalArgumentException("Vehicle make is missing");
    }

    String model = vehicleDto.getModel();
    if (!isNotBlank(model)) {
      throw new IllegalArgumentException("Vehicle model is missing");
    } else if (!doesModelExist(make, model)) {
      throw new IllegalArgumentException("Given model does not exist");
    }

    String generation = vehicleDto.getGeneration();
    if (!isNotBlank(generation)) {
      throw new IllegalArgumentException("Generation is not valid");
    } else if (!doesGenerationExist(make, model, generation)) {
      throw new IllegalArgumentException("Given generation does not exist");
    }

    if (isEmpty(vehicleDto.getLocation())) {
      throw new IllegalArgumentException("Vehicle location is missing");
    }

    Long mileage = vehicleDto.getMileage();
    if (isNull(mileage) || mileage < 0) {
      throw new IllegalArgumentException("Mileage has incorrect value");
    }

    Integer numberOfSeats = vehicleDto.getNumberOfSeats();
    if (isNull(numberOfSeats) || numberOfSeats < 1) {
      throw new IllegalArgumentException("Number of seats has incorrect value");
    }

    Integer numberOfVehicleOwners = vehicleDto.getNumberOfVehicleOwners();
    if (isNull(numberOfVehicleOwners) || numberOfVehicleOwners < 0) {
      throw new IllegalArgumentException("Number of vehicle owners has incorrect valueo");
    }

    BigDecimal price = vehicleDto.getPrice();
    if (isNull(price) || price.compareTo(ONE) <= 0) {
      throw new IllegalArgumentException("Price has incorrect value");
    }

    Type type = vehicleDto.getType();
    if (isNull(type) || !isValidEnum(Type.class, type.name())) {
      throw new IllegalArgumentException("Vehicle is missing");
    }

    String vin = vehicleDto.getVin();
    if (!isNotBlank(vin)) {
      throw new IllegalArgumentException("Vehicle vin is missing");
    }
  }

  private static void validateEngine(EngineDto engineDto) {
    if (isNull(engineDto)) {
      throw new IllegalArgumentException("Missing vehicle engine");
    }

    Double capacity = engineDto.getCapacity();
    if (isNull(capacity) || capacity <= 0.0) {
      throw new IllegalArgumentException("Engine capacity has incorrect value");
    }

    EmmisionClass emmisionClass = engineDto.getEmmisionClass();
    if (isNull(emmisionClass) || !isValidEnum(EmmisionClass.class, emmisionClass.name())) {
      throw new IllegalArgumentException("Emission class does not exist");
    }

    Fuel fuel = engineDto.getFuel();
    if (isNull(fuel) || !isValidEnum(Fuel.class, fuel.name())) {
      throw new IllegalArgumentException("Fuel type does not exist");
    }

    Double fuelConsumption = engineDto.getFuelConsumption();
    if (isNull(fuelConsumption) || fuelConsumption <= 0.0) {
      throw new IllegalArgumentException("Engine fuel consumption has incorrect value");
    }

    Integer power = engineDto.getPower();
    if (isNull(power) || power <= 0) {
      throw new IllegalArgumentException("Engine power has incorrect value");
    }
  }

  private static void validateFeatures(Set<Feature> features) {
    if (!CollectionUtils.isNotEmpty(features)) {
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

  private static boolean doesModelExist(Make make, String model) {
    return Arrays.stream(Model.class.getDeclaredClasses())
        .filter(aClass -> make.value.equalsIgnoreCase(aClass.getSimpleName()))
        .flatMap(aClass -> Arrays.stream(aClass.getEnumConstants()))
        .map(Object::toString)
        .anyMatch(model::equalsIgnoreCase);
  }

  private static boolean doesGenerationExist(Make make, String model, String generation) {
    if (doesMakeExistInGenerations(make.value)) {
      return Arrays.stream(Generation.class.getDeclaredClasses())
          .filter(aClass -> make.value.equalsIgnoreCase(aClass.getSimpleName()))
          .flatMap(aClass -> Arrays.stream(aClass.getDeclaredClasses()))
          .filter(aClass -> model.equalsIgnoreCase(aClass.getSimpleName()))
          .flatMap(aClass -> Arrays.stream(aClass.getEnumConstants()))
          .anyMatch(o -> generation.equalsIgnoreCase(o.toString()));
    }
    return false;
  }

  private static boolean doesMakeExistInGenerations(String make) {
    return Stream.of(Generation.class.getDeclaredClasses())
        .map(Class::getSimpleName)
        .anyMatch(make::equals);
  }
}
