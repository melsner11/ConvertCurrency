package com.dla;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ConvertCurrencyTest {
  ConvertCurrency convertCurrency = new ConvertCurrency();

  PrintStream standardOut = System.out;
  PrintStream standardErr = System.err;
  ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
    System.setErr(new PrintStream(errorStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
    System.setErr(standardErr);
  }

  @Test
  void testStart() {
    convertCurrency.start(new String[]{"20", "AED", "AUD"});
    Assertions.assertEquals("95.36 Dollars, (Australia)", outputStreamCaptor.toString()
        .trim());

  }

  @Test
  void testStartError() {
    convertCurrency.start(new String[]{"20", "AED", "AU"});
    Assertions.assertEquals("Wrong parameter length. Code for target currency has to be 3 letters but has: [2]", errorStreamCaptor.toString()
      .trim());

  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme