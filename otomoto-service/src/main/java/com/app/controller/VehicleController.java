package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.dto.VehicleDto;
import com.app.service.VehicleService;
import com.app.validator.VehicleValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

  private final VehicleService vehicleService;
  private final VehicleValidator vehicleValidator;

  @InitBinder
  private void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(vehicleValidator);
  }

  @PostMapping
  public ResponseEntity<VehicleDto> addVehicle(@RequestBody @Valid final VehicleDto vehicle) {
    return vehicleService.addVehicle(vehicle);
  }

  @PutMapping
  public ResponseEntity<VehicleDto> updateVehicle(@RequestParam long vehicleId,
      @RequestBody @Valid final VehicleDto vehicleDto) {
    return vehicleService.updateVehicle(vehicleId, vehicleDto);
  }

  @GetMapping
  public ResponseEntity<VehicleDto> findVehicleById(@RequestParam final long vehicleId) {
    return vehicleService.findVehicleById(vehicleId);
  }

  @GetMapping("/all")
  public List<VehicleDto> findAllVehicles() {
    return vehicleService.findAllVehicles();
  }

  @DeleteMapping
  public ResponseEntity<VehicleDto> removeVehicleById(@RequestParam final long vehicleId) {
    return vehicleService.removeVehicleById(vehicleId);
  }
}
