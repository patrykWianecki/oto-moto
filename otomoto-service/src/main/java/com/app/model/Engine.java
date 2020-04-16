package com.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

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
