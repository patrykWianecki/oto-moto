package com.app.model;

import lombok.Builder;
import lombok.Getter;

/**
 * This is a model class to hold currency information.
 *
 * @author Patryk Wianecki
 */
@Builder
@Getter
public class Currency {

  private String base;
  private String date;
  private Rate rates;
}
