package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.importer.ImporterService;
import com.app.model.County;
import com.app.model.Locality;
import com.app.model.Voivodeship;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/importer")
public class ImporterController {

  private final ImporterService jsonImporter;

  @PostMapping("/voivodeships")
  public List<Voivodeship> createVoivodeships(@RequestBody String voivodeshipsJSON) {
    return jsonImporter.createVoivodeships(voivodeshipsJSON);
  }

  @PostMapping("/counties")
  public List<County> createCounties(@RequestBody String countiesJSON) {
    return jsonImporter.createCounties(countiesJSON);
  }

  @PostMapping("/localities")
  public List<Locality> createLocalities(@RequestBody String localitiesJSON) {
    return jsonImporter.createLocalities(localitiesJSON);
  }
}
