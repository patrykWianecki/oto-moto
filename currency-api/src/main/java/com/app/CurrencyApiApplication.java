package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Starts application on port 8082.
 *
 * @author Patryk Wianecki
 */
@SpringBootApplication
@EnableEurekaClient
public class CurrencyApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyApiApplication.class, args);
  }
}
