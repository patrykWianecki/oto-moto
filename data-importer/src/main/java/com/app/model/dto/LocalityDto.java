package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LocalityDto {

  private long id;
  private String name;
  private double longitude;
  private double latitude;
  private int distance;
  private long countyId;
}
