package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Document(collection = "voivodeships")
public class Voivodeship {

  @Id
  private final String id;
  private final String name;
}
