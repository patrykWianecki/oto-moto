package com.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VehicleValidator implements Validator {

    @Override
    public boolean supports(final Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(final Object o, final Errors errors) {

    }
}
