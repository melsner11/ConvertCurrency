package com.dpa;

import java.util.Currency;
import java.util.HashMap;

public interface Converter {
  CurrencyConverterResult convert(double value, Currency sourceCurrency, Currency targetCurrency);

}
