package com.app.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "localities")
public class Locality {

  @Id
  @GeneratedValue
  private long id;
  @Column(unique = true)
  private String name;
  private double longitude;
  private double latitude;
  private int distance;
  @Setter
  @Column(name = "countyId")
  private long countyId;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Locality locality = (Locality) o;
    return Double.compare(locality.longitude, longitude) == 0 &&
        Double.compare(locality.latitude, latitude) == 0 &&
        distance == locality.distance &&
        Objects.equals(name, locality.name) &&
        countyId == locality.countyId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, longitude, latitude, distance, countyId);
  }
}
