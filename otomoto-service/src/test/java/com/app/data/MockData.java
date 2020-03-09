package com.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.app.model.Engine;
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

public class MockData {

  public static final String VEHICLE_ID = "71623v12hvk1j2";
  public static final String VEHICLE_DTO_ID = "7865asfdasdfaw3";

  public static Vehicle createVehicle() {
    return Vehicle.builder()
        .id(VEHICLE_ID)
        .colour(BLACK)
        .condition(NEW)
        .currency("PLN")
        .drive(ALL_WHEEL_DRIVE)
        .engine(createEngine())
        .features(Set.of(ABS, ASR, ESP))
        .firstRegistration(LocalDate.of(2020, 1, 1))
        .gearbox(AUTOMATIC)
        .isAccidentFree(true)
        .isDamaged(false)
        .location("Warsaw")
        .make(BMW)
        .mileage(0L)
        .model("E-Class")
        .numberOfSeats(4)
        .numberOfVehicleOwners(0)
        .price(BigDecimal.valueOf(900000))
        .type(SEDAN)
        .build();
  }

  private static Engine createEngine() {
    return Engine.builder()
        .capacity(4.0)
        .emmisionClass(EURO_6)
        .fuel(PETROL)
        .fuelConsumption(15.0)
        .power(612)
        .build();
  }

  public static VehicleDto createVehicleDto() {
    return VehicleDto.builder()
        .id(VEHICLE_DTO_ID)
        .colour(BLACK)
        .condition(NEW)
        .currency("PLN")
        .drive(ALL_WHEEL_DRIVE)
        .engineDto(createEngineDto())
        .features(Set.of(ABS, ASR, ESP))
        .firstRegistration(LocalDate.of(2020, 1, 1))
        .gearbox(AUTOMATIC)
        .isAccidentFree(true)
        .isDamaged(false)
        .location("Warsaw")
        .make(BMW)
        .mileage(0L)
        .model("E-Class")
        .numberOfSeats(4)
        .numberOfVehicleOwners(0)
        .price(BigDecimal.valueOf(900000))
        .type(SEDAN)
        .build();
  }

  private static EngineDto createEngineDto() {
    return EngineDto.builder()
        .capacity(4.0)
        .emmisionClass(EURO_6)
        .fuel(PETROL)
        .fuelConsumption(15.0)
        .power(612)
        .build();
  }

  public static String createValidResponse() {
    return """
        {
          "id": "71623v12hvk1j2",
          "colour": "BLACK",
          "condition": "NEW",
          "currency": "PLN",
          "drive": "ALL_WHEEL_DRIVE",
          "features": [
            "ASR",
            "ESP",
            "ABS"
          ],
          "firstRegistration": "2020-01-01",
          "engineDto": {
            "capacity": 4.0,
            "emmisionClass": "EURO_6",
            "fuel": "PETROL",
            "fuelConsumption": 15.0,
            "power": 612
          },
          "gearbox": "AUTOMATIC",
          "location": "Warsaw",
          "make": "BMW",
          "mileage": 0,
          "model": "E-Class",
          "numberOfSeats": 4,
          "numberOfVehicleOwners": 0,
          "price": 900000,
          "type": "SEDAN",
          "accidentFree": true,
          "damaged": false
        }""";
  }
}
