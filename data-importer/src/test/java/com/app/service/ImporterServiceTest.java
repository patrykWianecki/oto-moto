package com.app.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.dto.CountyDto;
import com.app.model.dto.LocalityDto;
import com.app.model.dto.VoivodeshipDto;
import com.app.respository.CountyRepository;
import com.app.respository.LocalityRepository;
import com.app.respository.VoivodeshipRepository;

import static com.app.data.MockDataForTests.*;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ImporterServiceTest {

  @Mock
  private VoivodeshipRepository voivodeshipRepository;
  @Mock
  private CountyRepository countyRepository;
  @Mock
  private LocalityRepository localityRepository;

  @InjectMocks
  private ImporterService jsonImporter;

  @Test
  void should_add_voivodeships_to_database() {
    // given
    when(voivodeshipRepository.saveAll(anyList())).thenReturn(createVoivodeships());

    // when
    List<VoivodeshipDto> voivodeships = jsonImporter.createVoivodeships(createVoivodeshipsJSON());

    // then
    assertNotNull(voivodeships);
    assertEquals(2, voivodeships.size());

    VoivodeshipDto voivodeship1 = voivodeships.get(0);
    assertNotNull(voivodeship1);
    assertEquals(VOIVODESHIP_NAME_1, voivodeship1.getName());

    VoivodeshipDto voivodeship2 = voivodeships.get(1);
    assertNotNull(voivodeship2);
    assertEquals(VOIVODESHIP_NAME_2, voivodeship2.getName());

    verify(voivodeshipRepository, times(1)).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository);
  }

  @Test
  void should_return_empty_list_when_voivodeships_has_no_values() {
    // when
    List<VoivodeshipDto> voivodeships =
        jsonImporter.createVoivodeships(createEmptyVoivodeshipsJSON());

    // then
    assertNotNull(voivodeships);
    assertTrue(voivodeships.isEmpty());

    verify(voivodeshipRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository);
  }

  @Test
  void should_return_empty_list_when_voivodeships_json_is_null() {
    // when
    List<VoivodeshipDto> voivodeships = jsonImporter.createVoivodeships(null);

    // then
    assertNotNull(voivodeships);
    assertTrue(voivodeships.isEmpty());

    verify(voivodeshipRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository);
  }

  @Test
  void should_return_empty_list_when_voivodeships_json_is_empty() {
    // when
    List<VoivodeshipDto> voivodeships = jsonImporter.createVoivodeships(EMPTY);

    // then
    assertNotNull(voivodeships);
    assertTrue(voivodeships.isEmpty());

    verify(voivodeshipRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository);
  }

  @Test
  void should_add_counties_to_database() {
    // given
    when(voivodeshipRepository.findVoivodeshipByName(anyString()))
        .thenReturn(Optional.of(createVoivodeship()));
    when(countyRepository.saveAll(anyList())).thenReturn(createCounties());

    // when
    List<CountyDto> counties = jsonImporter.createCountiesInVoivodeship(
        VOIVODESHIP_NAME_1, createCountiesJSON()
    );

    // then
    assertNotNull(counties);
    assertEquals(2, counties.size());

    CountyDto county1 = counties.get(0);
    assertNotNull(county1);
    assertEquals(COUNTY_NAME_1, county1.getName());
    assertEquals(VOIVODESHIP_ID_1, county1.getVoivodeshipId());

    CountyDto county2 = counties.get(1);
    assertNotNull(county2);
    assertEquals(COUNTY_NAME_2, county2.getName());
    assertEquals(VOIVODESHIP_ID_1, county2.getVoivodeshipId());

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, times(1)).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository);
  }

  @Test
  void should_return_empty_list_when_voivodeship_does_not_exist() {
    // when
    List<CountyDto> counties = jsonImporter.createCountiesInVoivodeship(
        VOIVODESHIP_NAME_1, createEmptyCountiesJSON()
    );

    // then
    assertNotNull(counties);
    assertTrue(counties.isEmpty());

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository);
  }

  @Test
  void should_return_empty_list_when_counties_json_is_null() {
    // given
    when(voivodeshipRepository.findVoivodeshipByName(anyString()))
        .thenReturn(Optional.of(createVoivodeship()));

    // when
    List<CountyDto> counties = jsonImporter.createCountiesInVoivodeship(VOIVODESHIP_NAME_1, null);

    // then
    assertNotNull(counties);
    assertTrue(counties.isEmpty());

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository);
  }

  @Test
  void should_return_empty_list_when_counties_json_is_empty() {
    // given
    when(voivodeshipRepository.findVoivodeshipByName(anyString()))
        .thenReturn(Optional.of(createVoivodeship()));

    // when
    List<CountyDto> counties = jsonImporter.createCountiesInVoivodeship(VOIVODESHIP_NAME_1, EMPTY);

    // then
    assertNotNull(counties);
    assertTrue(counties.isEmpty());

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository);
  }

  @Test
  void should_add_localities_to_database() {
    // given
    when(countyRepository.findCountyByName(anyString())).thenReturn(Optional.of(createCounty()));
    when(localityRepository.saveAll(anyList())).thenReturn(createLocalities());

    // when
    List<LocalityDto> localities = jsonImporter.createLocalitiesInCounty(
        COUNTY_NAME_1, createLocalitiesJSON()
    );

    // then
    assertNotNull(localities);
    assertEquals(2, localities.size());

    LocalityDto locality1 = localities.get(0);
    assertNotNull(locality1);
    assertEquals(LOCALITY_NAME_1, locality1.getName());
    assertEquals(LOCALITY_DISTANCE, locality1.getDistance());
    assertEquals(LOCALITY_LONGITUDE_1, locality1.getLongitude());
    assertEquals(LOCALITY_LATITUDE_1, locality1.getLatitude());
    assertEquals(COUNTY_ID_1, locality1.getCountyId());

    LocalityDto locality2 = localities.get(1);
    assertNotNull(locality2);
    assertEquals(LOCALITY_NAME_2, locality2.getName());
    assertEquals(LOCALITY_DISTANCE, locality2.getDistance());
    assertEquals(LOCALITY_LONGITUDE_2, locality2.getLongitude());
    assertEquals(LOCALITY_LATITUDE_2, locality2.getLatitude());
    assertEquals(COUNTY_ID_1, locality2.getCountyId());

    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, times(1)).saveAll(anyCollection());
    verifyNoMoreInteractions(countyRepository, localityRepository);
  }

  @Test
  void should_return_empty_list_when_county_does_not_exist() {
    // when
    List<LocalityDto> localities = jsonImporter.createLocalitiesInCounty(
        COUNTY_NAME_1, createEmptyLocalitiesJSON()
    );

    // then
    assertNotNull(localities);
    assertTrue(localities.isEmpty());

    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(countyRepository, localityRepository);
  }

  @Test
  void should_return_empty_list_when_localities_json_is_null() {
    // given
    when(countyRepository.findCountyByName(anyString())).thenReturn(Optional.of(createCounty()));

    // when
    List<LocalityDto> localities = jsonImporter.createLocalitiesInCounty(COUNTY_NAME_1, null);

    // then
    assertNotNull(localities);
    assertTrue(localities.isEmpty());

    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(countyRepository, localityRepository);
  }

  @Test
  void should_return_empty_list_when_localities_json_is_empty() {
    // given
    when(countyRepository.findCountyByName(anyString())).thenReturn(Optional.of(createCounty()));

    // when
    List<LocalityDto> localities = jsonImporter.createLocalitiesInCounty(COUNTY_NAME_1, EMPTY);

    // then
    assertNotNull(localities);
    assertTrue(localities.isEmpty());

    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, never()).saveAll(anyCollection());
    verifyNoMoreInteractions(countyRepository, localityRepository);
  }
}