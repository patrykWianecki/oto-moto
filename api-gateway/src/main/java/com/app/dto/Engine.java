package com.app.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Document(collection = "engines")
public class Engine {

  private double capacity;
  private EmmisionClass emmisionClass;
  private Fuel fuel;
  private double fuelConsumption;
  private int power;
}
