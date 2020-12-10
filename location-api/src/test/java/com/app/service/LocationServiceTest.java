package com.app.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.LocationResponse;
import com.app.model.Voivodeship;
import com.app.model.dto.LocalityDto;
import com.app.repository.CountyRepository;
import com.app.repository.LocalityRepository;
import com.app.repository.VoivodeshipRepository;

import static com.app.data.MockDataForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
class LocationServiceTest {

  private static final LocationResponse LOCATION_RESPONSE = createValidLocationResponse();
  private static final Voivodeship VOIVODESHIP = createVoivodeship();
  private static final County COUNTY = createCounty();
  private static final Locality LOCALITY = createLocality();

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
    when(voivodeshipRepository.findVoivodeshipByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(VOIVODESHIP));
    when(countyRepository.findCountyByName(STALOWOWOLSKI_COUNTY))
        .thenReturn(Optional.of(COUNTY));
    when(localityRepository.findLocalityByName(STALOWA_WOLA_LOCALITY))
        .thenReturn(Optional.of(LOCALITY));
    when(localityRepository.findAll()).thenReturn(createLocalities());

    // when
    ResponseEntity<List<LocalityDto>> response = locationService.createRequest(LOCATION_RESPONSE);

    // then
    assertNotNull(response);
    assertEquals(OK, response.getStatusCode());

    List<LocalityDto> localities = response.getBody();
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

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, times(1)).findLocalityByName(anyString());
    verify(localityRepository, times(1)).findAll();
    verify(localityRepository, times(3)).save(any(Locality.class));
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository, localityRepository);
  }

  @Test
  void should_return_empty_list_of_localities() {
    // given
    when(voivodeshipRepository.findVoivodeshipByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(VOIVODESHIP));
    when(countyRepository.findCountyByName(STALOWOWOLSKI_COUNTY))
        .thenReturn(Optional.of(COUNTY));
    when(localityRepository.findLocalityByName(STALOWA_WOLA_LOCALITY))
        .thenReturn(Optional.of(LOCALITY));

    // when
    ResponseEntity<List<LocalityDto>> response = locationService.createRequest(LOCATION_RESPONSE);

    // then
    assertNotNull(response);
    assertEquals(NO_CONTENT, response.getStatusCode());

    List<LocalityDto> localities = response.getBody();
    assertNull(localities);

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, times(1)).findLocalityByName(anyString());
    verify(localityRepository, times(1)).findAll();
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository, localityRepository);
  }

  @Test
  void should_throw_exception_when_voivodeship_does_not_exist_in_database() {
    // when
    NullPointerException exception = assertThrows(
        NullPointerException.class, () -> locationService.createRequest(LOCATION_RESPONSE)
    );

    // then
    assertEquals(
        "Voivodeship with given name does not exist " + PODKARPACKIE_VOIVODESHIP,
        exception.getMessage()
    );

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository, localityRepository);
  }

  @Test
  void should_throw_exception_when_county_does_not_exist_in_database() {
    // given
    when(voivodeshipRepository.findVoivodeshipByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(VOIVODESHIP));

    // when
    NullPointerException exception = assertThrows(
        NullPointerException.class, () -> locationService.createRequest(LOCATION_RESPONSE)
    );

    // then
    assertEquals(
        "County with given name does not exist " + STALOWOWOLSKI_COUNTY, exception.getMessage()
    );

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, times(1)).findCountyByName(anyString());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository, localityRepository);
  }

  @Test
  void should_throw_exception_when_locality_does_not_exist_in_database() {
    // given
    when(voivodeshipRepository.findVoivodeshipByName(PODKARPACKIE_VOIVODESHIP))
        .thenReturn(Optional.of(VOIVODESHIP));
    when(countyRepository.findCountyByName(STALOWOWOLSKI_COUNTY))
        .thenReturn(Optional.of(COUNTY));

    // when
    NullPointerException exception = assertThrows(
        NullPointerException.class, () -> locationService.createRequest(LOCATION_RESPONSE)
    );

    // then
    assertEquals(
        "Locality with given name does not exist " + STALOWA_WOLA_LOCALITY, exception.getMessage()
    );

    verify(voivodeshipRepository, times(1)).findVoivodeshipByName(anyString());
    verify(countyRepository, times(1)).findCountyByName(anyString());
    verify(localityRepository, times(1)).findLocalityByName(anyString());
    verifyNoMoreInteractions(voivodeshipRepository, countyRepository, localityRepository);
  }
}