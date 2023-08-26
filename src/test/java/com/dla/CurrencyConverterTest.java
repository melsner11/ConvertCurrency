package com.dla;

import com.dla.converter.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CurrencyConverterTest {
  @Mock
  CurrencyToConvertInputEntity input;
  @Mock
  Converter converter;
  @InjectMocks
  CurrencyConverter currencyConverter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testConvert() {
    when(input.getValue()).thenReturn(Double.valueOf(0));
    when(input.getSourceCurrency()).thenReturn(null);
    when(input.getTargetCurrency()).thenReturn(null);
    when(converter.convert(anyDouble(), any(), any())).thenReturn(new CurrencyConverterResult());

    CurrencyConverterResult result = currencyConverter.convert();
    Assertions.assertEquals(new CurrencyConverterResult(), result);
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme