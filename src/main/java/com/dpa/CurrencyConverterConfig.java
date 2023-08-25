package com.dpa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class CurrencyConverterConfig {

  public HashMap initialiseCurrencies(String baseCurrency) {
    HashMap currencies = new HashMap<String, CurrencyToConvertEntity>();
    String fileName = "currencies_" + baseCurrency.toLowerCase() + ".csv";
    Optional<InputStream> currenciesInputStream = getConfigFileInputStream(fileName);

    try (Scanner scanner = new Scanner(currenciesInputStream.get())) {
      while (scanner.hasNextLine()) {
        CurrencyToConvertEntity entity = getCurrencyFromLine(scanner.nextLine());
        currencies.put(entity.code.toString(), entity);

      }
    } catch (Exception e) {
      throw new RuntimeException(String.format("Error while reading config file for converter from base: [%s]. Did not find expected file: [%s] in classpath or resources folder.", baseCurrency, fileName), e);
    }
    return currencies;
  }

  private Optional<InputStream> getConfigFileInputStream(String fileName){

    Optional<InputStream> currenciesInputStream;
    try {
      return Optional.ofNullable(new FileInputStream(System.getProperty("user.dir") + "/" + fileName));
    } catch (FileNotFoundException e){
      currenciesInputStream =  Optional.empty();
    }

    if (!currenciesInputStream.isPresent()){
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      currenciesInputStream = Optional.ofNullable(classloader.getResourceAsStream(fileName));
    }
    return currenciesInputStream;
  };

  private CurrencyToConvertEntity getCurrencyFromLine(String line) {
    List<String> rowValues = new ArrayList<String>();
    try (Scanner rowScanner = new Scanner(line)) {
      rowScanner.useDelimiter(",");
      while (rowScanner.hasNext()) {
        rowValues.add(rowScanner.next().trim());
      }
    }

    try {
      CurrencyToConvertEntity currencyToConvertEntity = new CurrencyToConvertEntity(
        rowValues.get(0),
        rowValues.get(1),
        Currency.getInstance(rowValues.get(2)),
        Float.parseFloat(rowValues.get(3)));
      return currencyToConvertEntity;
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(String.format("Failure in file format while parsing data:  %s ", line), e);
    }
  }
}
