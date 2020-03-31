package com.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.model.VehicleDto;

@Component
public class VehicleValidator implements Validator {

  @Override
  public boolean supports(final Class<?> aClass) {
    return aClass.equals(VehicleDto.class);
  }

  @Override
  public void validate(final Object o, final Errors errors) {

  }
}
