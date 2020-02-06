package com.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.LocationResponse;
import com.app.model.dto.LocalityDto;
import com.app.service.LocationService;
import com.app.validator.LocationValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/localities")
public class LocationController {

  private final LocationService locationService;
  private final LocationValidator locationValidator;

  // @InitBinder
  // private void initBinder(WebDataBinder webDataBinder) {
  //   webDataBinder.addValidators(locationValidator);
  // }

  @GetMapping("/{locationResponse}")
  public ResponseEntity<List<LocalityDto>> getAvailableLocalities(@PathVariable LocationResponse locationResponse) {
    locationValidator.validateLocationResponse(locationResponse);
    List<LocalityDto> localities = locationService.createRequest(locationResponse);

    if (localities.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(localities);
  }
}
