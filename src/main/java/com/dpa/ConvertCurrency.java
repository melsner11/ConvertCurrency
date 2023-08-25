package com.dpa;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class ConvertCurrency {

  public static void main(String[] args) {
//    Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
//    while(loggerNames.hasMoreElements()){
//      Logger logger = LogManager.getLogManager().getLogger(loggerNames.nextElement());
//    }
//    loggers.add(LogManager.getRootLogger());
//    for ( Logger logger : loggers ) {
//      logger.setLevel(Level.OFF);
//    }

//    LogManager.getLogManager().;
//    Logger.ROOT_LOGGER_NAME .ge().setLevel(Level.OFF);
    Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();

    while (loggerNames.hasMoreElements()) {
      String name = loggerNames.nextElement();
      LogManager.getLogManager().getLogger(name).setLevel(Level.OFF);
//      System.out.println(param);
//      l.set   setLevel(Level.OFF);
    }
//    Logger l = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

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
      System.out.println(String.format("%s %s, (%s) "
        , formatRoundedUpAndTwoDigits.format(result.getValue())
        , result.getCurrencyEntity().getName()
        , result.getCurrencyEntity().getCountry()));

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}