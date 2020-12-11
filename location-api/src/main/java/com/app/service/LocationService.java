package com.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.LocationResponse;
import com.app.model.dto.CountyDto;
import com.app.model.dto.LocalityDto;
import com.app.model.dto.VoivodeshipDto;
import com.app.model.dto.mapper.ModelMapper;
import com.app.repository.CountyRepository;
import com.app.repository.LocalityRepository;
import com.app.repository.VoivodeshipRepository;

import lombok.RequiredArgsConstructor;

import static java.lang.Math.*;

@Service
@RequiredArgsConstructor
public class LocationService {

  private static final double EARTH_RADIUS = 6371D;

  private final VoivodeshipRepository voivodeshipRepository;
  private final CountyRepository countyRepository;
  private final LocalityRepository localityRepository;

  public ResponseEntity<List<LocalityDto>> createRequest(LocationResponse locationResponse) {
    LocalityDto baseLocality = findLocalityWithGivenName(locationResponse);
    List<LocalityDto> localities = getLocalitiesMatchingGivenRadius(locationResponse.getRadius());
    updateDistanceBetweenLocalities(localities, baseLocality);

    if (CollectionUtils.isEmpty(localities)) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(localities);
  }

  private List<LocalityDto> getLocalitiesMatchingGivenRadius(int radius) {
    return localityRepository.findAll()
        .stream()
        .filter(locality -> radius >= locality.getDistance())
        .map(ModelMapper::fromLocalityToLocalityDto)
        .collect(Collectors.toList());
  }

  private void updateDistanceBetweenLocalities(List<LocalityDto> localities,
      LocalityDto baseLocalityDto) {
    localities
        .stream()
        .peek(localityToCalc -> localityToCalc.setDistance(
            calculateDistanceBetweenLocations(baseLocalityDto, localityToCalc)
            )
        )
        .map(ModelMapper::fromLocalityDtoToLocality)
        .forEach(localityRepository::save);
  }

  private LocalityDto findLocalityWithGivenName(LocationResponse locationResponse) {
    VoivodeshipDto voivodeshipDto = findVoivodeshipWithGivenName(
        locationResponse.getVoivodeshipName());
    CountyDto countyDto = findCountyWithGivenName(voivodeshipDto, locationResponse.getCountyName());
    return localityRepository.findLocalityByName(locationResponse.getLocalityName())
        .stream()
        .filter(locality -> countyDto.getId() == locality.getCountyId())
        .map(ModelMapper::fromLocalityToLocalityDto)
        .findFirst()
        .orElseThrow(() -> new NullPointerException(
                "Locality with given name does not exist " + locationResponse.getLocalityName()
            )
        );
  }

  private VoivodeshipDto findVoivodeshipWithGivenName(String voidodeshipName) {
    return voivodeshipRepository.findVoivodeshipByName(voidodeshipName)
        .map(ModelMapper::fromVoivodeshipToVoivodeshipDto)
        .orElseThrow(() -> new NullPointerException(
                "Voivodeship with given name does not exist " + voidodeshipName
            )
        );
  }

  private CountyDto findCountyWithGivenName(VoivodeshipDto voivodeshipDto, String countyName) {
    return countyRepository.findCountyByName(countyName)
        .stream()
        .filter(county -> voivodeshipDto.getId() == county.getVoivodeshipId())
        .map(ModelMapper::fromCountyToCountyDto)
        .findFirst()
        .orElseThrow(
            () -> new NullPointerException("County with given name does not exist " + countyName)
        );
  }

  private int calculateDistanceBetweenLocations(LocalityDto baseLocalityDto,
      LocalityDto localityToCalculateDto) {
    double baseLocalityLongitude = localityToCalculateDto.getLongitude();
    double baseLocalityLatitude = localityToCalculateDto.getLatitude();
    double longitudeToCalculate = baseLocalityDto.getLongitude();
    double latitudeToCalculate = baseLocalityDto.getLatitude();

    if (baseLocalityLatitude == latitudeToCalculate && baseLocalityLongitude == longitudeToCalculate) {
      return BigDecimal.ZERO.intValue();
    }

    double distanceBetweenLongitudes = toRadians(longitudeToCalculate - baseLocalityLongitude);
    double distanceBetweenLatitudes = toRadians(latitudeToCalculate - baseLocalityLatitude);

    latitudeToCalculate = toRadians(latitudeToCalculate);
    baseLocalityLatitude = toRadians(baseLocalityLatitude);

    BigDecimal distance = BigDecimal.valueOf(
        2 * asin(sqrt(
            pow(sin(distanceBetweenLatitudes / 2), 2) +
                pow(sin(distanceBetweenLongitudes / 2), 2) *
                    cos(latitudeToCalculate) *
                    cos(baseLocalityLatitude)
            )
        ));

    return distance.multiply(BigDecimal.valueOf(EARTH_RADIUS))
        .setScale(BigDecimal.ZERO.intValue(), RoundingMode.HALF_UP)
        .intValue();
  }
}
