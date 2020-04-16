package com.app.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.dto.LocalityDto;
import com.app.repository.CountyRepository;
import com.app.repository.LocalityRepository;
import com.app.repository.VoivodeshipRepository;

import static com.app.data.MockDataForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class LocationServiceTest {

  @Mock
  private CountyRepository countyRepository;
  @Mock
  private LocalityRepository localityRepository;
  @Mock
  private VoivodeshipRepository voivodeshipRepository;

  @InjectMocks
  private LocationService locationService;

  @Test
  void should_find_all_matching_localities() {
    // given
    when(voivodeshipRepository.findByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(createVoivodeship()));
    when(countyRepository.findByName(STALOWOWOLSKI_COUNTY)).thenReturn(Optional.of(createCounty()));
    when(localityRepository.findByName(STALOWA_WOLA_LOCALITY))
        .thenReturn(Optional.of(createLocality()));
    when(localityRepository.findAll()).thenReturn(createLocalities());

    // when
    List<LocalityDto> localities = locationService.createRequest(createValidLocationResponse());

    // then
    assertNotNull(localities);
    assertEquals(3, localities.size());

    LocalityDto firstLocality = localities.get(0);
    assertEquals(STALOWA_WOLA_LOCALITY, firstLocality.getName());
    assertEquals(0, firstLocality.getDistance());

    LocalityDto secondLocality = localities.get(1);
    assertEquals(PYSZNICA_LOCALITY, secondLocality.getName());
    assertEquals(16, secondLocality.getDistance());

    LocalityDto thirdLocality = localities.get(2);
    assertEquals(ZAKLIKOW_LOCALITY, thirdLocality.getName());
    assertEquals(17, thirdLocality.getDistance());
  }

  @Test
  void should_return_empty_list_of_localities() {
    // given
    when(voivodeshipRepository.findByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(createVoivodeship()));
    when(countyRepository.findByName(STALOWOWOLSKI_COUNTY)).thenReturn(Optional.of(createCounty()));
    when(localityRepository.findByName(STALOWA_WOLA_LOCALITY))
        .thenReturn(Optional.of(createLocality()));

    // when
    List<LocalityDto> localities = locationService.createRequest(createValidLocationResponse());

    // then
    assertNotNull(localities);
    assertEquals(0, localities.size());
  }

  @Test
  void should_throw_exception_when_voivodeship_does_not_exist_in_database() {
    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> locationService.createRequest(createValidLocationResponse()));

    // then
    assertEquals("Voivodeship with given name does not exist " + PODKARPACKIE_VOIVODESHIP,
        nullPointerException.getMessage());
  }

  @Test
  void should_throw_exception_when_county_does_not_exist_in_database() {
    // given
    when(voivodeshipRepository.findByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(createVoivodeship()));

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> locationService.createRequest(createValidLocationResponse()));

    // then
    assertEquals("County with given name does not exist " + STALOWOWOLSKI_COUNTY,
        nullPointerException.getMessage());
  }

  @Test
  void should_throw_exception_when_locality_does_not_exist_in_database() {
    // given
    when(voivodeshipRepository.findByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(createVoivodeship()));
    when(countyRepository.findByName(STALOWOWOLSKI_COUNTY)).thenReturn(Optional.of(createCounty()));

    // when
    NullPointerException nullPointerException = assertThrows(NullPointerException.class,
        () -> locationService.createRequest(createValidLocationResponse()));

    // then
    assertEquals("Locality with given name does not exist " + STALOWA_WOLA_LOCALITY,
        nullPointerException.getMessage());
  }
}