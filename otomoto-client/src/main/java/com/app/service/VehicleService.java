package com.app.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.model.VehicleDto;

import lombok.RequiredArgsConstructor;

import static com.app.constants.ClientConstants.*;
import static org.springframework.http.HttpMethod.*;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private static final String URL = "http://localhost:8080/vehicles";

  private final RestTemplate restTemplate;

  public VehicleDto addVehicle(final VehicleDto vehicleDto) {
    HttpEntity<VehicleDto> entity = new HttpEntity<>(vehicleDto, new HttpHeaders());
    ResponseEntity<VehicleDto> response = restTemplate.postForEntity(URL, entity, VehicleDto.class);
    return response.getBody();
  }

  public VehicleDto updateVehicle(final VehicleDto vehicleDto) {
    final String vehicleId = vehicleDto.getId();
    HttpEntity<VehicleDto> entity = new HttpEntity<>(vehicleDto, new HttpHeaders());
    ResponseEntity<VehicleDto> response =
        restTemplate.exchange(URL + VEHICLE_ID_QUERY + vehicleId, PUT, entity, VehicleDto.class);
    return response.getBody();
  }

  public VehicleDto findVehicleById(final String vehicleId) {
    ResponseEntity<VehicleDto> response =
        restTemplate.getForEntity(URL + VEHICLE_ID_QUERY + vehicleId, VehicleDto.class);
    return response.getBody();
  }

  public Page<VehicleDto> findPaginatedVehicles(Pageable pageable) {
    List<VehicleDto> vehicles = findAllVehicles();
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;

    List<VehicleDto> vehiclesOnPage;
    if (vehicles.size() < startItem) {
      vehiclesOnPage = Collections.emptyList();
    } else {
      int toIndex = Math.min(startItem + pageSize, vehicles.size());
      vehiclesOnPage = vehicles.subList(startItem, toIndex);
    }

    return new PageImpl<>(vehiclesOnPage, PageRequest.of(currentPage, pageSize), vehicles.size());
  }

  private List<VehicleDto> findAllVehicles() {
    HttpEntity<VehicleDto> entity = new HttpEntity<>(null, new HttpHeaders());
    ResponseEntity<VehicleDto[]> response =
        restTemplate.exchange(URL + "/all", GET, entity, VehicleDto[].class);
    final VehicleDto[] body = response.getBody();
    if (body == null || body.length == 0) {
      return Collections.emptyList();
    }
    return Arrays.asList(body);
  }

  public VehicleDto removeVehicleById(final String vehicleId) {
    HttpEntity<VehicleDto> entity = new HttpEntity<>(null, new HttpHeaders());
    ResponseEntity<VehicleDto> response =
        restTemplate.exchange(URL + VEHICLE_ID_QUERY + vehicleId, DELETE, entity, VehicleDto.class);
    return response.getBody();
  }

  public List<VehicleDto> removeAllVehicles() {
    HttpEntity<VehicleDto> entity = new HttpEntity<>(null, new HttpHeaders());
    ResponseEntity<VehicleDto[]> response =
        restTemplate.exchange(URL, DELETE, entity, VehicleDto[].class);
    final VehicleDto[] body = response.getBody();
    if (body == null || body.length == 0) {
      return Collections.emptyList();
    }
    return Arrays.asList(body);
  }
}
