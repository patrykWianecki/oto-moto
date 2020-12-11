package com.app.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "counties")
public class County {

  @Id
  @GeneratedValue
  private long id;
  @Column(unique = true)
  private String name;
  @Setter
  @Column(name = "voivodeshipId")
  private long voivodeshipId;
  @OneToMany
  @JoinColumn(name = "countyId")
  private List<Locality> localities;

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final County county = (County) o;
    return voivodeshipId == county.voivodeshipId &&
        Objects.equals(name, county.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, voivodeshipId);
  }
}
