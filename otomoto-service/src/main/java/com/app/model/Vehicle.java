package com.app.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehicles")
public class Vehicle {

  @Id
  private String id;
  private Colour colour;
  private Condition condition;
  private String currency;
  private Drive drive;
  private Engine engine;
  private Set<Feature> features;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate firstRegistration;
  private Gearbox gearbox;
  private boolean isAccidentFree;
  private boolean isDamaged;
  private String location;
  private Make make;
  private Long mileage;
  private String model;
  private Integer numberOfSeats;
  private Integer numberOfVehicleOwners;
  private BigDecimal price;
  private Type type;
}
