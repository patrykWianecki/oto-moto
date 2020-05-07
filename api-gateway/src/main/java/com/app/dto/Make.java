package com.app.dto;

public enum Make {
  ACURA("Acura"),
  AUDI("Audi"),
  BMW("BMW"),
  CHRYSLER("Chrysler"),
  DACIA("Dacia"),
  FORD("Ford"),
  HONDA("Honda"),
  INFINITY("Infinity"),
  KIA("Kia"),
  LEXUS("Lexus"),
  MAZDA("Mazda"),
  MERCEDES("Mercedes");

  public String value;

  Make(String value) {
    this.value = value;
  }
}
