package com.app.service;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.model.Currency;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
class CurrencyServiceTest {

  @InjectMocks
  private CurrencyService currencyService;

  // TODO napisać testy
  @Test
  void should() throws URISyntaxException, InterruptedException {
    // given

    // when
    Currency currency = currencyService.findCurrentExchange();

    // then
    assertEquals("", "");
  }
}