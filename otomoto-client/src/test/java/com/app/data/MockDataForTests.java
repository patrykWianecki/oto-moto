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

public class MockDataForTests {

  public static final String BLANK = "";
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
  public static final LocalDate DATE_OF_PRODUCTION = LocalDate.of(2019, 12, 1);
  public static final LocalDate DATE_OF_FIRST_REGISTRATION = LocalDate.of(2020, 1, 1);

  public static VehicleDto createVehicleDto() {
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

  public static EngineDto createEngineDto() {
    return EngineDto.builder()
        .capacity(CAPACITY)
        .emmisionClass(EURO_5)
        .fuel(DIESEL)
        .fuelConsumption(FUEL_CONSUMPTION)
        .power(POWER)
        .build();
  }

  public static String createValidResponse() {
    return """
        {
          "id": "7865asfdasdfaw3",
          "colour": "BLACK",
          "condition": "NEW",
          "currency": "PLN",
          "dateOfProduction": "2019-12-01",
          "drive": "ALL_WHEEL_DRIVE",
          "engineDto": {
            "capacity": 4.0,
            "emmisionClass": "EURO_5",
            "fuel": "DIESEL",
            "fuelConsumption": 15.0,
            "power": 612
          },
          "features": [
            "ESP",
            "ABS",
            "ASR"
          ],
          "firstRegistration": "2020-01-01",
          "gearbox": "AUTOMATIC",
          "generation": "C8",
          "location": "Warsaw",
          "make": "AUDI",
          "mileage": 0,
          "model": "A_6",
          "numberOfSeats": 4,
          "numberOfVehicleOwners": 0,
          "price": 900000,
          "type": "SEDAN",
          "vin": "JH4CW2H61CC000073",
          "priceNegotiable": false,
          "damaged": false,
          "accidentFree": true
        }
        """;
  }
}
