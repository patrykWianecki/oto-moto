package com.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.app.model.Currency;
import com.app.service.CurrencyService;

import static com.app.data.MockDataForTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for {@link com.app.controller.CurrencyController}
 *
 * @author Patryk Wianecki
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CurrencyControllerTest {

  private static final String SEND_CURRENCY_METHOD = "sendCurrency";
  private static final String QUERY_PARAM = "/currency?base=";

  private final Currency plnBasecurrency = createPLNBaseCurrency();
  private final Currency noBaseCurrency = createNoBaseCurrency();

  @MockBean
  private CurrencyService currencyService;

  @Autowired
  private MockMvc mockMvc;

  /**
   * Returns valid {@link com.app.model.Currency}.
   * Handles case when base is passed.
   */
  @Test
  void should_get_currency_response_with_pln_base() throws Exception {
    // given
    when(currencyService.findCurrentExchange(any(String.class))).thenReturn(plnBasecurrency);

    // when
    String response = mockMvc
        .perform(get(QUERY_PARAM + PLN))
        .andExpect(status().isOk())
        .andExpect(handler().methodName(SEND_CURRENCY_METHOD))
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andReturn()
        .getResponse()
        .getContentAsString();

    // then
    assertNotNull(response);
    assertTrue(response.contains("\"base\":\"PLN\""));
    assertTrue(response.contains("\"date\":\"2020-10-10\""));
    assertTrue(response.contains("\"pln\":\"1.00\""));
    assertTrue(response.contains("\"eur\":\"0.23\""));
    assertTrue(response.contains("\"gbp\":\"0.20\""));
    assertTrue(response.contains("\"usd\":\"0.26\""));
  }

  /**
   * Returns valid {@link com.app.model.Currency}.
   * Handles case when base is not passed and returns default base (EUR).
   */
  @Test
  void should_get_currency_response_with_default_base() throws Exception {
    // given
    when(currencyService.findCurrentExchange(any(String.class))).thenReturn(noBaseCurrency);

    // when
    String response = mockMvc
        .perform(get(QUERY_PARAM))
        .andExpect(status().isOk())
        .andExpect(handler().methodName(SEND_CURRENCY_METHOD))
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andReturn()
        .getResponse()
        .getContentAsString();

    // then
    assertNotNull(response);
    assertTrue(response.contains("\"base\":\"EUR\""));
    assertTrue(response.contains("\"date\":\"2020-10-10\""));
    assertTrue(response.contains("\"eur\":\"1.00\""));
    assertTrue(response.contains("\"gbp\":\"0.88\""));
    assertTrue(response.contains("\"pln\":\"4.25\""));
    assertTrue(response.contains("\"usd\":\"1.08\""));
  }
}