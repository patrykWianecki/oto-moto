package com.app.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.model.LocationResponse;

@Component
public class LocationValidator {

  public void validateLocationResponse(LocationResponse locationResponse) {
    Optional.ofNullable(locationResponse)
        .orElseThrow(() -> new NullPointerException("Missing location response"));

    String countyName = locationResponse.getCountyName();
    String localityName = locationResponse.getLocalityName();
    String voivodeshipName = locationResponse.getVoivodeshipName();
    int radious = locationResponse.getRadius();

    if (voivodeshipName == null || isNotNameUpperCase(voivodeshipName)) {
      throw new IllegalArgumentException("Voivodeship name is null or in wrong format");
    }
    if (countyName == null || isNotNameUpperCase(countyName)) {
      throw new IllegalArgumentException("County name is null or in wrong format");
    }
    if (localityName == null || isNotNameUpperCase(localityName)) {
      throw new IllegalArgumentException("Locality name is null or in wrong format");
    }
    if (radious <= 0) {
      throw new IllegalArgumentException("Radious must be greater than zero");
    }
  }

  private static boolean isNotNameUpperCase(String name) {
    return !name.equals(name.toUpperCase());
  }
}
