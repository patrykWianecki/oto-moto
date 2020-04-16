package com.app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class LocalityDto {

  private String id;
  private String name;
  private String countyId;
  private double longitude;
  private double latitude;
  @Setter
  private int distance;
}
