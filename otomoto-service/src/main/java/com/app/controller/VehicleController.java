package com.app.controller;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.MyException;
import com.app.model.Vehicle;
import com.app.service.VehicleService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public Mono<Vehicle> addVehicle(@RequestBody final Vehicle vehicle) {
        try {
            return vehicleService.addVehicle(vehicle);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to add new vehicle"));
        }
    }

    @PutMapping("/{id}")
    public Mono<Vehicle> updateVehicle(@RequestBody final Vehicle vehicle) {
        try {
            return vehicleService.updateVehicle(vehicle);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to update vehicle with id " + vehicle.getId()));
        }
    }

    @GetMapping("/{id}")
    public Mono<Vehicle> findVehicleById(@PathVariable final BigInteger id) {
        try {
            return vehicleService.findVehicleById(id);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to find vehicle with id " + id));
        }
    }

    @GetMapping
    public Flux<Vehicle> findAllVehicles() {
        try {
            return vehicleService.findAllVehicles();
        } catch (Exception e) {
            return Flux.error(new MyException("Failed to find all vehicles"));
        }
    }

    @DeleteMapping("/{id}")
    public Mono<Void> removeVehicleById(@PathVariable final BigInteger id) {
        try {
            return vehicleService.removeVehicleById(id);
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to remove vehicle with id " + id));
        }
    }

    @DeleteMapping
    public Mono<Void> removeAllVehicles() {
        try {
            return vehicleService.removeAllVehicles();
        } catch (Exception e) {
            return Mono.error(new MyException("Failed to remove all vehicles"));
        }
    }
}
