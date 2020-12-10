package com.app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class LocalityDto {

  private final long id;
  private final String name;
  private final long countyId;
  private final double longitude;
  private final double latitude;
  @Setter
  private int distance;
}
