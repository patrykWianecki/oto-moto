package com.app.service;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.Currency;
import com.app.model.Rate;

import static com.app.data.MockDataForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This is a test class to check if {@link com.app.service.CurrencyService} works properly.
 *
 * @author Patryk Wianecki
 */
@ExtendWith({SpringExtension.class})
class CurrencyServiceTest {

  private final Currency plnBaseCurrency = createPLNBaseCurrency();
  private final Currency noBaseCurrency = createNoBaseCurrency();

  @Mock
  private CurrencyService currencyService;

  /**
   * Creates a valid {@link com.app.model.Currency}.
   * Checks if currency contains valid date and {@link com.app.model.Rate} for PLN base.
   */
  @Test
  void should_get_correct_currency_response_with_pln_base() throws URISyntaxException,
      InterruptedException {
    // given
    when(currencyService.findCurrentExchange(any(String.class))).thenReturn(plnBaseCurrency);

    // when
    Currency actualCurrency = currencyService.findCurrentExchange(PLN);

    // then
    assertNotNull(actualCurrency);
    assertEquals(PLN, actualCurrency.getBase());
    assertEquals(DATE.toString(), actualCurrency.getDate());

    Rate rate = actualCurrency.getRates();
    assertNotNull(rate);
    assertEquals(rate, actualCurrency.getRates());
    assertEquals("1.00", rate.getPLN());
    assertEquals("0.23", rate.getEUR());
    assertEquals("0.20", rate.getGBP());
    assertEquals("0.26", rate.getUSD());
  }

  /**
   * Creates a valid {@link com.app.model.Currency}.
   * Checks if currency contains valid date and {@link com.app.model.Rate} for default base.
   */
  @Test
  void should_get_correct_currency_response_with_no_base() throws URISyntaxException,
      InterruptedException {
    // given
    when(currencyService.findCurrentExchange(any(String.class))).thenReturn(noBaseCurrency);

    // when
    Currency actualCurrency = currencyService.findCurrentExchange("");

    // then
    assertNotNull(actualCurrency);
    assertEquals(EUR, actualCurrency.getBase());
    assertEquals(DATE.toString(), actualCurrency.getDate());

    Rate rate = actualCurrency.getRates();
    assertNotNull(rate);
    assertEquals(rate, actualCurrency.getRates());
    assertEquals("1.00", rate.getEUR());
    assertEquals("4.25", rate.getPLN());
    assertEquals("0.88", rate.getGBP());
    assertEquals("1.08", rate.getUSD());
  }

  /**
   * Handles thrown exception if currency object does not exist.
   */
  @Test
  void should_handle_empty_request() throws URISyntaxException, InterruptedException {
    // given
    when(currencyService.findCurrentExchange(any(String.class)))
        .thenThrow(NullPointerException.class);

    // when + then
    assertThrows(NullPointerException.class, () -> currencyService.findCurrentExchange(PLN));
  }
}