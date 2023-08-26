package com.dla;

import lombok.Data;

@Data
public class CurrencyConverterResult {
  double value;
  CurrencyToConvertEntity currencyEntity;
}
