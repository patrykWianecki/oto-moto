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
    public Mono<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Mono<Vehicle> updateVehicle(@PathVariable BigInteger id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @GetMapping("/{id}")
    public Mono<Vehicle> findVehicleById(@PathVariable BigInteger id) {
        return vehicleService.findVehicleById(id);
    }

    @GetMapping
    public Flux<Vehicle> findAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> removeVehicleById(@PathVariable BigInteger id) {
        return vehicleService.removeVehicleById(id);
    }

    @DeleteMapping
    public Flux<Void> removeAllVehicles() {
        return vehicleService.removeAllVehicles();
    }
}
