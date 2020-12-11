package com.app.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CountyDto {

  private long id;
  private String name;
  @Setter
  private long voivodeshipId;
  private List<LocalityDto> localitiesDto;
}
