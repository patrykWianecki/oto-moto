package com.app.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Engine;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;
import com.app.model.mapper.ModelMapper;
import com.app.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.app.model.mapper.ModelMapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {

  private final VehicleRepository vehicleRepository;

  public Mono<ResponseEntity<VehicleDto>> addVehicle(final VehicleDto vehicleDto) {
    return Mono.just(fromVehicleDtoToVehicle(vehicleDto))
        .flatMap(vehicleRepository::save)
        .map(ModelMapper::fromVehicleToVehicleDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.badRequest().build());
  }

  public Mono<ResponseEntity<VehicleDto>> updateVehicle(final String vehicleId,
      final VehicleDto vehicleDto) {
    return vehicleRepository.findById(vehicleId)
        .map(vehicle -> {
          vehicle.setId(vehicleDto.getId());
          vehicle.setColour(vehicleDto.getColour());
          vehicle.setCondition(vehicleDto.getCondition());
          vehicle.setCurrency(vehicleDto.getCurrency());
          vehicle.setDateOfProduction(vehicleDto.getDateOfProduction());
          vehicle.setDrive(vehicleDto.getDrive());
          vehicle.setEngine(createEngine(vehicleDto));
          vehicle.setFeatures(vehicleDto.getFeatures());
          vehicle.setFirstRegistration(vehicleDto.getFirstRegistration());
          vehicle.setGearbox(vehicleDto.getGearbox());
          vehicle.setGeneration(vehicleDto.getGeneration());
          vehicle.setAccidentFree(vehicleDto.isAccidentFree());
          vehicle.setDamaged(vehicleDto.isDamaged());
          vehicle.setPriceNegotiable(vehicleDto.isPriceNegotiable());
          vehicle.setLocation(vehicleDto.getLocation());
          vehicle.setMake(vehicleDto.getMake());
          vehicle.setMileage(vehicleDto.getMileage());
          vehicle.setModel(vehicleDto.getModel());
          vehicle.setNumberOfSeats(vehicleDto.getNumberOfSeats());
          vehicle.setNumberOfVehicleOwners(vehicleDto.getNumberOfVehicleOwners());
          vehicle.setPrice(vehicleDto.getPrice());
          vehicle.setType(vehicleDto.getType());
          vehicle.setVin(vehicleDto.getVin());
          return vehicle;
        })
        .flatMap(vehicleRepository::save)
        .map(ModelMapper::fromVehicleToVehicleDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.badRequest().build());
  }

  private static Engine createEngine(VehicleDto vehicleDto) {
    EngineDto engineDto = vehicleDto.getEngineDto();
    return Engine.builder()
        .capacity(engineDto.getCapacity())
        .emmisionClass(engineDto.getEmmisionClass())
        .fuel(engineDto.getFuel())
        .fuelConsumption(engineDto.getFuelConsumption())
        .power(engineDto.getPower())
        .build();
  }

  public Mono<ResponseEntity<VehicleDto>> findVehicleById(final String id) {
    Optional.ofNullable(id).orElseThrow(() -> new IllegalStateException("Missing vehicle id"));
    return vehicleRepository.findById(id)
        .map(ModelMapper::fromVehicleToVehicleDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.badRequest().build());
  }

  public Flux<VehicleDto> findAllVehicles() {
    return vehicleRepository.findAll()
        .map(ModelMapper::fromVehicleToVehicleDto);
  }

  public Mono<ResponseEntity<Void>> removeVehicleById(final String id) {
    Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("Missing vehicle id"));
    return vehicleRepository.deleteById(id)
        .map(ResponseEntity::ok);
  }

  public Mono<ResponseEntity<Void>> removeAllVehicles() {
    return vehicleRepository.deleteAll()
        .map(ResponseEntity::ok);
  }
}
