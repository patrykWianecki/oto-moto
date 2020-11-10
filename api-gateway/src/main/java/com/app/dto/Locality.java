package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class Locality {

  private String id;
  private String countyId;
  private String name;
  private double longitude;
  private double latitude;
  private int distance;
}
