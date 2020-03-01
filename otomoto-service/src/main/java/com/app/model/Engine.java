package com.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
