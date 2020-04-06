package com.app.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Currency;
import com.app.service.CurrencyService;

import lombok.RequiredArgsConstructor;

/**
 * Rest controller which provides ability to send request with information about
 * current exchange rate.
 *
 * @author Patryk Wianecki
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

  private final CurrencyService currencyService;

  /**
   * Returns Currency object, which contains informations about base, date and rates.
   * <p>
   * Request param is one of currencies, e.g. PLN, USD, EUR.
   * If request param is not passed, returns by default information about Euro currency.
   *
   * @return the {@link com.app.model.Currency} which contains information about
   * date of exchanged rate and current rates.
   * @throws URISyntaxException   if URI violates RFC&nbsp;2396
   * @throws InterruptedException if the current thread is interrupted while waiting
   */
  @GetMapping
  public Currency sendCurrency(@RequestParam String base) throws URISyntaxException,
      InterruptedException {
    return currencyService.findCurrentExchange(base);
  }
}
