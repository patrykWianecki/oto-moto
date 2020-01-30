package com.app.service;

import java.math.BigInteger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.model.Vehicle;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final WebClient webClient;

    public VehicleService() {
        webClient = WebClient.builder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl("http://localhost:8080/vehicles")
            .build();
    }

    public Mono<Vehicle> addVehicle(final Vehicle vehicle) {
        return webClient
            .post()
            .body(BodyInserters.fromValue(vehicle))
            .retrieve()
            .bodyToMono(Vehicle.class);
    }

    public Mono<Vehicle> updateVehicle(final BigInteger id, final Vehicle vehicle) {
        return webClient
            .put()
            .uri("/{id}", id)
            .body(BodyInserters.fromValue(vehicle))
            .retrieve()
            .bodyToMono(Vehicle.class);
    }

    public Mono<Vehicle> findVehicleById(final BigInteger id) {
        return webClient
            .get()
            .uri("/{id}", id)
            .retrieve()
            .bodyToMono(Vehicle.class);
    }

    public Flux<Vehicle> findAllVehicles() {
        return webClient
            .get()
            .retrieve()
            .bodyToFlux(Vehicle.class);
    }

    public Mono<Void> removeVehicleById(final BigInteger id) {
        return webClient
            .delete()
            .uri("/{id}", id)
            .retrieve()
            .bodyToMono(Void.class);
    }

    public Flux<Void> removeAllVehicles() {
        return webClient
            .delete()
            .retrieve()
            .bodyToFlux(Void.class);
    }
}
