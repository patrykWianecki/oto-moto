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
  private final String id;
  private final String countyId;
  private final String name;
  private final double longitude;
  private final double latitude;
  private final int distance;
}
