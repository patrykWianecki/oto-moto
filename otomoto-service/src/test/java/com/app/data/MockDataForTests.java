package com.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.app.model.Engine;
import com.app.model.Generation.Mercedes.SClass;
import com.app.model.Model.Mercedes;
import com.app.model.Vehicle;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;

import static com.app.model.Colour.*;
import static com.app.model.Condition.*;
import static com.app.model.Drive.*;
import static com.app.model.EmmisionClass.*;
import static com.app.model.Feature.*;
import static com.app.model.Fuel.*;
import static com.app.model.Gearbox.*;
import static com.app.model.Make.*;
import static com.app.model.Type.*;

public interface MockDataForTests {

  String BLANK = "";
  String VEHICLE_ID = "71623v12hvk1j2";
  String VEHICLE_DTO_ID = "7865asfdasdfaw3";
  String VIN = "JH4CW2H61CC000073";
  String CURRENCY_PLN = "PLN";
  String LOCATION_WARSAW = "Warsaw";
  long MILEAGE = 0L;
  int NUMBER_OF_SEATS = 4;
  int NUMBER_OF_VEHICLE_OWNERS = 0;
  int POWER = 612;
  BigDecimal PRICE = BigDecimal.valueOf(900000);
  double CAPACITY = 4.0D;
  double FUEL_CONSUMPTION = 15.0D;
  LocalDate dateOfProduction = LocalDate.of(2019, 12, 1);
  LocalDate dateOfFirstRegistration = LocalDate.of(2020, 1, 1);

  static Vehicle createVehicle() {
    return Vehicle.builder()
        .id(VEHICLE_ID)
        .colour(BLACK)
        .condition(NEW)
        .currency(CURRENCY_PLN)
        .dateOfProduction(dateOfProduction)
        .drive(ALL_WHEEL_DRIVE)
        .engine(createEngine())
        .features(Set.of(ABS, ASR, ESP))
        .firstRegistration(dateOfFirstRegistration)
        .gearbox(AUTOMATIC)
        .generation(SClass.W222.name())
        .isAccidentFree(true)
        .isDamaged(false)
        .isPriceNegotiable(false)
        .location(LOCATION_WARSAW)
        .make(MERCEDES)
        .mileage(MILEAGE)
        .model(Mercedes.SCLASS.name())
        .numberOfSeats(NUMBER_OF_SEATS)
        .numberOfVehicleOwners(NUMBER_OF_VEHICLE_OWNERS)
        .price(PRICE)
        .type(SEDAN)
        .vin(VIN)
        .build();
  }

  private static Engine createEngine() {
    return Engine.builder()
        .capacity(CAPACITY)
        .emmisionClass(EURO_6)
        .fuel(PETROL)
        .fuelConsumption(FUEL_CONSUMPTION)
        .power(POWER)
        .build();
  }

  static VehicleDto createVehicleDto() {
    return VehicleDto.builder()
        .id(VEHICLE_DTO_ID)
        .colour(BLACK)
        .condition(NEW)
        .currency(CURRENCY_PLN)
        .dateOfProduction(dateOfProduction)
        .drive(ALL_WHEEL_DRIVE)
        .engineDto(createEngineDto())
        .features(Set.of(ABS, ASR, ESP))
        .firstRegistration(dateOfFirstRegistration)
        .gearbox(AUTOMATIC)
        .generation(SClass.W222.name())
        .isAccidentFree(true)
        .isDamaged(false)
        .isPriceNegotiable(false)
        .location(LOCATION_WARSAW)
        .make(MERCEDES)
        .mileage(MILEAGE)
        .model(Mercedes.SCLASS.name())
        .numberOfSeats(NUMBER_OF_SEATS)
        .numberOfVehicleOwners(NUMBER_OF_VEHICLE_OWNERS)
        .price(PRICE)
        .type(SEDAN)
        .vin(VIN)
        .build();
  }

  static EngineDto createEngineDto() {
    return EngineDto.builder()
        .capacity(CAPACITY)
        .emmisionClass(EURO_6)
        .fuel(PETROL)
        .fuelConsumption(FUEL_CONSUMPTION)
        .power(POWER)
        .build();
  }

  static String createValidResponse() {
    return "{\n" +
        "          \"id\": \"71623v12hvk1j2\",\n" +
        "          \"colour\": \"BLACK\",\n" +
        "          \"condition\": \"NEW\",\n" +
        "          \"currency\": \"PLN\",\n" +
        "          \"dateOfProduction\": \"2019-12-01\",\n" +
        "          \"drive\": \"ALL_WHEEL_DRIVE\",\n" +
        "          \"engineDto\": {\n" +
        "            \"capacity\": 4.0,\n" +
        "            \"emmisionClass\": \"EURO_6\",\n" +
        "            \"fuel\": \"PETROL\",\n" +
        "            \"fuelConsumption\": 15.0,\n" +
        "            \"power\": 612\n" +
        "          },\n" +
        "          \"features\": [\n" +
        "            \"ASR\",\n" +
        "            \"ESP\",\n" +
        "            \"ABS\"\n" +
        "          ],\n" +
        "          \"firstRegistration\": \"2020-01-01\",\n" +
        "          \"gearbox\": \"AUTOMATIC\",\n" +
        "          \"generation\": \"W222\",\n" +
        "          \"accidentFree\": true,\n" +
        "          \"damaged\": false,\n" +
        "          \"priceNegotiable\": false,\n" +
        "          \"location\": \"Warsaw\",\n" +
        "          \"make\": \"MERCEDES\",\n" +
        "          \"mileage\": 0,\n" +
        "          \"model\": \"SCLASS\",\n" +
        "          \"numberOfSeats\": 4,\n" +
        "          \"numberOfVehicleOwners\": 0,\n" +
        "          \"price\": 900000,\n" +
        "          \"type\": \"SEDAN\",\n" +
        "          \"vin\": \"JH4CW2H61CC000073\"\n" +
        "        }";
  }
}
