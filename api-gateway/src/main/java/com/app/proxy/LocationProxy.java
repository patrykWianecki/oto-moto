package com.app.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dto.*;

import static org.springframework.http.MediaType.*;

@FeignClient(name = "location-api")
public interface LocationProxy {

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  ResponseEntity<List<LocalityDto>> getAvailableLocalities(
      @RequestBody LocationResponse locationResponse
  );
}
