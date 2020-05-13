package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Document(collection = "counties")
public class County {

  @Id
  private final String id;
  private final String voivodeshipId;
  private final String name;
}
