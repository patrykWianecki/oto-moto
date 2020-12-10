package com.app.model;

import java.util.List;

import com.app.model.dto.LocalityDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocalityResponse {

  private final List<LocalityDto> localities;
}
