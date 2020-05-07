package com.app.proxy;

import com.app.dto.VehicleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FeignClient("otomoto-service")
public interface VehicleServiceProxy {

    @PostMapping
    Mono<ResponseEntity<VehicleDto>> addVehicle(@RequestBody @Valid VehicleDto vehicle);

    @PutMapping
    Mono<ResponseEntity<VehicleDto>> updateVehicle(@RequestParam String vehicleId,
            @RequestBody @Valid VehicleDto vehicleDto);

    @GetMapping
    Mono<ResponseEntity<VehicleDto>> findVehicleById(@RequestParam String vehicleId);

    @GetMapping("/all")
    Flux<VehicleDto> findAllVehicles();

    @DeleteMapping
    Mono<ResponseEntity<Void>> removeVehicleById(@RequestParam String vehicleId);

    @DeleteMapping("/all")
    Mono<ResponseEntity<Void>> removeAllVehicles();
}
