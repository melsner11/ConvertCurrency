package com.dpa;

import java.util.Currency;
import java.util.HashMap;

public class BaseConverter implements Converter{
  protected String base;
  protected HashMap<String, CurrencyToConvertEntity> currencies;
  private CurrencyConverterConfig currencyConverterConfig;

  public BaseConverter(String base) {
    this.base = base;
    this.currencyConverterConfig = new CurrencyConverterConfig();
    this.currencies = currencyConverterConfig.initialiseCurrencies(base);
  }

  @Override
  public CurrencyConverterResult convert(double value, Currency sourceCurrency, Currency targetCurrency) {
    return new CurrencyConverterResult();
  }
}
