package com.app.importer;

import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.app.data.MockDataForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ImporterServiceTest {

  private static final String BLANK_JSON = "";

  @Mock
  private MongoDatabase mongoDatabase;
  @Mock
  private MongoCollection<Document> documentMongoCollection;

  @InjectMocks
  private final ImporterService jsonImporter = new ImporterService("localhost", "test_db", 8888);

  @BeforeEach
  public void setUp() {
    when(mongoDatabase.getCollection(anyString())).thenReturn(documentMongoCollection);
    doNothing().when(documentMongoCollection).insertMany(anyList());
  }

  @Test
  void should_add_voivodeships_to_database() {
    // when
    List<Voivodeship> voivodeships = jsonImporter.createVoivodeships(createVoivodeshipsJSON());

    // then
    assertNotNull(voivodeships);
    assertEquals(2, voivodeships.size());

    Voivodeship voivodeship1 = voivodeships.get(0);
    assertNotNull(voivodeship1);
    assertEquals(VOIVODESHIP_ID_1, voivodeship1.getId());
    assertEquals(VOIVODESHIP_NAME_1, voivodeship1.getName());

    Voivodeship voivodeship2 = voivodeships.get(1);
    assertNotNull(voivodeship2);
    assertEquals(VOIVODESHIP_ID_2, voivodeship2.getId());
    assertEquals(VOIVODESHIP_NAME_2, voivodeship2.getName());
  }

  @Test
  void should_return_empty_list_when_voivodeships_has_no_values() {
    // when
    List<Voivodeship> voivodeships = jsonImporter.createVoivodeships(createEmptyVoivodeshipsJSON());

    // then
    assertNotNull(voivodeships);
    assertTrue(voivodeships.isEmpty());
  }

  @Test
  void should_return_empty_list_when_voivodeships_json_is_null() {
    // when
    List<Voivodeship> voivodeships = jsonImporter.createVoivodeships(null);

    // then
    assertNotNull(voivodeships);
    assertTrue(voivodeships.isEmpty());
  }

  @Test
  void should_return_empty_list_when_voivodeships_json_is_empty() {
    // when
    List<Voivodeship> voivodeships = jsonImporter.createVoivodeships(BLANK_JSON);

    // then
    assertNotNull(voivodeships);
    assertTrue(voivodeships.isEmpty());
  }

  @Test
  void should_add_counties_to_database() {
    // when
    List<County> counties = jsonImporter.createCounties(createCountiesJSON());

    // then
    assertNotNull(counties);
    assertEquals(2, counties.size());

    County county1 = counties.get(0);
    assertNotNull(county1);
    assertEquals(COUNTY_ID_1, county1.getId());
    assertEquals(COUNTY_NAME_1, county1.getName());
    assertEquals(VOIVODESHIP_ID_1, county1.getVoivodeshipId());

    County county2 = counties.get(1);
    assertNotNull(county2);
    assertEquals(COUNTY_ID_2, county2.getId());
    assertEquals(COUNTY_NAME_2, county2.getName());
    assertEquals(VOIVODESHIP_ID_2, county2.getVoivodeshipId());
  }

  @Test
  void should_return_empty_list_when_counties_has_no_values() {
    // when
    List<County> counties = jsonImporter.createCounties(createEmptyCountiesJSON());

    // then
    assertNotNull(counties);
    assertTrue(counties.isEmpty());
  }

  @Test
  void should_return_empty_list_when_counties_json_is_null() {
    // when
    List<County> counties = jsonImporter.createCounties(null);

    // then
    assertNotNull(counties);
    assertTrue(counties.isEmpty());
  }

  @Test
  void should_return_empty_list_when_counties_json_is_empty() {
    // when
    List<County> counties = jsonImporter.createCounties(BLANK_JSON);

    // then
    assertNotNull(counties);
    assertTrue(counties.isEmpty());
  }

  @Test
  void should_add_localities_to_database() {
    // when
    List<Locality> localities = jsonImporter.createLocalities(createLocalitiesJSON());

    // then
    assertNotNull(localities);
    assertEquals(2, localities.size());

    Locality locality1 = localities.get(0);
    assertNotNull(locality1);
    assertEquals(LOCALITY_ID_1, locality1.getId());
    assertEquals(LOCALITY_NAME_1, locality1.getName());
    assertEquals(COUNTY_ID_1, locality1.getCountyId());
    assertEquals(LOCALITY_DISTANCE, locality1.getDistance());
    assertEquals(LOCALITY_LONGITUDE_1, locality1.getLongitude());
    assertEquals(LOCALITY_LATITUDE_1, locality1.getLatitude());

    Locality locality2 = localities.get(1);
    assertNotNull(locality2);
    assertEquals(LOCALITY_ID_2, locality2.getId());
    assertEquals(LOCALITY_NAME_2, locality2.getName());
    assertEquals(COUNTY_ID_1, locality2.getCountyId());
    assertEquals(LOCALITY_DISTANCE, locality2.getDistance());
    assertEquals(LOCALITY_LONGITUDE_2, locality2.getLongitude());
    assertEquals(LOCALITY_LATITUDE_2, locality2.getLatitude());
  }

  @Test
  void should_return_empty_list_when_localities_has_no_values() {
    // when
    List<Locality> localities = jsonImporter.createLocalities(createEmptyLocalitiesJSON());

    // then
    assertNotNull(localities);
    assertTrue(localities.isEmpty());
  }

  @Test
  void should_return_empty_list_when_localities_json_is_null() {
    // when
    List<Locality> localities = jsonImporter.createLocalities(null);

    // then
    assertNotNull(localities);
    assertTrue(localities.isEmpty());
  }

  @Test
  void should_return_empty_list_when_localities_json_is_empty() {
    // when
    List<Locality> localities = jsonImporter.createLocalities(BLANK_JSON);

    // then
    assertNotNull(localities);
    assertTrue(localities.isEmpty());
  }
}