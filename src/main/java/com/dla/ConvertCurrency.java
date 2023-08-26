package com.dla;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ConvertCurrency {

  public static void main(String[] args) {
    ConvertCurrency convertCurrency = new ConvertCurrency();
    convertCurrency.start(args);
  }
  public void start(String[] args) {
    DecimalFormat formatRoundedUpAndTwoDigits = new DecimalFormat("0.00");
    formatRoundedUpAndTwoDigits.setRoundingMode(RoundingMode.UP);

    try {
      CurrencyConverter currencyConverter = new CurrencyConverter(args, "GBP");
//      CurrencyConverter currencyConverter = new CurrencyConverter(new String[]{"34.56", "AED", "AUD"}, "GBP");
//      CurrencyConverter currencyConverter = new CurrencyConverter(new String[]{"34.56", "AED"}, "GBP");

      CurrencyConverterResult result = currencyConverter.convert();
      System.out.printf("%s %s, (%s) %n"
        , formatRoundedUpAndTwoDigits.format(result.getValue())
        , result.getCurrencyEntity().getName()
        , result.getCurrencyEntity().getCountry());

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}