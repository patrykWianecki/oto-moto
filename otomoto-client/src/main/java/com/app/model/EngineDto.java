package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EngineDto {

  private Double capacity;
  private EmmisionClass emmisionClass;
  private Fuel fuel;
  private Double fuelConsumption;
  private Integer power;
}
