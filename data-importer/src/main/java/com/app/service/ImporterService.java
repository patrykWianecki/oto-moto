package com.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.app.model.County;
import com.app.model.CountyResponse;
import com.app.model.Locality;
import com.app.model.LocalityResponse;
import com.app.model.Voivodeship;
import com.app.model.VoivodeshipResponse;
import com.app.model.dto.CountyDto;
import com.app.model.dto.LocalityDto;
import com.app.model.dto.VoivodeshipDto;
import com.app.respository.CountyRepository;
import com.app.respository.LocalityRepository;
import com.app.respository.VoivodeshipRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.RequiredArgsConstructor;

import static com.app.model.mapper.ModelMapper.*;

@Service
@RequiredArgsConstructor
public class ImporterService {

  private final CountyRepository countyRepository;
  private final LocalityRepository localityRepository;
  private final VoivodeshipRepository voivodeshipRepository;
  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public List<VoivodeshipDto> createVoivodeships(String voivodeshipsJSON) {
    Optional<VoivodeshipResponse> voivodeshipRequest =
        Optional.ofNullable(gson.fromJson(voivodeshipsJSON, VoivodeshipResponse.class));

    if (voivodeshipRequest.isPresent()) {
      List<VoivodeshipDto> voivodeshipsDto = voivodeshipRequest.get().getVoivodeships();

      if (CollectionUtils.isNotEmpty(voivodeshipsDto)) {
        List<Voivodeship> voivodeships = fromVoivodeshipsDtoToVoivodeships(voivodeshipsDto);
        return fromVoivodeshipsToVoivodeshipsDto(voivodeshipRepository.saveAll(voivodeships));
      }
    }

    return Collections.emptyList();
  }

  public List<CountyDto> createCountiesInVoivodeship(String voivodeshipName, String countiesJSON) {
    Optional<Voivodeship> optionalVoivodeship =
        voivodeshipRepository.findVoivodeshipByName(voivodeshipName);

    if (optionalVoivodeship.isPresent()) {
      Optional<CountyResponse> countyRequest =
          Optional.ofNullable(gson.fromJson(countiesJSON, CountyResponse.class));

      if (countyRequest.isPresent()) {
        List<CountyDto> countiesDto = countyRequest.get().getCounties();

        if (CollectionUtils.isNotEmpty(countiesDto)) {
          List<County> counties = fromCountiesDtoToCounties(countiesDto);
          counties.forEach(county -> county.setVoivodeshipId(optionalVoivodeship.get().getId()));
          return fromCountiesToCountiesDto(countyRepository.saveAll(counties));
        }
      }
    }

    return Collections.emptyList();
  }

  public List<LocalityDto> createLocalitiesInCounty(String countyName, String localitiesJSON) {
    Optional<County> optionalCounty = countyRepository.findCountyByName(countyName);

    if (optionalCounty.isPresent()) {
      Optional<LocalityResponse> localityRequest =
          Optional.ofNullable(gson.fromJson(localitiesJSON, LocalityResponse.class));

      if (localityRequest.isPresent()) {
        List<LocalityDto> localitiesDto = localityRequest.get().getLocalities();

        if (CollectionUtils.isNotEmpty(localitiesDto)) {
          List<Locality> localities = fromLocalitiesDtoToLocalities(localitiesDto);
          localities.forEach(locality -> locality.setCountyId(optionalCounty.get().getId()));
          return fromLocalitiesToLocalitiesDto(localityRepository.saveAll(localities));
        }
      }
    }

    return Collections.emptyList();
  }
}
