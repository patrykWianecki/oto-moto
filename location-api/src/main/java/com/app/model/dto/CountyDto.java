package com.app.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountyDto {

  private final String id;
  private final String voivodeshipId;
  private final String name;
}
