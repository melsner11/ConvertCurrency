package com.dla.converter;

import com.dla.model.CurrencyConverterResult;

import java.util.Currency;

public interface Converter {
  CurrencyConverterResult convert(double value, Currency sourceCurrency, Currency targetCurrency);

}
