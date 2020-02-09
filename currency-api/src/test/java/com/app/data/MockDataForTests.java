package com.app.data;

import java.time.LocalDate;

import com.app.model.Base;
import com.app.model.Currency;
import com.app.model.Rate;

public class MockDataForTests {

  public static Currency createCurrency() {
    return Currency.builder()
        .base(Base.PLN)
        .date(LocalDate.of(2020, 10, 10).toString())
        .rates(createRate())
        .build();
  }

  private static Rate createRate() {
    return Rate.builder()
        .EUR("0.23")
        .GBP("0.20")
        .USD("0.26")
        .PLN("1.00")
        .build();
  }
}
