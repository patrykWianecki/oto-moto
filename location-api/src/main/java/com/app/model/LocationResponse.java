package com.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LocationResponse {

  private String voivodeshipName;
  private String countyName;
  private String localityName;
  private int radius;
}
