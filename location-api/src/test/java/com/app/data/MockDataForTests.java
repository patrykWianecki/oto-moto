package com.app.data;

import java.util.List;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.LocationResponse;
import com.app.model.Voivodeship;

public class MockDataForTests {

  public static final String PODKARPACKIE_VOIVODESHIP = "PODKARPACKIE";
  public static final String STALOWOWOLSKI_COUNTY = "STALOWOWOLSKI";
  public static final String STALOWA_WOLA_LOCALITY = "STALOWA WOLA";
  public static final String PYSZNICA_LOCALITY = "PYSZNICA";
  public static final String ZAKLIKOW_LOCALITY = "ZAKLIKÓW";

  public static Voivodeship createVoivodeship() {
    return Voivodeship.builder()
        .id("11")
        .name(PODKARPACKIE_VOIVODESHIP)
        .build();
  }

  public static County createCounty() {
    return County.builder()
        .id("11-11")
        .voivodeshipId("11")
        .name(STALOWOWOLSKI_COUNTY)
        .build();
  }

  public static Locality createLocality() {
    return Locality.builder()
        .id("11-11-11")
        .countyId("11-11")
        .longitude(10)
        .latitude(10)
        .name(STALOWA_WOLA_LOCALITY)
        .build();
  }

  public static List<Locality> createLocalities() {
    return List.of(
        Locality.builder()
            .id("11-11-11")
            .countyId("11-11")
            .name(STALOWA_WOLA_LOCALITY)
            .longitude(10)
            .latitude(10)
            .build(),
        Locality.builder()
            .id("11-11-12")
            .countyId("11-11")
            .name(PYSZNICA_LOCALITY)
            .longitude(10.1)
            .latitude(10.1)
            .build(),
        Locality.builder()
            .id("11-11-13")
            .countyId("11-11")
            .name(ZAKLIKOW_LOCALITY)
            .longitude(10.11)
            .latitude(10.11)
            .build()
    );
  }

  public static LocationResponse createValidLocationResponse() {
    return LocationResponse.builder()
        .voivodeshipName(PODKARPACKIE_VOIVODESHIP)
        .countyName(STALOWOWOLSKI_COUNTY)
        .localityName(STALOWA_WOLA_LOCALITY)
        .radius(25)
        .build();
  }
}
