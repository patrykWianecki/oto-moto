package com.app.data;

import java.util.List;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;

public interface MockDataForTests {

  String COUNTY_ID_1 = "5e120820cc59bf3c462ba6b4";
  String COUNTY_ID_2 = "5e130820cc59bf3c462ba6b5";
  String COUNTY_NAME_1 = "stalowowolski";
  String COUNTY_NAME_2 = "niżański";

  String LOCALITY_ID_1 = "5e920820cc59bf3c462ba6qs";
  String LOCALITY_ID_2 = "4e920820cc59bf3c462ba6qs";
  String LOCALITY_NAME_1 = "Stalowa Wola";
  String LOCALITY_NAME_2 = "Jastkowice";
  double LOCALITY_LATITUDE_1 = 50.5666644;
  double LOCALITY_LATITUDE_2 = 50.60389;
  double LOCALITY_LONGITUDE_1 = 22.0499998;
  double LOCALITY_LONGITUDE_2 = 22.10667;
  int LOCALITY_DISTANCE = 0;

  String VOIVODESHIP_ID_1 = "5e920820cc59bf3c462ba6b4";
  String VOIVODESHIP_ID_2 = "5e920820cc59bf3c462ba6b5";
  String VOIVODESHIP_NAME_1 = "PODKARPACKIE";
  String VOIVODESHIP_NAME_2 = "MAŁOPOLSKIE";

  static String createVoivodeshipsJSON() {
    return """
        {
          "voivodeships": [
            {
              "id": "5e920820cc59bf3c462ba6b4",
              "name": "PODKARPACKIE"
            },
            {
              "id": "5e920820cc59bf3c462ba6b5",
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
              "id": "5e120820cc59bf3c462ba6b4",
              "name": "stalowowolski",
              "voivodeshipId": "5e920820cc59bf3c462ba6b4"
            },
            {
              "id": "5e130820cc59bf3c462ba6b5",
              "name": "niżański",
              "voivodeshipId": "5e920820cc59bf3c462ba6b5"
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
              "id": "5e920820cc59bf3c462ba6qs",
              "countyId": "5e120820cc59bf3c462ba6b4",
              "distance": 0,
              "latitude": 50.5666644,
              "longitude": 22.0499998,
              "name": "Stalowa Wola"
            },
            {
              "id": "4e920820cc59bf3c462ba6qs",
              "countyId": "5e120820cc59bf3c462ba6b4",
              "distance": 0,
              "latitude": 50.60389,
              "longitude": 22.10667,
              "name": "Jastkowice"
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

  static List<Voivodeship> createVoivodeships() {
    return List.of(
        Voivodeship.builder()
            .id(VOIVODESHIP_ID_1)
            .name(VOIVODESHIP_NAME_1)
            .build(),
        Voivodeship.builder()
            .id(VOIVODESHIP_ID_2)
            .name(VOIVODESHIP_NAME_2)
            .build()
    );
  }

  static List<County> createCounties() {
    return List.of(
        County.builder()
            .id(COUNTY_ID_1)
            .name(COUNTY_NAME_1)
            .voivodeshipId(VOIVODESHIP_ID_1)
            .build(),
        County.builder()
            .id(COUNTY_ID_2)
            .name(COUNTY_NAME_2)
            .voivodeshipId(VOIVODESHIP_ID_2)
            .build()
    );
  }

  static List<Locality> createLocalities() {
    return List.of(
        Locality.builder()
            .id(LOCALITY_ID_1)
            .countyId(COUNTY_ID_1)
            .distance(LOCALITY_DISTANCE)
            .latitude(LOCALITY_LATITUDE_1)
            .longitude(LOCALITY_LONGITUDE_1)
            .name(LOCALITY_NAME_1)
            .build(),
        Locality.builder()
            .id(LOCALITY_ID_2)
            .countyId(COUNTY_ID_1)
            .distance(LOCALITY_DISTANCE)
            .latitude(LOCALITY_LATITUDE_2)
            .longitude(LOCALITY_LONGITUDE_2)
            .name(LOCALITY_NAME_2)
            .build()
    );
  }
}
