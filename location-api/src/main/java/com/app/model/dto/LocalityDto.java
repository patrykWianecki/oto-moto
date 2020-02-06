package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalityDto {

  private String id;
  private String name;
  private String countyId;
  private double longitude;
  private double latitude;
  private int distance;
}
