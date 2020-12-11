package com.app.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.VehicleDto;

@FeignClient(name = "otomoto-service")
public interface VehicleServiceProxy {

  @PostMapping("/vehicles")
  ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleDto vehicle);

  @PutMapping("/vehicles")
  ResponseEntity<VehicleDto> updateVehicle(@RequestParam long vehicleId,
      @RequestBody VehicleDto vehicleDto);

  @GetMapping("/vehicles")
  ResponseEntity<VehicleDto> findVehicleById(@RequestParam long vehicleId);

  @GetMapping("/vehicles/all")
  List<VehicleDto> findAllVehicles();

  @DeleteMapping("/vehicles")
  ResponseEntity<VehicleDto> removeVehicleById(@RequestParam long vehicleId);
}
