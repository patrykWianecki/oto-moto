package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.dto.CountyDto;
import com.app.model.dto.LocalityDto;
import com.app.model.dto.VoivodeshipDto;
import com.app.service.ImporterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/importer")
public class ImporterController {

  private final ImporterService jsonImporter;

  @PostMapping("/voivodeships")
  public List<VoivodeshipDto> createVoivodeships(@RequestBody String voivodeshipsJSON) {
    return jsonImporter.createVoivodeships(voivodeshipsJSON);
  }

  @PostMapping("/counties/{voivodeshipName}")
  public List<CountyDto> createCounties(@PathVariable String voivodeshipName,
      @RequestBody String countiesJSON) {
    return jsonImporter.createCountiesInVoivodeship(voivodeshipName, countiesJSON);
  }

  @PostMapping("/localities/{countyName}")
  public List<LocalityDto> createLocalities(@PathVariable String countyName,
      @RequestBody String localitiesJSON) {
    return jsonImporter.createLocalitiesInCounty(countyName, localitiesJSON);
  }
}
