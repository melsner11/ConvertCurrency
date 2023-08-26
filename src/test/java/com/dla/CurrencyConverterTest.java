package com.dla;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CurrencyConverterTest {

  @Test
  void testConvert() {
    CurrencyConverter currencyConverter = new CurrencyConverter(new String[]{"10","AED", "AUD" },"GBP");
    CurrencyConverterResult result = currencyConverter.convert();
    Assertions.assertEquals(47.675533427224465, result.getValue());
  }
  @Test
  void testConvertThrows() {
    Exception e = Assertions.assertThrows(RuntimeException.class, ()->
      new CurrencyConverter(new String[]{"10","AE", "AUD" },"GBP")
    );
    Assertions.assertTrue(e.getMessage().contains("Wrong parameter length. Code for source currency has to be 3 letters but has: [2]"));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme