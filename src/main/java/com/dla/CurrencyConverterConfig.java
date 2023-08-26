package com.dla;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class CurrencyConverterConfig {

  public HashMap<String, CurrencyToConvertEntity> initialiseCurrencies(String baseCurrency) {
    HashMap<String, CurrencyToConvertEntity> currencies = new HashMap<>();
    String fileName = "currencies_" + baseCurrency.toLowerCase() + ".csv";
    Optional<InputStream> currenciesInputStream = getConfigFileInputStream(fileName);

    if (currenciesInputStream.isPresent()) {
      Scanner scanner = new Scanner(currenciesInputStream.get());
      while (scanner.hasNextLine()) {
        CurrencyToConvertEntity entity = getCurrencyFromLine(scanner.nextLine());
        currencies.put(entity.code.toString(), entity);

      }
      return currencies;
    }
    throw new RuntimeException(String.format("Error while reading config file for converter from base: [%s]. Did not find expected file: [%s] in classpath or resources folder.", baseCurrency, fileName));

  }

  private Optional<InputStream> getConfigFileInputStream(String fileName){

    Optional<InputStream> currenciesInputStream;
    try {
      return Optional.of(new FileInputStream(System.getProperty("user.dir") + "/" + fileName));
    } catch (FileNotFoundException e){
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      currenciesInputStream = Optional.ofNullable(classloader.getResourceAsStream(fileName));
    }
    return currenciesInputStream;
  }

  private CurrencyToConvertEntity getCurrencyFromLine(String line) {
    List<String> rowValues = new ArrayList<>();
    try (Scanner rowScanner = new Scanner(line)) {
      rowScanner.useDelimiter(",");
      while (rowScanner.hasNext()) {
        rowValues.add(rowScanner.next().trim());
      }
    }

    try {
      return new CurrencyToConvertEntity(
        rowValues.get(0),
        rowValues.get(1),
        Currency.getInstance(rowValues.get(2)),
        Double.parseDouble(rowValues.get(3)));
    } catch (IllegalArgumentException e) {
      throw new RuntimeException(String.format("Failure in file format while parsing data:  %s ", line), e);
    }
  }
}
