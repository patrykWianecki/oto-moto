package com.app.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "engines")
public class Engine {

  @Id
  @GeneratedValue
  private long id;
  private double capacity;
  @Enumerated(STRING)
  private EmmisionClass emmisionClass;
  @Enumerated(STRING)
  private Fuel fuel;
  private double fuelConsumption;
  private int power;
  @OneToMany(fetch = EAGER, mappedBy = "engine")
  private Set<Vehicle> vehicles;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Engine engine = (Engine) o;
    return id == engine.id &&
        Double.compare(engine.capacity, capacity) == 0 &&
        Double.compare(engine.fuelConsumption, fuelConsumption) == 0 &&
        power == engine.power &&
        emmisionClass == engine.emmisionClass &&
        fuel == engine.fuel;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, capacity, emmisionClass, fuel, fuelConsumption, power);
  }
}
