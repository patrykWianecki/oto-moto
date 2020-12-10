package com.app.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountyDto {

  private final long id;
  private final long voivodeshipId;
  private final String name;
}
