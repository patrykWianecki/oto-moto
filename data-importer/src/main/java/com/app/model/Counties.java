package com.app.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Counties {

  private List<County> counties;
}
