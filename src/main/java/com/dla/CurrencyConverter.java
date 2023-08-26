package com.dla;

import com.dla.converter.Converter;

import java.util.*;

public class CurrencyConverter {
  CurrencyToConvertInputEntity input = null;
  Converter converter;

  public CurrencyConverter(String[] args, String baseCurrency) {
    Optional<CurrencyToConvertInputEntity> validInput = InputValidator.getValidInput(args);
    if (validInput.isPresent()) {
        this.input = validInput.get();
        this.converter = ConverterServiceFactory.getConverter(baseCurrency);
    }
  }

  public CurrencyConverterResult convert() {
    return converter.convert(input.getValue(), input.getSourceCurrency(), input.getTargetCurrency());
  }



}

