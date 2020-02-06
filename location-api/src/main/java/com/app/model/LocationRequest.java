package com.app.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationRequest {

  private List<Locality> localities;
}
