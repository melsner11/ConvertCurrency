package com.dla;

import com.dla.converter.Converter;
import com.dla.converter.GBPCurrencyConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterServiceFactoryTest {

  @Test
  void testGetConverter() {
    Converter result = ConverterServiceFactory.getConverter("GBP");
    assertTrue(result.getClass().isInstance(new GBPCurrencyConverter()));
  }

  @Test
  void testGetConverterThrows() {
    Exception exception =  Assertions.assertThrows( RuntimeException.class, () ->
      ConverterServiceFactory.getConverter("OTHER")
    );
    assertTrue(exception.getMessage().contains("Failure while initialising converter.  No converter found that handles base currency: [OTHER]."));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme