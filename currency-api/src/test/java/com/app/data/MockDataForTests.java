package com.app.data;

import java.time.LocalDate;

import com.app.model.Currency;
import com.app.model.Rate;

/**
 * This class mocks data used in tests.
 *
 * @author Patryk Wianecki
 */
public class MockDataForTests {

  public static final String PLN = "PLN";
  public static final String EUR = "EUR";
  public static final LocalDate DATE = LocalDate.of(2020, 10, 10);

  public static Currency createPLNBaseCurrency() {
    return Currency.builder()
        .base(PLN)
        .date(DATE.toString())
        .rates(createPLNBaseRate())
        .build();
  }

  public static Currency createNoBaseCurrency() {
    return Currency.builder()
        .base(EUR)
        .date(DATE.toString())
        .rates(createNoBaseRate())
        .build();
  }

  private static Rate createPLNBaseRate() {
    return Rate.builder()
        .EUR("0.23")
        .GBP("0.20")
        .USD("0.26")
        .PLN("1.00")
        .build();
  }

  private static Rate createNoBaseRate() {
    return Rate.builder()
        .EUR("1.00")
        .GBP("0.88")
        .USD("1.08")
        .PLN("4.25")
        .build();
  }
}
