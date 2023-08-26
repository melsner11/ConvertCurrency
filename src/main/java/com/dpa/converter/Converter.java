package com.dpa.converter;

import com.dpa.CurrencyConverterResult;

import java.util.Currency;
import java.util.HashMap;

public interface Converter {
  CurrencyConverterResult convert(double value, Currency sourceCurrency, Currency targetCurrency);

}
