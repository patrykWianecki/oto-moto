package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.SerializationFeature.*;

@SpringBootApplication
@EnableEurekaClient
public class OtomotoClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(OtomotoClientApplication.class, args);
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .setAnnotationIntrospector(new JacksonAnnotationIntrospector())
        .registerModule(new JavaTimeModule())
        .setDateFormat(new StdDateFormat())
        .disable(WRITE_DATES_AS_TIMESTAMPS);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
