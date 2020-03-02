package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.dto.VehicleDto;
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
  public Mono<ResponseEntity<VehicleDto>> addVehicle(@RequestBody final VehicleDto vehicle) {
    return vehicleService.addVehicle(vehicle);
  }

  @PutMapping
  public Mono<ResponseEntity<VehicleDto>> updateVehicle(@RequestParam String vehicleId,
      @RequestBody final VehicleDto vehicleDto) {
    return vehicleService.updateVehicle(vehicleId, vehicleDto);
  }

  @GetMapping
  public Mono<ResponseEntity<VehicleDto>> findVehicleById(@RequestParam final String vehicleId) {
    return vehicleService.findVehicleById(vehicleId);
  }

  @GetMapping("/all")
  public Flux<VehicleDto> findAllVehicles() {
    return vehicleService.findAllVehicles();
  }

  @DeleteMapping
  public Mono<ResponseEntity<Void>> removeVehicleById(@RequestParam final String vehicleId) {
    return vehicleService.removeVehicleById(vehicleId);
  }

  @DeleteMapping("/all")
  public Mono<ResponseEntity<Void>> removeAllVehicles() {
    return vehicleService.removeAllVehicles();
  }
}
