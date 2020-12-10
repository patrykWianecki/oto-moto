package com.app.data;

import java.util.List;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;

public interface MockDataForTests {

  String COUNTY_NAME_1 = "STALOWOWOLSKI";
  String COUNTY_NAME_2 = "NIŻAŃSKI";
  String LOCALITY_NAME_1 = "STALOWA WOLA";
  String LOCALITY_NAME_2 = "JASTKOWICE";
  double LOCALITY_LATITUDE_1 = 50.5666644;
  double LOCALITY_LATITUDE_2 = 50.60389;
  double LOCALITY_LONGITUDE_1 = 22.0499998;
  double LOCALITY_LONGITUDE_2 = 22.10667;
  int LOCALITY_DISTANCE = 0;
  long COUNTY_ID_1 = 5120820L;
  long VOIVODESHIP_ID_1 = 5920820L;
  String VOIVODESHIP_NAME_1 = "PODKARPACKIE";
  String VOIVODESHIP_NAME_2 = "MAŁOPOLSKIE";

  static String createVoivodeshipsJSON() {
    return """
        {
          "voivodeships": [
            {
              "name": "PODKARPACKIE"
            },
            {
              "name": "MAŁOPOLSKIE"
            }
          ]
        }
        """;
  }

  static String createEmptyVoivodeshipsJSON() {
    return """
        {
          "voivodeships": []
        }
        """;
  }

  static String createCountiesJSON() {
    return """
        {
          "counties": [
            {
              "name": "STALOWOWOLSKI"
            },
            {
              "name": "NIŻAŃSKI"
            }
          ]
        }
        """;
  }

  static String createEmptyCountiesJSON() {
    return """
        {
          "counties": []
        }
        """;
  }

  static String createLocalitiesJSON() {
    return """
        {
          "localities": [
            {
              "distance": 0,
              "latitude": 50.5666644,
              "longitude": 22.0499998,
              "name": "STALOWA WOLA"
            },
            {
              "distance": 0,
              "latitude": 50.60389,
              "longitude": 22.10667,
              "name": "JASTKOWICE"
            }
          ]
        }
        """;
  }

  static String createEmptyLocalitiesJSON() {
    return """
        {
          "localities": []
        }
        """;
  }

  static Voivodeship createVoivodeship() {
    return Voivodeship.builder()
        .name(VOIVODESHIP_NAME_1)
        .build();
  }

  static List<Voivodeship> createVoivodeships() {
    return List.of(
        Voivodeship.builder()
            .name(VOIVODESHIP_NAME_1)
            .build(),
        Voivodeship.builder()
            .name(VOIVODESHIP_NAME_2)
            .build()
    );
  }

  static County createCounty() {
    return County.builder()
        .name(COUNTY_NAME_1)
        .build();
  }

  static List<County> createCounties() {
    return List.of(
        County.builder()
            .name(COUNTY_NAME_1)
            .voivodeshipId(VOIVODESHIP_ID_1)
            .build(),
        County.builder()
            .voivodeshipId(VOIVODESHIP_ID_1)
            .name(COUNTY_NAME_2)
            .build()
    );
  }

  static List<Locality> createLocalities() {
    return List.of(
        Locality.builder()
            .distance(LOCALITY_DISTANCE)
            .latitude(LOCALITY_LATITUDE_1)
            .longitude(LOCALITY_LONGITUDE_1)
            .name(LOCALITY_NAME_1)
            .countyId(COUNTY_ID_1)
            .build(),
        Locality.builder()
            .distance(LOCALITY_DISTANCE)
            .latitude(LOCALITY_LATITUDE_2)
            .longitude(LOCALITY_LONGITUDE_2)
            .name(LOCALITY_NAME_2)
            .countyId(COUNTY_ID_1)
            .build()
    );
  }
}
