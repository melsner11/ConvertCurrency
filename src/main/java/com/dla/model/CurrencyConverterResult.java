package com.dla.model;

import lombok.Data;

@Data
public class CurrencyConverterResult {
  double value;
  CurrencyToConvertEntity currencyEntity;
}
