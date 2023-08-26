package com.dpa;

import com.dpa.converter.Converter;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ConverterServiceFactory {

  private static final Map<String, String> serviceCache = new HashMap<>();

  public ConverterServiceFactory() {
    initServiceCache();
  }

  /**
   * Load/Initialise available converters - a valid name for a converter would be EURCurrencyConverter.
   * So currency the converter is based on plus the "CurrencyConverter"
   */
  private static void initServiceCache() {
    if (serviceCache.isEmpty()) {
      Reflections reflections = new Reflections("com.dpa", new SubTypesScanner(false));
      reflections.getSubTypesOf(Converter.class)
        .stream()
        .forEach(converter -> {
          try {
            String currencyCodeAsKey = getCurrencyCodeFromClassName(converter.getCanonicalName());
            if (!currencyCodeAsKey.isEmpty()) {
              serviceCache.put(currencyCodeAsKey, converter.getName());
            }
          } catch (Exception e) {
            throw new RuntimeException("Failure initialising available converters.", e);
          }
        });
    }
  }

  private static String getCurrencyCodeFromClassName(String className) {
    String[] items = className.split("\\.");
    String lastItem = items[items.length - 1];
    return lastItem.replace("CurrencyConverter", "");
  }

  public static Converter getConverter(String baseCurrency) {
    try {
      initServiceCache();
      String className = ConverterServiceFactory.serviceCache.get(baseCurrency);
      Class<?> converterClass = ClassLoader.getSystemClassLoader().loadClass(className);
      return (Converter) converterClass.getDeclaredConstructor().newInstance();
    } catch (InvocationTargetException | NoSuchMethodException | InstantiationException e){
      throw new RuntimeException(String.format("Failure while initialising converter: %s ", e.getCause()));
    } catch (NullPointerException | ClassNotFoundException | IllegalAccessException e) {
      throw new RuntimeException(String.format("Failure while initialising converter.  No converter found that handles base currency: [%s].", baseCurrency), e);
    }

  }
}
