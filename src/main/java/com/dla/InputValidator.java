package com.dla;

import java.security.InvalidParameterException;
import java.util.Currency;
import java.util.Optional;

public class InputValidator {

  static public Optional<CurrencyToConvertInputEntity> getValidInput(String[] input) {
    if (validateInput(input)) {
      CurrencyToConvertInputEntity entity = parseInput(input);
      return Optional.of(entity);
    }
    return Optional.empty();
  }

  static private boolean validateInput(String[] input) throws NumberFormatException {
    if (input.length == 3) {
      if (input[1].length() != 3) {
        throw new InvalidParameterException(String.format("Wrong parameter length. Code for source currency has to be 3 letters but has: [%s]  ", input[1].length()));
      }
      if (input[2].length() != 3) {
        throw new InvalidParameterException(String.format("Wrong parameter length. Code for target currency has to be 3 letters but has: [%s]  ", input[2].length()));
      }
      return true;
    }
    throw new InvalidParameterException(String.format("Missing Parameter. Expected 3 but got: [%s]  ", input.length));
  }

  private static CurrencyToConvertInputEntity parseInput(String[] input) {

    try {
      CurrencyToConvertInputEntity currencyToConvertInputEntity = new CurrencyToConvertInputEntity(
        Double.parseDouble(input[0]),
        Currency.getInstance(input[1]),
        Currency.getInstance(input[2]));
      if (currencyToConvertInputEntity.getValue() < 0) {
        throw new RuntimeException(String.format("Error while parsing Input data - Amount value has to be >= 0 : %s ", input[0]));
      }
      return currencyToConvertInputEntity;
    } catch (NumberFormatException e) {
      throw new RuntimeException(String.format("Error while parsing Input data - Cannot parse amount value  : %s ", input[0]));
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(String.format("Error while parsing Input data - Cannot parse currency - one of the provided currencies is not valid: %s %s ", input[1], input[2]));
    }
  }
}
