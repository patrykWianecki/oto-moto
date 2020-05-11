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

@FeignClient(name = "otomoto-client")
public interface VehicleClientProxy {

    @PostMapping
    Mono<ResponseEntity<VehicleDto>> addVehicle(@RequestBody VehicleDto vehicle);

    @PutMapping
    Mono<ResponseEntity<VehicleDto>> updateVehicle(@RequestParam String vehicleId,
            @RequestBody VehicleDto vehicle);

    @GetMapping
    Mono<ResponseEntity<VehicleDto>> findVehicleById(@RequestParam String vehicleId);

    @GetMapping("/all")
    Flux<VehicleDto> findAllVehicles();

    @DeleteMapping
    Mono<ResponseEntity<Void>> removeVehicleById(@RequestParam String vehicleId);

    @DeleteMapping("/all")
    Mono<ResponseEntity<Void>> removeAllVehicles();
}
