package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts application on port 8082.
 *
 * @author Patryk Wianecki
 */
@SpringBootApplication
public class CurrencyApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyApiApplication.class, args);
  }
}
