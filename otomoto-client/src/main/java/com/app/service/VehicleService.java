package com.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.model.VehicleDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.*;

@RequiredArgsConstructor
@Service
public class VehicleService {

  private static final String URL = "http://localhost:8080/vehicles";

  private final WebClient webClient;

  public VehicleService() {
    webClient = WebClient.builder()
        .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
        .baseUrl(URL)
        .build();
  }

  public Mono<ResponseEntity<VehicleDto>> addVehicle(final VehicleDto vehicle) {
    return webClient
        .post()
        .body(BodyInserters.fromValue(vehicle))
        .retrieve()
        .bodyToMono(VehicleDto.class)
        .map(ResponseEntity::ok);
  }

  public Mono<ResponseEntity<VehicleDto>> updateVehicle(final String vehicleId, final VehicleDto vehicle) {
    return webClient
        .put()
        .uri("/{vehicleId}", vehicleId)
        .body(BodyInserters.fromValue(vehicle))
        .retrieve()
        .bodyToMono(VehicleDto.class)
        .map(ResponseEntity::ok);
  }

  public Mono<ResponseEntity<VehicleDto>> findVehicleById(final String vehicleId) {
    return webClient
        .get()
        .uri("/{vehicleId}", vehicleId)
        .retrieve()
        .bodyToMono(VehicleDto.class)
        .map(ResponseEntity::ok);
  }

  public Flux<VehicleDto> findAllVehicles() {
    return webClient
        .get()
        .retrieve()
        .bodyToFlux(VehicleDto.class);
  }

  public Mono<ResponseEntity<Void>> removeVehicleById(final String vehicleId) {
    return webClient
        .delete()
        .uri("/{vehicleId}", vehicleId)
        .retrieve()
        .bodyToMono(Void.class)
        .map(ResponseEntity::ok);
  }

  public Flux<ResponseEntity<Void>> removeAllVehicles() {
    return webClient
        .delete()
        .retrieve()
        .bodyToFlux(Void.class)
        .map(ResponseEntity::ok);
  }
}
