package com.app;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import com.app.model.Condition;
import com.app.model.Drive;
import com.app.model.EmmisionClass;
import com.app.model.Engine;
import com.app.model.Feature;
import com.app.model.Fuel;
import com.app.model.Gearbox;
import com.app.model.Type;
import com.app.model.Vehicle;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

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
            .id(BigInteger.valueOf(1))
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
}
