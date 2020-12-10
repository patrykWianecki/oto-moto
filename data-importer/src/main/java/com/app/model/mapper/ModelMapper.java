package com.app.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;
import com.app.model.dto.CountyDto;
import com.app.model.dto.LocalityDto;
import com.app.model.dto.VoivodeshipDto;

public interface ModelMapper {

  static List<VoivodeshipDto> fromVoivodeshipsToVoivodeshipsDto(List<Voivodeship> voivodeships) {
    return voivodeships
        .stream()
        .map(ModelMapper::fromVoivodeshipToVoivodeshipDto)
        .collect(Collectors.toList());
  }

  static List<Voivodeship> fromVoivodeshipsDtoToVoivodeships(List<VoivodeshipDto> voivodeshipsDto) {
    return voivodeshipsDto
        .stream()
        .map(ModelMapper::fromVoivodeshipDtoToVoivodeship)
        .collect(Collectors.toList());
  }

  static VoivodeshipDto fromVoivodeshipToVoivodeshipDto(Voivodeship voivodeship) {
    return VoivodeshipDto.builder()
        .id(voivodeship.getId())
        .name(voivodeship.getName())
        .countiesDto(
            voivodeship.getCounties() == null ?
                null : fromCountiesToCountiesDto(voivodeship.getCounties())
        )
        .build();
  }

  static Voivodeship fromVoivodeshipDtoToVoivodeship(VoivodeshipDto voivodeshipDto) {
    return Voivodeship.builder()
        .id(voivodeshipDto.getId())
        .name(voivodeshipDto.getName())
        .counties(
            voivodeshipDto.getCountiesDto() == null ?
                null : fromCountiesDtoToCounties(voivodeshipDto.getCountiesDto())
        )
        .build();
  }

  static List<County> fromCountiesDtoToCounties(List<CountyDto> countiesDto) {
    return countiesDto
        .stream()
        .map(ModelMapper::fromCountyDtoToCounty)
        .collect(Collectors.toList());
  }

  static List<CountyDto> fromCountiesToCountiesDto(List<County> counties) {
    return counties
        .stream()
        .map(ModelMapper::fromCountyToCountyDto)
        .collect(Collectors.toList());
  }

  static CountyDto fromCountyToCountyDto(County county) {
    return CountyDto.builder()
        .id(county.getId())
        .name(county.getName())
        .voivodeshipId(county.getVoivodeshipId())
        .localitiesDto(
            county.getLocalities() == null ?
                null : fromLocalitiesToLocalitiesDto(county.getLocalities())
        )
        .build();
  }

  static County fromCountyDtoToCounty(CountyDto countyDto) {
    return County.builder()
        .id(countyDto.getId())
        .name(countyDto.getName())
        .voivodeshipId(countyDto.getVoivodeshipId())
        .localities(
            countyDto.getLocalitiesDto() == null ?
                null : fromLocalitiesDtoToLocalities(countyDto.getLocalitiesDto())
        )
        .build();
  }

  static List<Locality> fromLocalitiesDtoToLocalities(List<LocalityDto> localitiesDto) {
    return localitiesDto
        .stream()
        .map(ModelMapper::fromLocalityDtoToLocality)
        .collect(Collectors.toList());
  }

  static List<LocalityDto> fromLocalitiesToLocalitiesDto(List<Locality> localities) {
    return localities
        .stream()
        .map(ModelMapper::fromLocalityToLocalityDto)
        .collect(Collectors.toList());
  }

  static Locality fromLocalityDtoToLocality(LocalityDto localityDto) {
    return Locality.builder()
        .id(localityDto.getId())
        .name(localityDto.getName())
        .latitude(localityDto.getLatitude())
        .longitude(localityDto.getLongitude())
        .distance(localityDto.getDistance())
        .countyId(localityDto.getCountyId())
        .build();
  }

  static LocalityDto fromLocalityToLocalityDto(Locality locality) {
    return LocalityDto.builder()
        .id(locality.getId())
        .name(locality.getName())
        .latitude(locality.getLatitude())
        .longitude(locality.getLongitude())
        .distance(locality.getDistance())
        .countyId(locality.getCountyId())
        .build();
  }
}
