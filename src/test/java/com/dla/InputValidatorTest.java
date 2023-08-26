package com.dla;

import com.dla.model.CurrencyToConvertInputEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class InputValidatorTest {

  @Test
  void testGetValidInput() {
    Optional<CurrencyToConvertInputEntity> result = InputValidator.getValidInput(new String[]{"20", "AED", "AUD"});
    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(20, result.get().getValue());
    Assertions.assertEquals("AED", result.get().getSourceCurrency().getCurrencyCode());
    Assertions.assertEquals("AUD", result.get().getTargetCurrency().getCurrencyCode());

  }

  @Test
  void testGetValidInputThrows() {
    Exception e = Assertions.assertThrows(RuntimeException.class, () ->
      InputValidator.getValidInput(new String[]{"20", "AE", "AUD"})
    );
    Assertions.assertTrue(e.getMessage().contains("Wrong parameter length. Code for source currency has to be 3 letters but has: [2]"));
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme