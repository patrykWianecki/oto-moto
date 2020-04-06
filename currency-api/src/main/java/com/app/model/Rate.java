package com.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * This is a model class to hold rate information.
 *
 * @author Patryk Wianecki
 */
@Builder
@Getter
@JsonInclude(NON_NULL)
public class Rate {

  private String PLN;
  private String USD;
  private String EUR;
  private String GBP;
  private String CAD;
  private String HKD;
  private String ISK;
  private String PHP;
  private String DKK;
  private String HUF;
  private String CZK;
  private String RON;
  private String SEK;
  private String IDR;
  private String INR;
  private String BRL;
  private String RUB;
  private String HRK;
  private String JPY;
  private String THB;
  private String CHF;
  private String MYR;
  private String BGN;
  private String TRY;
  private String CNY;
  private String NOK;
  private String NZD;
  private String ZAR;
  private String MXN;
  private String SGD;
  private String AUD;
  private String ILS;
  private String KRW;
}
