package com.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "engines")
public class Engine {

  private Double capacity;
  private EmmisionClass emmisionClass;
  private Fuel fuel;
  private Double fuelConsumption;
  private Integer power;
}
