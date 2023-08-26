package com.dpa;

import com.dpa.converter.Converter;
import com.dpa.converter.GBPCurrencyConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterServiceFactoryTest {
  ConverterServiceFactory converterServiceFactory;

  @Test
  void testInitFactoryDoesNotThrow() {
    Assertions.assertDoesNotThrow(() -> {
      converterServiceFactory = new ConverterServiceFactory();
    });
  }

  @Test
  void testGetConverter() {
    Converter result = converterServiceFactory.getConverter("GBP");
    assertTrue(result.getClass().isInstance(new GBPCurrencyConverter()));
  }

  @Test
  void testGetConverterThrows() {
    Exception exception =  Assertions.assertThrows( RuntimeException.class, () -> {
      converterServiceFactory.getConverter("OTHER");
    });
    assertTrue(exception.getMessage().contains("Failure while initialising converter.  No converter found that handles base currency: [OTHER]."));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme