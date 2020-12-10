package com.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class VehicleDto {

  private String id;
  private Colour colour;
  private Condition condition;
  private String currency;
  private LocalDate dateOfProduction;
  private Drive drive;
  private EngineDto engineDto;
  private Set<Feature> features;
  private LocalDate firstRegistration;
  private Gearbox gearbox;
  private String generation;
  private boolean isAccidentFree;
  private boolean isDamaged;
  private boolean isPriceNegotiable;
  private String location;
  private Make make;
  private long mileage;
  private String model;
  private int numberOfSeats;
  private int numberOfVehicleOwners;
  private BigDecimal price;
  private Type type;
  private String vin;
}