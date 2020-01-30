package com.app.model.mapper;

import com.app.model.Engine;
import com.app.model.Vehicle;
import com.app.model.dto.EngineDto;
import com.app.model.dto.VehicleDto;

public interface ModelMapper {

    static Vehicle fromVehicleDtoToVehicle(final VehicleDto vehicleDto) {
        return vehicleDto == null ? null : Vehicle.builder()
            .id(vehicleDto.getId())
            .model(vehicleDto.getModel())
            .make(vehicleDto.getMake())
            .price(vehicleDto.getPrice())
            .type(vehicleDto.getType())
            .mileage(vehicleDto.getMileage())
            .gearbox(vehicleDto.getGearbox())
            .location(vehicleDto.getLocation())
            .condition(vehicleDto.getCondition())
            .firstRegistration(vehicleDto.getFirstRegistration())
            .features(vehicleDto.getFeatures())
            .colour(vehicleDto.getColour())
            .numberOfSeats(vehicleDto.getNumberOfSeats())
            .numberOfVehicleOwners(vehicleDto.getNumberOfVehicleOwners())
            .isDamaged(vehicleDto.isDamaged())
            .isAccidentFree(vehicleDto.isAccidentFree())
            .drive(vehicleDto.getDrive())
            .currency(vehicleDto.getCurrency())
            .engine(vehicleDto.getEngineDto() == null ? null : fromEngineDtoToEngine(vehicleDto.getEngineDto()))
            .build();
    }

    static VehicleDto fromVehicleToVehicleDto(final Vehicle vehicle) {
        return vehicle == null ? null : VehicleDto.builder()
            .id(vehicle.getId())
            .model(vehicle.getModel())
            .make(vehicle.getMake())
            .price(vehicle.getPrice())
            .type(vehicle.getType())
            .mileage(vehicle.getMileage())
            .gearbox(vehicle.getGearbox())
            .location(vehicle.getLocation())
            .condition(vehicle.getCondition())
            .firstRegistration(vehicle.getFirstRegistration())
            .features(vehicle.getFeatures())
            .colour(vehicle.getColour())
            .numberOfSeats(vehicle.getNumberOfSeats())
            .numberOfVehicleOwners(vehicle.getNumberOfVehicleOwners())
            .isDamaged(vehicle.isDamaged())
            .isAccidentFree(vehicle.isAccidentFree())
            .drive(vehicle.getDrive())
            .currency(vehicle.getCurrency())
            .engineDto(vehicle.getEngine() == null ? null : fromEngineToEngineDto(vehicle.getEngine()))
            .build();
    }

    static Engine fromEngineDtoToEngine(final EngineDto engineDto) {
        return engineDto == null ? null : Engine.builder()
            .capacity(engineDto.getCapacity())
            .fuel(engineDto.getFuel())
            .fuelConsumption(engineDto.getFuelConsumption())
            .power(engineDto.getPower())
            .build();
    }

    static EngineDto fromEngineToEngineDto(final Engine engine) {
        return engine == null ? null : EngineDto.builder()
            .capacity(engine.getCapacity())
            .fuel(engine.getFuel())
            .fuelConsumption(engine.getFuelConsumption())
            .power(engine.getPower())
            .build();
    }
}
