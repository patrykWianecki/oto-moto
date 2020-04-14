package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Document(collection = "localities")
public class Locality {

  @Id
  private String id;
  private String countyId;
  private String name;
  private double longitude;
  private double latitude;
  private int distance;
}
