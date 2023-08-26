package com.dla.converter;

import com.dla.CurrencyConverterResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Currency;

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
    Assertions.assertThrows(IllegalArgumentException.class,() ->
      converter.convert(20, Currency.getInstance("AE"), Currency.getInstance("AUD"))
    );


  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme