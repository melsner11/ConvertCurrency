package com.dpa.converter;

import com.dpa.BaseConverter;
import com.dpa.Converter;
import com.dpa.CurrencyConverterResult;
import com.dpa.CurrencyToConvertEntity;

import java.util.Currency;

public class EURCurrencyConverter extends BaseConverter implements Converter {

  public EURCurrencyConverter() {
    super("EUR");
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


