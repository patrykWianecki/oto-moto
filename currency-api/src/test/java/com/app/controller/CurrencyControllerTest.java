package com.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.app.data.MockDataForTests;
import com.app.service.CurrencyService;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CurrencyControllerTest {

  @MockBean
  private CurrencyService currencyService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void should_get_valid_currency_response() throws Exception {
    // given
    when(currencyService.findCurrentExchange()).thenReturn(MockDataForTests.createCurrency());

    // when + then
    mockMvc
        .perform(MockMvcRequestBuilders.get("/currency"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.handler().methodName("sendCurrency"))
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
  }
}