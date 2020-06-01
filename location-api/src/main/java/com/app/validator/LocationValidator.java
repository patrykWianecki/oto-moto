package com.app.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.app.model.LocationResponse;

@Component
public class LocationValidator {

  public void validateLocationResponse(LocationResponse locationResponse) {
    if (Objects.isNull(locationResponse)) {
      throw new NullPointerException("Missing location response");
    }

    String countyName = locationResponse.getCountyName();
    String localityName = locationResponse.getLocalityName();
    String voivodeshipName = locationResponse.getVoivodeshipName();
    int radious = locationResponse.getRadius();

    if (Objects.isNull(voivodeshipName) || isNameNotUpperCase(voivodeshipName)) {
      throw new IllegalArgumentException("Voivodeship name is null or in wrong format");
    }
    if (Objects.isNull(countyName) || isNameNotUpperCase(countyName)) {
      throw new IllegalArgumentException("County name is null or in wrong format");
    }
    if (Objects.isNull(localityName) || isNameNotUpperCase(localityName)) {
      throw new IllegalArgumentException("Locality name is null or in wrong format");
    }
    if (radious <= 0) {
      throw new IllegalArgumentException("Radious must be greater than zero");
    }
  }

  private static boolean isNameNotUpperCase(String name) {
    return !name.matches("^[A-Z]+((\\s|-)[A-Z]+)*$");
  }
}
