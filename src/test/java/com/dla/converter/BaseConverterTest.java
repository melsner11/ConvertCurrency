package com.dla.converter;

import com.dla.CurrencyConverterResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Currency;

class BaseConverterTest {


  @Test
  void testConvert() {
    BaseConverter baseConverter  = new BaseConverter("GBP");
    CurrencyConverterResult result = baseConverter.convert(1.23, Currency.getInstance("AED"),  Currency.getInstance("AUD"));
    Assertions.assertNull(result.getCurrencyEntity());
    Assertions.assertEquals(0,result.getValue());
  }

  @Test
  void testConstructorThrows() {
    Assertions.assertThrows(RuntimeException.class, () -> new BaseConverter("NEW"));
  }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme