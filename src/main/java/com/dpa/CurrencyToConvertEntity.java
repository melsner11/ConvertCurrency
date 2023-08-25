package com.dpa;

import lombok.Getter;

import java.util.Currency;

@Getter
public class CurrencyToConvertEntity {
  String country;
  String name;
  Currency code; // in ISO4217 - 3 letters;
  double rate; // related to base GBP

  public CurrencyToConvertEntity(String country, String name, Currency code, double rate) {
    this.country = country;
    this.name = name;
    this.code = code;
    this.rate = rate;
  }
}
