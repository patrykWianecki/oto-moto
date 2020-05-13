package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@Document(collection = "localities")
public class Locality {

  @Id
  private final String id;
  private final String name;
  private final String countyId;
  private final double longitude;
  private final double latitude;
  @Field
  private final int distance;
}
