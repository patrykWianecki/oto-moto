package com.app.model;

import java.util.List;

import com.app.model.dto.VoivodeshipDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoivodeshipResponse {

  private final List<VoivodeshipDto> voivodeships;
}
