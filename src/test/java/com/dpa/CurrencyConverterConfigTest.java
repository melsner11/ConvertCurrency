package com.dpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CurrencyConverterConfigTest {
  CurrencyConverterConfig currencyConverterConfig = new CurrencyConverterConfig();

  @Test
  void testInitialiseCurrencies() {
    HashMap<String, CurrencyToConvertEntity> result = currencyConverterConfig.initialiseCurrencies("GBP");
    Assertions.assertEquals(4, result.size());
  }
  @Test
  void testInitialiseCurrenciesThrows() {
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
      currencyConverterConfig.initialiseCurrencies("OTHER");
    });
    Assertions.assertTrue(exception.getMessage().contains("Error while reading config file for converter from base:"));
  }

  @Test
  void testInitialiseCurrenciesWithWrongCurrencyCodeInConfigFileThrows() {
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
      currencyConverterConfig.initialiseCurrencies("fortest1");
    });
    Assertions.assertTrue(exception.getMessage().contains("Failure in file format while parsing data:"));
  }

  @Test
  void testInitialiseCurrenciesWithWrongRateValueInConfigFileThrows() {
    Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
      currencyConverterConfig.initialiseCurrencies("fortest2");
    });
    Assertions.assertTrue(exception.getMessage().contains("Failure in file format while parsing data:"));
  }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme