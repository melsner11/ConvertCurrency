package com.dpa;

import lombok.Data;

@Data
public class CurrencyConverterResult {
  double value;
  CurrencyToConvertEntity currencyEntity;
}
