package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Document(collection = "localities")
public class Locality {

  @Id
  private String id;
  private String name;
  private String countyId;
  private double longitude;
  private double latitude;
  @Field
  private int distance;
}
