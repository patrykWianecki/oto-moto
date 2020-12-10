package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Vehicle;
import com.app.model.dto.VehicleDto;
import com.app.model.mapper.ModelMapper;
import com.app.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

import static com.app.model.mapper.ModelMapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  public ResponseEntity<VehicleDto> addVehicle(final VehicleDto vehicleDto) {
    final Vehicle vehicleToAdd = fromVehicleDtoToVehicle(vehicleDto);
    final VehicleDto addedVehicleDto =
        fromVehicleToVehicleDto(vehicleRepository.save(vehicleToAdd));
    return ResponseEntity.of(Optional.of(addedVehicleDto));
  }

  public ResponseEntity<VehicleDto> updateVehicle(final long vehicleId,
      final VehicleDto vehicleDto) {
    if (vehicleRepository.findById(vehicleId).isPresent()) {
      final Vehicle vehicle = fromVehicleDtoToVehicle(vehicleDto);
      final VehicleDto updatedVehicleDto = fromVehicleToVehicleDto(vehicleRepository.save(vehicle));
      return ResponseEntity.of(Optional.of(updatedVehicleDto));
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<VehicleDto> findVehicleById(final long id) {
    final Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
    return createResponseEntity(optionalVehicle);
  }

  public List<VehicleDto> findAllVehicles() {
    return vehicleRepository.findAll()
        .stream()
        .map(ModelMapper::fromVehicleToVehicleDto)
        .collect(Collectors.toList());
  }

  public ResponseEntity<VehicleDto> removeVehicleById(final long vehicleId) {
    final Optional<Vehicle> optionalVehicle = vehicleRepository.deleteVehicleById(vehicleId);
    return createResponseEntity(optionalVehicle);
  }

  private static ResponseEntity<VehicleDto> createResponseEntity(
      final Optional<Vehicle> optionalVehicle) {
    if (optionalVehicle.isPresent()) {
      Vehicle vehicle = optionalVehicle.get();
      VehicleDto vehicleDto = fromVehicleToVehicleDto(vehicle);
      Optional<VehicleDto> optionalVehicleDto = Optional.ofNullable(vehicleDto);
      return ResponseEntity.of(optionalVehicleDto);
    }
    return ResponseEntity.notFound().build();
  }
}
