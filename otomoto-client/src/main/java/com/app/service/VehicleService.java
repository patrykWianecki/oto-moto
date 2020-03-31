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
  private static final String VEHICLE_ID_PARAM = "?vehicleId={vehicleId}";
  private static final String ALL = "/all";

  private final WebClient webClient;

  public VehicleService() {
    webClient = WebClient.builder()
        .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
        .baseUrl(URL)
        .build();
  }

  public Mono<ResponseEntity<VehicleDto>> addVehicle(final VehicleDto vehicleDto) {
    return webClient
        .post()
        .body(BodyInserters.fromValue(vehicleDto))
        .retrieve()
        .bodyToMono(VehicleDto.class)
        .map(ResponseEntity::ok);
  }

  public Mono<ResponseEntity<VehicleDto>> updateVehicle(final String vehicleId,
      final VehicleDto vehicleDto) {
    return webClient
        .put()
        .uri(VEHICLE_ID_PARAM, vehicleId)
        .body(BodyInserters.fromValue(vehicleDto))
        .retrieve()
        .bodyToMono(VehicleDto.class)
        .map(ResponseEntity::ok);
  }

  public Mono<ResponseEntity<VehicleDto>> findVehicleById(final String vehicleId) {
    return webClient
        .get()
        .uri(VEHICLE_ID_PARAM, vehicleId)
        .retrieve()
        .bodyToMono(VehicleDto.class)
        .map(ResponseEntity::ok);
  }

  public Flux<VehicleDto> findAllVehicles() {
    return webClient
        .get()
        .uri(ALL)
        .retrieve()
        .bodyToFlux(VehicleDto.class);
  }

  public Mono<ResponseEntity<Void>> removeVehicleById(final String vehicleId) {
    return webClient
        .delete()
        .uri(VEHICLE_ID_PARAM, vehicleId)
        .retrieve()
        .bodyToMono(Void.class)
        .map(ResponseEntity::ok);
  }

  public Mono<ResponseEntity<Void>> removeAllVehicles() {
    return webClient
        .delete()
        .uri(ALL)
        .retrieve()
        .bodyToMono(Void.class)
        .map(ResponseEntity::ok);
  }
}
