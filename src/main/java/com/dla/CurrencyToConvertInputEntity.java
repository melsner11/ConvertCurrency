package com.dla;

import java.util.Currency;

public class CurrencyToConvertInputEntity {
  double value;
  Currency sourceCurrency;
  Currency targetCurrency;

  public CurrencyToConvertInputEntity(double value, Currency sourceCurrency, Currency targetCurrency) throws IllegalArgumentException {
    this.value = value;
    this.sourceCurrency = sourceCurrency;
    this.targetCurrency = targetCurrency;
  }

  public Double getValue() {
    return value;
  }

  public Currency getSourceCurrency() {
    return sourceCurrency;
  }

  public Currency getTargetCurrency() {
    return targetCurrency;
  }
}
