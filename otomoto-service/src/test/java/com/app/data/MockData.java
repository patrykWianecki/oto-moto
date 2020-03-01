package com.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.app.model.Engine;
import com.app.model.Type;
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

public class MockData {

  public static Vehicle createVehicle() {
    return Vehicle.builder()
        .id("71623v12hvk1j2")
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
        .type(Type.SEDAN)
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
        .id("7865asfdasdfaw3")
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
        .type(Type.SEDAN)
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
}
