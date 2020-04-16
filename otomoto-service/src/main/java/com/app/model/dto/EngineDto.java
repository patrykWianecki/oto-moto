package com.app.model.dto;

import com.app.model.EmmisionClass;
import com.app.model.Fuel;

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
