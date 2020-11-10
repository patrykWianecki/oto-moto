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
public class EngineDto {

  private double capacity;
  private EmmisionClass emmisionClass;
  private Fuel fuel;
  private double fuelConsumption;
  private int power;
}
