package com.app.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Currency {

  private String base;
  private String date;
  private Rate rates;
}
