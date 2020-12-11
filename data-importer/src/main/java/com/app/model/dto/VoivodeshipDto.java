package com.app.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class VoivodeshipDto {

  private long id;
  private String name;
  private List<CountyDto> countiesDto;
}
