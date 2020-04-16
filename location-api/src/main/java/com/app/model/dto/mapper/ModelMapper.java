package com.app.model.dto.mapper;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;
import com.app.model.dto.CountyDto;
import com.app.model.dto.LocalityDto;
import com.app.model.dto.VoivodeshipDto;

public interface ModelMapper {

  static VoivodeshipDto fromVoivodeshipToVoivodeshipDto(Voivodeship voivodeship) {
    return VoivodeshipDto.builder()
        .id(voivodeship.getId())
        .name(voivodeship.getName())
        .build();
  }

  static CountyDto fromCountyToCountyDto(County county) {
    return CountyDto.builder()
        .id(county.getId())
        .name(county.getName())
        .voivodeshipId(county.getVoivodeshipId())
        .build();
  }

  static LocalityDto fromLocalityToLocalityDto(Locality locality) {
    return LocalityDto.builder()
        .id(locality.getId())
        .name(locality.getName())
        .countyId(locality.getCountyId())
        .latitude(locality.getLatitude())
        .longitude(locality.getLongitude())
        .distance(locality.getDistance())
        .build();
  }

  static Locality fromLocalityDtoToLocality(LocalityDto localityDto) {
    return Locality.builder()
        .id(localityDto.getId())
        .name(localityDto.getName())
        .countyId(localityDto.getCountyId())
        .latitude(localityDto.getLatitude())
        .longitude(localityDto.getLongitude())
        .distance(localityDto.getDistance())
        .build();
  }
}
