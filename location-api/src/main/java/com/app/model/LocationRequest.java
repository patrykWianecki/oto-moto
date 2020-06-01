package com.app.model;

import java.util.List;

import lombok.Builder;

@Builder
public class LocationRequest {

  private final List<Locality> localities;
}
