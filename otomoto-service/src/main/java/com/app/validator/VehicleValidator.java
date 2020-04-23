package com.app.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
import com.app.model.Feature;
import com.app.model.Fuel;
import com.app.model.Gearbox;
import com.app.model.Generation;
import com.app.model.Make;
import com.app.model.Model;
import com.app.model.Type;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;

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
  public void validate(@NonNull final Object o, @NonNull final Errors errors) {
    VehicleDto vehicleDto = (VehicleDto) o;

    Colour colour = vehicleDto.getColour();
    if (isNull(colour) || !isValidEnum(Colour.class, colour.name())) {
      throw new IllegalArgumentException("Vehicle colour is missing");
    }

    Condition condition = vehicleDto.getCondition();
    if (isNull(condition) || !isValidEnum(Condition.class, condition.name())) {
      throw new IllegalArgumentException("Vehicle condition does not exist");
    }

    if (isBlank(vehicleDto.getCurrency())) {
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
    if (isBlank(model)) {
      throw new IllegalArgumentException("Vehicle model is missing");
    } else if (!doesModelExist(make, model)) {
      throw new IllegalArgumentException("Given model does not exist");
    }

    String generation = vehicleDto.getGeneration();
    if (isBlank(generation)) {
      throw new IllegalArgumentException("Generation is not valid");
    } else if (!doesGenerationExist(make, model, generation)) {
      throw new IllegalArgumentException("Given generation does not exist");
    }

    if (isEmpty(vehicleDto.getLocation())) {
      throw new IllegalArgumentException("Vehicle location is missing");
    }

    long mileage = vehicleDto.getMileage();
    if (mileage < 0) {
      throw new IllegalArgumentException("Mileage has incorrect value");
    }

    int numberOfSeats = vehicleDto.getNumberOfSeats();
    if (numberOfSeats < 1) {
      throw new IllegalArgumentException("Number of seats has incorrect value");
    }

    int numberOfVehicleOwners = vehicleDto.getNumberOfVehicleOwners();
    if (numberOfVehicleOwners < 0) {
      throw new IllegalArgumentException("Number of vehicle owners has incorrect value");
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
    if (isBlank(vin)) {
      throw new IllegalArgumentException("Vehicle vin is missing");
    }
    validateVin(vin);
  }

  private static void validateEngine(EngineDto engineDto) {
    if (isNull(engineDto)) {
      throw new IllegalArgumentException("Missing vehicle engine");
    }

    double capacity = engineDto.getCapacity();
    if (capacity <= 0.0) {
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

    double fuelConsumption = engineDto.getFuelConsumption();
    if (fuelConsumption <= 0.0) {
      throw new IllegalArgumentException("Engine fuel consumption has incorrect value");
    }

    int power = engineDto.getPower();
    if (power <= 0) {
      throw new IllegalArgumentException("Engine power has incorrect value");
    }
  }

  private static void validateFeatures(Set<Feature> features) {
    if (CollectionUtils.isEmpty(features)) {
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

  private static void validateVin(String vin) {
    List<Integer> values =
        List.of(1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 0, 7, 0, 9, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> weights = List.of(8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2);

    vin = vin.replaceAll("-", "");
    vin = vin.toUpperCase();

    int vinLength = vin.length();
    if (vinLength != 17) {
      throw new IllegalArgumentException("VIN number must be 17 characters, but was " + vinLength);
    }

    int sum = 0;
    for (int i = 0; i < 17; i++) {
      char vinCharacter = vin.charAt(i);
      int value;
      int weight = weights.get(i);

      if (vinCharacter >= 'A' && vinCharacter <= 'Z') {
        value = values.get(vinCharacter - 'A');
        if (value == 0) {
          throw new IllegalArgumentException("VIN contains illegal letter " + vinCharacter);
        }
      } else if (vinCharacter >= '0' && vinCharacter <= '9') {
        value = vinCharacter - '0';
      } else {
        throw new IllegalArgumentException("VIN contains illegal character " + vinCharacter);
      }
      sum = sum + weight * value;
    }

    validateCheckDigitCalculation(vin, sum);
  }

  private static void validateCheckDigitCalculation(String vin, int sum) {
    sum = sum % 11;
    char check = vin.charAt(8);
    if (check != 'X' && (check < '0' || check > '9')) {
      throw new IllegalArgumentException("Incorrect VIN - check digit returned wrong value");
    }
    if ((sum != 10 || check != 'X') && sum != check - '0') {
      throw new IllegalArgumentException("Incorrect VIN - check digit calculated wrong value");
    }
  }
}
