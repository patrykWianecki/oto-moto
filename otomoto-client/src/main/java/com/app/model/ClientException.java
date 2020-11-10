package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientException extends RuntimeException {

  private final String message;
}
