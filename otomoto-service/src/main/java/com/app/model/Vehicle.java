package com.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {

  @Id
  @GeneratedValue
  private long id;
  @Enumerated(STRING)
  private Colour colour;
  @Enumerated(STRING)
  private Condition condition;
  private String currency;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfProduction;
  @Enumerated(STRING)
  private Drive drive;
  @ManyToOne(cascade = {PERSIST, MERGE})
  @JoinColumn(name = "engineId")
  private Engine engine;
  @ElementCollection
  @CollectionTable(name = "features", joinColumns = @JoinColumn(name = "vehicleId"))
  @Column(name = "feature")
  @Enumerated(STRING)
  private Set<Feature> features;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate firstRegistration;
  @Enumerated(STRING)
  private Gearbox gearbox;
  private String generation;
  private boolean isAccidentFree;
  private boolean isDamaged;
  private boolean isPriceNegotiable;
  private String location;
  @Enumerated(STRING)
  private Make make;
  private long mileage;
  private String model;
  private int numberOfSeats;
  private int numberOfVehicleOwners;
  private BigDecimal price;
  private Type type;
  private String vin;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Vehicle vehicle = (Vehicle) o;
    return id == vehicle.id &&
        isAccidentFree == vehicle.isAccidentFree &&
        isDamaged == vehicle.isDamaged &&
        isPriceNegotiable == vehicle.isPriceNegotiable &&
        mileage == vehicle.mileage &&
        numberOfSeats == vehicle.numberOfSeats &&
        numberOfVehicleOwners == vehicle.numberOfVehicleOwners &&
        colour == vehicle.colour &&
        condition == vehicle.condition &&
        Objects.equals(currency, vehicle.currency) &&
        Objects.equals(dateOfProduction, vehicle.dateOfProduction) &&
        drive == vehicle.drive &&
        Objects.equals(features, vehicle.features) &&
        Objects.equals(firstRegistration, vehicle.firstRegistration) &&
        gearbox == vehicle.gearbox &&
        Objects.equals(generation, vehicle.generation) &&
        Objects.equals(location, vehicle.location) &&
        make == vehicle.make &&
        Objects.equals(model, vehicle.model) &&
        Objects.equals(price, vehicle.price) &&
        type == vehicle.type &&
        Objects.equals(vin, vehicle.vin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, colour, condition, currency, dateOfProduction, drive, features, firstRegistration,
        gearbox, generation, isAccidentFree, isDamaged, isPriceNegotiable, location, make, mileage,
        model, numberOfSeats, numberOfVehicleOwners, price, type, vin
    );
  }
}
