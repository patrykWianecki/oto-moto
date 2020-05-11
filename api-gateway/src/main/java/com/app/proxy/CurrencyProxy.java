package com.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.Currency;

@FeignClient(name = "currency-api")
public interface CurrencyProxy {

  @GetMapping("/currency")
  Currency sendCurrency(@RequestParam String base);
}
