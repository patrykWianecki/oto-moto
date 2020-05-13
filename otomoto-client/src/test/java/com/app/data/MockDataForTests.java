package com.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.app.model.EngineDto;
import com.app.model.Generation.Audi.A_6;
import com.app.model.Model.Audi;
import com.app.model.VehicleDto;

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
  String VEHICLE_DTO_ID = "7865asfdasdfaw3";
  String VIN = "JH4CW2H61CC000073";
  String CURRENCY_PLN = "PLN";
  String LOCATION_WARSAW = "Warsaw";
  Long MILEAGE = 0L;
  Integer NUMBER_OF_SEATS = 4;
  Integer NUMBER_OF_VEHICLE_OWNERS = 0;
  Integer POWER = 612;
  BigDecimal PRICE = BigDecimal.valueOf(900000);
  Double CAPACITY = 4.0D;
  Double FUEL_CONSUMPTION = 15.0D;
  LocalDate DATE_OF_PRODUCTION = LocalDate.of(2019, 12, 1);
  LocalDate DATE_OF_FIRST_REGISTRATION = LocalDate.of(2020, 1, 1);

  static VehicleDto createVehicleDto() {
    return VehicleDto.builder()
        .id(VEHICLE_DTO_ID)
        .colour(BLACK)
        .condition(NEW)
        .currency(CURRENCY_PLN)
        .dateOfProduction(DATE_OF_PRODUCTION)
        .drive(ALL_WHEEL_DRIVE)
        .engineDto(createEngineDto())
        .features(Set.of(ABS, ASR, ESP))
        .firstRegistration(DATE_OF_FIRST_REGISTRATION)
        .gearbox(AUTOMATIC)
        .generation(A_6.C8.name())
        .isAccidentFree(true)
        .isDamaged(false)
        .isPriceNegotiable(false)
        .location(LOCATION_WARSAW)
        .make(AUDI)
        .mileage(MILEAGE)
        .model(Audi.A_6.name())
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
        .emmisionClass(EURO_5)
        .fuel(DIESEL)
        .fuelConsumption(FUEL_CONSUMPTION)
        .power(POWER)
        .build();
  }

  static String createValidResponse() {
    return "{\n" +
        "          \"id\": \"7865asfdasdfaw3\",\n" +
        "          \"colour\": \"BLACK\",\n" +
        "          \"condition\": \"NEW\",\n" +
        "          \"currency\": \"PLN\",\n" +
        "          \"dateOfProduction\": \"2019-12-01\",\n" +
        "          \"drive\": \"ALL_WHEEL_DRIVE\",\n" +
        "          \"engineDto\": {\n" +
        "            \"capacity\": 4.0,\n" +
        "            \"emmisionClass\": \"EURO_5\",\n" +
        "            \"fuel\": \"DIESEL\",\n" +
        "            \"fuelConsumption\": 15.0,\n" +
        "            \"power\": 612\n" +
        "          },\n" +
        "          \"features\": [\n" +
        "            \"ESP\",\n" +
        "            \"ABS\",\n" +
        "            \"ASR\"\n" +
        "          ],\n" +
        "          \"firstRegistration\": \"2020-01-01\",\n" +
        "          \"gearbox\": \"AUTOMATIC\",\n" +
        "          \"generation\": \"C8\",\n" +
        "          \"location\": \"Warsaw\",\n" +
        "          \"make\": \"AUDI\",\n" +
        "          \"mileage\": 0,\n" +
        "          \"model\": \"A_6\",\n" +
        "          \"numberOfSeats\": 4,\n" +
        "          \"numberOfVehicleOwners\": 0,\n" +
        "          \"price\": 900000,\n" +
        "          \"type\": \"SEDAN\",\n" +
        "          \"vin\": \"JH4CW2H61CC000073\",\n" +
        "          \"priceNegotiable\": false,\n" +
        "          \"damaged\": false,\n" +
        "          \"accidentFree\": true\n" +
        "        }";
  }
}
