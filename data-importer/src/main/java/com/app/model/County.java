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
  private String id;
  private String name;
  private String voivodeshipId;
}
