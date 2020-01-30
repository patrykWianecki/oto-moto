package com.app.controller;

import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.data.MockDataForTests;
import com.app.model.Base;
import com.app.model.Currency;
import com.app.service.CurrencyService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CurrencyControllerTest {

  @Mock
  private CurrencyService currencyService;

  @InjectMocks
  private CurrencyController currencyController;

  // TODO zmienić nazwę testu
  // Dopisać inne testy jak sie da

  @Test
  void should() throws URISyntaxException, InterruptedException {
    // given
    when(currencyService.findCurrentExchange()).thenReturn(MockDataForTests.createCurrency());

    // when
    Currency actualCurrency = currencyController.sendCurrency();

    // then
    assertNotNull(actualCurrency);
    assertEquals(Base.PLN, actualCurrency.getBase());
    assertEquals(LocalDate.of(2020, 10, 10).toString(), actualCurrency.getDate());
    assertEquals(MockDataForTests.createCurrency().getRates(), actualCurrency.getRates());
  }
}