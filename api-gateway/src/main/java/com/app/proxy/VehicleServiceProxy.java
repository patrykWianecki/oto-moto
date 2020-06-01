package com.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.VehicleDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient(name = "otomoto-service")
public interface VehicleServiceProxy {

    @PostMapping("/vehicles")
    Mono<ResponseEntity<VehicleDto>> addVehicle(@RequestBody VehicleDto vehicle);

    @PutMapping("/vehicles")
    Mono<ResponseEntity<VehicleDto>> updateVehicle(@RequestParam String vehicleId,
            @RequestBody VehicleDto vehicleDto);

    @GetMapping("/vehicles")
    Mono<ResponseEntity<VehicleDto>> findVehicleById(@RequestParam String vehicleId);

    @GetMapping("/vehicles/all")
    Flux<VehicleDto> findAllVehicles();

    @DeleteMapping("/vehicles")
    Mono<ResponseEntity<Void>> removeVehicleById(@RequestParam String vehicleId);

    @DeleteMapping("/vehicles/all")
    Mono<ResponseEntity<Void>> removeAllVehicles();
}
