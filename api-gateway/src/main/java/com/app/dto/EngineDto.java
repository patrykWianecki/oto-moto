package com.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EngineDto {

  private double capacity;
  private EmmisionClass emmisionClass;
  private Fuel fuel;
  private double fuelConsumption;
  private int power;
}
