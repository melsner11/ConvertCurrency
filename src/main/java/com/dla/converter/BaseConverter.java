package com.dla.converter;

import com.dla.CurrencyConverterConfig;
import com.dla.model.CurrencyConverterResult;
import com.dla.model.CurrencyToConvertEntity;

import java.util.Currency;
import java.util.HashMap;

public class BaseConverter implements Converter {
  protected String base;
  protected HashMap<String, CurrencyToConvertEntity> currencies;


  public BaseConverter(String base) {
    this.base = base;
    CurrencyConverterConfig currencyConverterConfig = new CurrencyConverterConfig();
    this.currencies = currencyConverterConfig.initialiseCurrencies(base);
  }

  @Override
  public CurrencyConverterResult convert(double value, Currency sourceCurrency, Currency targetCurrency) {
    return new CurrencyConverterResult();
  }
}
