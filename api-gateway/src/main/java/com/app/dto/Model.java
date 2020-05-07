package com.app.dto;

public enum Model {
  ;

  public enum Audi {
    A_1("A1"),
    A_2("A2"),
    A_3("A3"),
    A_4("A4"),
    A_5("A5"),
    A_6("A6"),
    A_7("A7"),
    A_8("A8");

    public String value;

    Audi(String value) {
      this.value = value;
    }
  }

  public enum BMW {
    SERIES_1("1 Series"),
    SERIES_2("2 Series"),
    SERIES_3("3 Series"),
    SERIES_4("4 Series"),
    SERIES_5("5 Series"),
    SERIES_6("6 Series"),
    SERIES_7("7 Series"),
    SERIES_8("8 Series");

    public String value;

    BMW(String value) {
      this.value = value;
    }
  }

  public enum Mercedes {
    CCLASS("C-Class"),
    ECLASS("E-Class"),
    SCLASS("S-Class");

    public String value;

    Mercedes(String value) {
      this.value = value;
    }
  }
}
