package com.app.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.LocationResponse;

import static com.app.data.MockDataForTests.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class LocationValidatorTest {

  @InjectMocks
  private LocationValidator locationValidator;

  private LocationResponse response = createValidLocationResponse();

  @Test
  void should_validate_response_without_throwing_any_exception() {
    // when + then
    locationValidator.validateLocationResponse(response);
  }

  @Test
  void should_throw_exception_when_response_is_null() {
    // given

    // when + then
    assertThrows(NullPointerException.class, () -> locationValidator.validateLocationResponse(null));
  }

  @Test
  void should_throw_exception_when_voivodeship_name_is_null() {
    // given
    response.setVoivodeshipName(null);

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }

  @Test
  void should_throw_exception_when_voivodeship_name_has_wrong_format() {
    // given
    response.setVoivodeshipName(response.getVoivodeshipName().toLowerCase());

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }

  @Test
  void should_throw_exception_when_county_name_is_null() {
    // given
    response.setCountyName(null);

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }

  @Test
  void should_throw_exception_when_county_name_has_wrong_format() {
    // given
    response.setCountyName(response.getCountyName().toLowerCase());

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }

  @Test
  void should_throw_exception_when_locality_name_is_null() {
    // given
    response.setLocalityName(null);

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }

  @Test
  void should_throw_exception_when_locality_name_has_wrong_format() {
    // given
    response.setLocalityName(response.getLocalityName().toLowerCase());

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }

  @Test
  void should_throw_exception_when_radius_is_not_greater_than_zero() {
    // given
    response.setRadius(0);

    // when + then
    assertThrows(IllegalArgumentException.class, () -> locationValidator.validateLocationResponse(response));
  }
}