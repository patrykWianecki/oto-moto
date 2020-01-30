package com.app.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.exception.MyException;
import com.app.model.Engine;
import com.app.model.Vehicle;
import com.app.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Mono<Vehicle> addVehicle(final Vehicle vehicle) {
        try {
            Optional.ofNullable(vehicle).orElseThrow(() -> new MyException("Missing vehicle"));
            return vehicleRepository.save(vehicle);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to add new vehicle"));
        }
    }

    public Mono<Vehicle> updateVehicle(final Vehicle vehicle) {
        try {
            Optional.ofNullable(vehicle).orElseThrow(() -> new MyException("Missing vehicle"));

            return vehicleRepository
                .findById(vehicle.getId())
                .map(VehicleService::createVehicle)
                .flatMap(vehicleRepository::save);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to update vehicle with id " + vehicle.getId()));
        }
    }

    private static Vehicle createVehicle(final Vehicle vehicle) {
        return Vehicle.builder()
            .colour(vehicle.getColour())
            .condition(vehicle.getCondition())
            .currency(vehicle.getCurrency())
            .drive(vehicle.getDrive())
            .engine(createEngine(vehicle.getEngine()))
            .features(vehicle.getFeatures())
            .firstRegistration(vehicle.getFirstRegistration())
            .gearbox(vehicle.getGearbox())
            .isAccidentFree(vehicle.isAccidentFree())
            .isDamaged(vehicle.isDamaged())
            .location(vehicle.getLocation())
            .make(vehicle.getMake())
            .mileage(vehicle.getMileage())
            .model(vehicle.getModel())
            .numberOfSeats(vehicle.getNumberOfSeats())
            .numberOfSeats(vehicle.getNumberOfSeats())
            .price(vehicle.getPrice())
            .type(vehicle.getType())
            .build();
    }

    private static Engine createEngine(final Engine engine) {
        return Engine.builder()
            .capacity(engine.getCapacity())
            .emmisionClass(engine.getEmmisionClass())
            .fuel(engine.getFuel())
            .fuelConsumption(engine.getFuelConsumption())
            .power(engine.getPower())
            .build();
    }

    public Mono<Vehicle> findVehicleById(final BigInteger id) {
        try {
            Optional.ofNullable(id).orElseThrow(() -> new MyException("Missing vehicle id"));
            return vehicleRepository.findById(id);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to find vehicle with id " + id));
        }
    }

    public Flux<Vehicle> findAllVehicles() {
        try {
            return vehicleRepository.findAll();
        } catch (Exception e) {
            return Flux.error(new MyException("Failed to find all vehicles"));
        }
    }

    public Mono<Void> removeVehicleById(final BigInteger id) {
        try {
            Optional.ofNullable(id).orElseThrow(() -> new MyException("Missing vehicle id"));
            return vehicleRepository.deleteById(id);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to remove vehicle with id " + id));
        }
    }

    public Mono<Void> removeAllVehicles() {
        try {
            return vehicleRepository.deleteAll();
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to remove all vehicles"));
        }
    }
}
