package com.dpa.converter;

import com.dpa.CurrencyConverterConfig;
import com.dpa.CurrencyConverterResult;
import com.dpa.CurrencyToConvertEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Currency;
import java.util.HashMap;

import static org.mockito.Mockito.*;

class GBPCurrencyConverterTest {

  @Test
  void testConvert() {
    GBPCurrencyConverter converter  =new GBPCurrencyConverter();
    CurrencyConverterResult result = converter.convert(20, Currency.getInstance("AED"), Currency.getInstance("AUD"));
    Assertions.assertEquals(95.35106685444893, result.getValue());
    Assertions.assertEquals("AUD", result.getCurrencyEntity().getCode().getCurrencyCode());
  }

  @Test
  void testConvertThrows() {
    GBPCurrencyConverter converter  =new GBPCurrencyConverter();
    Exception exception = Assertions.assertThrows(IllegalArgumentException.class,() -> {
      converter.convert(20, Currency.getInstance("AE"), Currency.getInstance("AUD"));
    });


  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme