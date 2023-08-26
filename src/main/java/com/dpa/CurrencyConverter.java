package com.dpa;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.dpa.converter.Converter;

import java.security.InvalidParameterException;
import java.util.*;

public class CurrencyConverter {
  String baseCurrency = null;
  CurrencyToConvertInputEntity input = null;
  Converter converter;

  public CurrencyConverter(String[] args, String baseCurrency) {
    this.baseCurrency = baseCurrency;
    if (validateInput(args)) {
        this.input = parseInput(args);
        this.converter = ConverterServiceFactory.getConverter(this.baseCurrency);
    }
  }

  public CurrencyConverterResult convert() {
    return converter.convert(input.getValue(), input.getSourceCurrency(), input.getTargetCurrency());
  }

  static private boolean validateInput(String[] args) throws NumberFormatException {
    if (args.length == 3) {
      if (args[1].length() != 3) {
        throw new InvalidParameterException(String.format("Wrong parameter length. Code for source currency has to be 3 letters but has: [%s]  ", args[1].length()));
      }
      if (args[2].length() != 3) {
        throw new InvalidParameterException(String.format("Wrong parameter length. Code for target currency has to be 3 letters but has: [%s]  ", args[2].length()));
      }
      return true;
    }
    throw new InvalidParameterException(String.format("Missing Parameter. Expected 3 but got: [%s]  ", args.length));
  }

  private CurrencyToConvertInputEntity parseInput(String[] args) {

    try {
      CurrencyToConvertInputEntity currencyToConvertInputEntity = new CurrencyToConvertInputEntity(
        Double.parseDouble(args[0]),
        Currency.getInstance(args[1]),
        Currency.getInstance(args[2]));
      if (currencyToConvertInputEntity.getValue() < 0 ){
        throw new RuntimeException(String.format("Error while parsing Input data - Amount value has to be >= 0 : %s ", args[0]));
      }
      return currencyToConvertInputEntity;
    } catch (NumberFormatException e) {
      throw new RuntimeException(String.format("Error while parsing Input data - Cannot parse amount value  : %s ", args[0]));
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(String.format("Error while parsing Input data - Cannot parse currency - one of the provided currencies is not valid: %s %s ", args[1], args[2]));
    }
  }

}

