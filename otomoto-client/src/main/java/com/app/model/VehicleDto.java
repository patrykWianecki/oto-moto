package com.app.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleDto {

  @Id
  private String id;
  private Colour colour;
  private Condition condition;
  private String currency;
  private Drive drive;
  private EngineDto engine;
  private Set<Feature> features;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfProduction;
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
