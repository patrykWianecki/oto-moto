package com.app.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dto.*;

@FeignClient(name = "data-importer")
public interface ImporterProxy {

  @PostMapping("/importer/voivodeships")
  List<Voivodeship> createVoivodeships(@RequestBody String voivodeshipsJSON);

  @PostMapping("/importer/counties")
  List<County> createCounties(@RequestBody String countiesJSON);

  @PostMapping("/importer/localities")
  List<Locality> createLocalities(@RequestBody String localitiesJSON);
}
