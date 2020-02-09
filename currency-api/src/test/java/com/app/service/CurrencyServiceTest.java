package com.app.service;

import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.data.MockDataForTests;
import com.app.model.Base;
import com.app.model.Currency;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
class CurrencyServiceTest {

  @Mock
  private CurrencyService currencyService;

  @Test
  void should_get_correct_currency_response() throws URISyntaxException, InterruptedException {
    // given
    when(currencyService.findCurrentExchange()).thenReturn(MockDataForTests.createCurrency());

    // when
    Currency actualCurrency = currencyService.findCurrentExchange();

    // then
    assertNotNull(actualCurrency);
    assertEquals(Base.PLN, actualCurrency.getBase());
    assertEquals(LocalDate.of(2020, 10, 10).toString(), actualCurrency.getDate());
    assertEquals(MockDataForTests.createCurrency().getRates(), actualCurrency.getRates());
  }

  @Test
  void should_handle_empty_response() throws URISyntaxException, InterruptedException {
    // given
    when(currencyService.findCurrentExchange()).thenThrow(NullPointerException.class);

    // when + then
    assertThrows(NullPointerException.class, () -> currencyService.findCurrentExchange());
  }
}