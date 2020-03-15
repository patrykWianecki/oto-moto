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

public class MockDataForTests {

  public static final String VEHICLE_ID = "71623v12hvk1j2";
  public static final String VEHICLE_DTO_ID = "7865asfdasdfaw3";
  public static final String VIN = "JH4CW2H61CC000073";
  public static final String CURRENCY_PLN = "PLN";
  public static final String LOCATION_WARSAW = "Warsaw";
  public static final Long MILEAGE = 0L;
  public static final Integer NUMBER_OF_SEATS = 4;
  public static final Integer NUMBER_OF_VEHICLE_OWNERS = 0;
  public static final Integer POWER = 612;
  public static final BigDecimal PRICE = BigDecimal.valueOf(900000);
  public static final Double CAPACITY = 4.0D;
  public static final Double FUEL_CONSUMPTION = 15.0D;
  public static final LocalDate dateOfProduction = LocalDate.of(2019, 12, 1);
  public static final LocalDate dateOfFirstRegistration = LocalDate.of(2020, 1, 1);

  public static Vehicle createVehicle() {
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

  public static VehicleDto createVehicleDto() {
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

  public static EngineDto createEngineDto() {
    return EngineDto.builder()
        .capacity(CAPACITY)
        .emmisionClass(EURO_6)
        .fuel(PETROL)
        .fuelConsumption(FUEL_CONSUMPTION)
        .power(POWER)
        .build();
  }

  public static String createValidResponse() {
    return """
        {
          "id": "71623v12hvk1j2",
          "colour": "BLACK",
          "condition": "NEW",
          "currency": "PLN",
          "dateOfProduction": "2019-12-01",
          "drive": "ALL_WHEEL_DRIVE",
          "engineDto": {
            "capacity": 4.0,
            "emmisionClass": "EURO_6",
            "fuel": "PETROL",
            "fuelConsumption": 15.0,
            "power": 612
          },
          "features": [
            "ASR",
            "ESP",
            "ABS"
          ],
          "firstRegistration": "2020-01-01",
          "gearbox": "AUTOMATIC",
          "generation": "W222",
          "accidentFree": true,
          "damaged": false,
          "priceNegotiable": false,
          "location": "Warsaw",
          "make": "MERCEDES",
          "mileage": 0,
          "model": "SCLASS",
          "numberOfSeats": 4,
          "numberOfVehicleOwners": 0,
          "price": 900000,
          "type": "SEDAN",
          "vin": "JH4CW2H61CC000073"
        }""";
  }
}
