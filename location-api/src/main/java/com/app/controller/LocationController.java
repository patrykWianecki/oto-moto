package com.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.LocationResponse;
import com.app.model.dto.LocalityDto;
import com.app.service.LocationService;
import com.app.validator.LocationValidator;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/localities")
public class LocationController {

  private final LocationService locationService;
  private final LocationValidator locationValidator;

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LocalityDto>> getAvailableLocalities(
      @RequestBody LocationResponse locationResponse
  ) {
    locationValidator.validateLocationResponse(locationResponse);
    return locationService.createRequest(locationResponse);
  }
}
