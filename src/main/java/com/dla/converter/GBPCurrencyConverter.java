package com.dla.converter;

import com.dla.CurrencyConverterResult;
import com.dla.CurrencyToConvertEntity;

import java.util.Currency;

public class GBPCurrencyConverter extends BaseConverter implements Converter {

  public GBPCurrencyConverter() {
    super("GBP");
  }

  @Override
  public CurrencyConverterResult convert(double value, Currency sourceCurrency, Currency targetCurrency) {
    CurrencyToConvertEntity source = currencies.get(sourceCurrency.getCurrencyCode());
    CurrencyToConvertEntity target = currencies.get(targetCurrency.getCurrencyCode());

    CurrencyConverterResult result = new CurrencyConverterResult();
    result.setValue(value * source.getRate() / target.getRate());
    result.setCurrencyEntity(target);
    return result;
  }
}
