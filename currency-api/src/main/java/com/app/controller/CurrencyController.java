package com.app.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Currency;
import com.app.service.CurrencyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

  private final CurrencyService currencyService;

  @GetMapping
  public Currency sendCurrency() throws URISyntaxException, InterruptedException {
    return currencyService.findCurrentExchange();
  }
}
