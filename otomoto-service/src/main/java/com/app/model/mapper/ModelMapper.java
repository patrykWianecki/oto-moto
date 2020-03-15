package com.app.model.mapper;

import com.app.model.Engine;
import com.app.model.Vehicle;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;

public interface ModelMapper {

  static Vehicle fromVehicleDtoToVehicle(final VehicleDto vehicleDto) {
    return vehicleDto == null ? null : Vehicle.builder()
        .id(vehicleDto.getId())
        .colour(vehicleDto.getColour())
        .condition(vehicleDto.getCondition())
        .currency(vehicleDto.getCurrency())
        .dateOfProduction(vehicleDto.getDateOfProduction())
        .drive(vehicleDto.getDrive())
        .engine(vehicleDto.getEngineDto() == null ?
            null : fromEngineDtoToEngine(vehicleDto.getEngineDto()))
        .features(vehicleDto.getFeatures())
        .firstRegistration(vehicleDto.getFirstRegistration())
        .gearbox(vehicleDto.getGearbox())
        .generation(vehicleDto.getGeneration())
        .isAccidentFree(vehicleDto.isAccidentFree())
        .isDamaged(vehicleDto.isDamaged())
        .isPriceNegotiable(vehicleDto.isPriceNegotiable())
        .location(vehicleDto.getLocation())
        .make(vehicleDto.getMake())
        .mileage(vehicleDto.getMileage())
        .model(vehicleDto.getModel())
        .numberOfSeats(vehicleDto.getNumberOfSeats())
        .numberOfVehicleOwners(vehicleDto.getNumberOfVehicleOwners())
        .price(vehicleDto.getPrice())
        .type(vehicleDto.getType())
        .vin(vehicleDto.getVin())
        .build();
  }

  static VehicleDto fromVehicleToVehicleDto(final Vehicle vehicle) {
    return vehicle == null ? null : VehicleDto.builder()
        .id(vehicle.getId())
        .colour(vehicle.getColour())
        .condition(vehicle.getCondition())
        .currency(vehicle.getCurrency())
        .dateOfProduction(vehicle.getDateOfProduction())
        .drive(vehicle.getDrive())
        .engineDto(vehicle.getEngine() == null ? null : fromEngineToEngineDto(vehicle.getEngine()))
        .features(vehicle.getFeatures())
        .firstRegistration(vehicle.getFirstRegistration())
        .gearbox(vehicle.getGearbox())
        .generation(vehicle.getGeneration())
        .isAccidentFree(vehicle.isAccidentFree())
        .isDamaged(vehicle.isDamaged())
        .isPriceNegotiable(vehicle.isPriceNegotiable())
        .location(vehicle.getLocation())
        .make(vehicle.getMake())
        .mileage(vehicle.getMileage())
        .model(vehicle.getModel())
        .numberOfSeats(vehicle.getNumberOfSeats())
        .numberOfVehicleOwners(vehicle.getNumberOfVehicleOwners())
        .price(vehicle.getPrice())
        .type(vehicle.getType())
        .vin(vehicle.getVin())
        .build();
  }

  static Engine fromEngineDtoToEngine(final EngineDto engineDto) {
    return engineDto == null ? null : Engine.builder()
        .capacity(engineDto.getCapacity())
        .emmisionClass(engineDto.getEmmisionClass())
        .fuel(engineDto.getFuel())
        .fuelConsumption(engineDto.getFuelConsumption())
        .power(engineDto.getPower())
        .build();
  }

  static EngineDto fromEngineToEngineDto(final Engine engine) {
    return engine == null ? null : EngineDto.builder()
        .capacity(engine.getCapacity())
        .emmisionClass(engine.getEmmisionClass())
        .fuel(engine.getFuel())
        .fuelConsumption(engine.getFuelConsumption())
        .power(engine.getPower())
        .build();
  }
}
