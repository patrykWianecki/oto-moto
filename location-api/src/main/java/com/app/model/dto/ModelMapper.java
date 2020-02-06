package com.app.model.dto;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;

public interface ModelMapper {

  static VoivodeshipDto fromVoivodeshipToVoivodeshipDto(Voivodeship voivodeship) {
    return voivodeship == null ? null : VoivodeshipDto.builder()
        .id(voivodeship.getId())
        .name(voivodeship.getName())
        .build();
  }

  static Voivodeship fromVoivodeshipDtoToVoivodeship(VoivodeshipDto voivodeshipDto) {
    return voivodeshipDto == null ? null : Voivodeship.builder()
        .id(voivodeshipDto.getId())
        .name(voivodeshipDto.getName())
        .build();
  }

  static CountyDto fromCountyToCountyDto(County county) {
    return county == null ? null : CountyDto.builder()
        .id(county.getId())
        .name(county.getName())
        .voivodeshipId(county.getVoivodeshipId())
        .build();
  }

  static County fromCountyDtoToCounty(CountyDto countyDto) {
    return countyDto == null ? null : County.builder()
        .id(countyDto.getId())
        .name(countyDto.getName())
        .voivodeshipId(countyDto.getVoivodeshipId())
        .build();
  }

  static LocalityDto fromLocalityToLocalityDto(Locality locality) {
    return locality == null ? null : LocalityDto.builder()
        .id(locality.getId())
        .name(locality.getName())
        .countyId(locality.getCountyId())
        .latitude(locality.getLatitude())
        .longitude(locality.getLongitude())
        .distance(locality.getDistance())
        .build();
  }

  static Locality fromLocalityDtoToLocality(LocalityDto localityDto) {
    return localityDto == null ? null : Locality.builder()
        .id(localityDto.getId())
        .name(localityDto.getName())
        .countyId(localityDto.getCountyId())
        .latitude(localityDto.getLatitude())
        .longitude(localityDto.getLongitude())
        .distance(localityDto.getDistance())
        .build();
  }
}
