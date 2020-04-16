package com.app.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountyDto {

  private String id;
  private String voivodeshipId;
  private String name;
}
