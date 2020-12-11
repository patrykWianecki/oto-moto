package com.app.model;

import java.util.List;

import com.app.model.dto.CountyDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountyResponse {

  private final List<CountyDto> counties;
}
