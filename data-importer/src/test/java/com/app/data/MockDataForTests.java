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
    return "{\n" +
            "          \"voivodeships\": [\n" +
            "            {\n" +
            "              \"id\": \"5e920820cc59bf3c462ba6b4\",\n" +
            "              \"name\": \"PODKARPACKIE\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"id\": \"5e920820cc59bf3c462ba6b5\",\n" +
            "              \"name\": \"MAŁOPOLSKIE\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }";
  }

  static String createEmptyVoivodeshipsJSON() {
    return "{\n" +
            "          \"voivodeships\": []\n" +
            "        }";
  }

  static String createCountiesJSON() {
    return "{\n" +
            "          \"counties\": [\n" +
            "            {\n" +
            "              \"id\": \"5e120820cc59bf3c462ba6b4\",\n" +
            "              \"name\": \"stalowowolski\",\n" +
            "              \"voivodeshipId\": \"5e920820cc59bf3c462ba6b4\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"id\": \"5e130820cc59bf3c462ba6b5\",\n" +
            "              \"name\": \"niżański\",\n" +
            "              \"voivodeshipId\": \"5e920820cc59bf3c462ba6b5\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }";
  }

  static String createEmptyCountiesJSON() {
    return "{\n" +
            "          \"counties\": []\n" +
            "        }";
  }

  static String createLocalitiesJSON() {
    return "{\n" +
            "  \"localities\": [\n" +
            "    {\n" +
            "      \"id\": \"5e920820cc59bf3c462ba6qs\",\n" +
            "      \"countyId\": \"5e120820cc59bf3c462ba6b4\",\n" +
            "      \"distance\": 0,\n" +
            "      \"latitude\": 50.5666644,\n" +
            "      \"longitude\": 22.0499998,\n" +
            "      \"name\": \"Stalowa Wola\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"4e920820cc59bf3c462ba6qs\",\n" +
            "      \"countyId\": \"5e120820cc59bf3c462ba6b4\",\n" +
            "      \"distance\": 0,\n" +
            "      \"latitude\": 50.60389,\n" +
            "      \"longitude\": 22.10667,\n" +
            "      \"name\": \"Jastkowice\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
  }

  static String createEmptyLocalitiesJSON() {
    return "{\n" +
            "  \"localities\": []\n" +
            "}";
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
