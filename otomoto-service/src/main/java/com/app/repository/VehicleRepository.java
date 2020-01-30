package com.app.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Vehicle;

@Repository
public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, BigInteger> {
}