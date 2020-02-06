package com.app.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.app.model.LocationResponse;

@Component
public class LocationValidator {

  public void validateLocationResponse(LocationResponse locationResponse) {
    Optional.ofNullable(locationResponse).orElseThrow(() -> new NullPointerException("Missing location response"));

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

  //    public boolean validateGeometry(final Geometry geometry) {
  //        return geometry != null && validateCoordinates(geometry.getCoordinates());
  //    }
  //
  //    public boolean validateProperties(final Properties properties) {
  //        return !isBlank(properties.getTitle());
  //    }
  //
  //    private static boolean validateCoordinates(final List<Double> coordinates) {
  //        return CollectionUtils.isNotEmpty(coordinates) &&
  //            validateLongitude(Optional.ofNullable(coordinates.get(0)).orElse(181.0)) &&
  //            validateLatitude(Optional.ofNullable(coordinates.get(1)).orElse(-91.0));
  //    }
  //
  //    private static boolean validateLongitude(final Double longitude) {
  //        return longitude != null && longitude <= 180 && longitude >= -180;
  //    }
  //
  //    private static boolean validateLatitude(final Double latitude) {
  //        return latitude != null && latitude <= 90 && latitude >= -90;
  //    }
}
