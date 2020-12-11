package com.app.data;

import java.util.List;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.LocationResponse;
import com.app.model.Voivodeship;

public interface MockDataForTests {

  String PODKARPACKIE_VOIVODESHIP = "PODKARPACKIE";
  String STALOWOWOLSKI_COUNTY = "STALOWOWOLSKI";
  String STALOWA_WOLA_LOCALITY = "STALOWA WOLA";
  String PYSZNICA_LOCALITY = "PYSZNICA";
  String ZAKLIKOW_LOCALITY = "ZAKLIKÓW";
  long VOIVODESHIP_ID = 100L;
  long COUNTY_ID = 200L;
  long LOCALITY_ID_1 = 300L;
  long LOCALITY_ID_2 = 301L;
  long LOCALITY_ID_3 = 302L;

  static Voivodeship createVoivodeship() {
    return Voivodeship.builder()
        .id(VOIVODESHIP_ID)
        .name(PODKARPACKIE_VOIVODESHIP)
        .build();
  }

  static County createCounty() {
    return County.builder()
        .id(COUNTY_ID)
        .voivodeshipId(VOIVODESHIP_ID)
        .name(STALOWOWOLSKI_COUNTY)
        .build();
  }

  static Locality createLocality() {
    return Locality.builder()
        .id(LOCALITY_ID_1)
        .countyId(COUNTY_ID)
        .longitude(10)
        .latitude(10)
        .name(STALOWA_WOLA_LOCALITY)
        .build();
  }

  static List<Locality> createLocalities() {
    return List.of(
        Locality.builder()
            .id(LOCALITY_ID_1)
            .countyId(COUNTY_ID)
            .name(STALOWA_WOLA_LOCALITY)
            .longitude(10)
            .latitude(10)
            .build(),
        Locality.builder()
            .id(LOCALITY_ID_2)
            .countyId(COUNTY_ID)
            .name(PYSZNICA_LOCALITY)
            .longitude(10.1)
            .latitude(10.1)
            .build(),
        Locality.builder()
            .id(LOCALITY_ID_3)
            .countyId(COUNTY_ID)
            .name(ZAKLIKOW_LOCALITY)
            .longitude(10.11)
            .latitude(10.11)
            .build()
    );
  }

  static LocationResponse createValidLocationResponse() {
    return LocationResponse.builder()
        .voivodeshipName(PODKARPACKIE_VOIVODESHIP)
        .countyName(STALOWOWOLSKI_COUNTY)
        .localityName(STALOWA_WOLA_LOCALITY)
        .radius(25)
        .build();
  }
}
