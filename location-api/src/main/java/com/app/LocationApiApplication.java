package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class LocationApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(LocationApiApplication.class, args);
  }
}
