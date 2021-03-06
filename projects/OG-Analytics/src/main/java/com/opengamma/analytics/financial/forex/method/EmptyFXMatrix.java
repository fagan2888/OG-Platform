/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.forex.method;

import java.util.Collections;
import java.util.Map;

import com.opengamma.util.money.Currency;

/**
 * An immutable and empty FXMatrix. This is written as a singleton pattern.
 */
public final class EmptyFXMatrix extends FXMatrix {
  private static final long serialVersionUID = 1L;
  private static final double[][] RATES_MATRIX = new double[0][0];
  private static final Map<Currency, Integer> CURRENCIES_MAP = Collections.emptyMap();
  
  /**
   * The singleton instance.
   */
  public static final EmptyFXMatrix INSTANCE = new EmptyFXMatrix();
  
  private EmptyFXMatrix() {
  }

  @Override
  public void addCurrency(Currency ccyToAdd, Currency ccyReference, double fxRate) {
    throw new UnsupportedOperationException("Cannot modify EmptyFXMatrix()");
  }

  @Override
  public double getFxRate(Currency ccy1, Currency ccy2) {
    if (ccy1.equals(ccy2)) {
      return 1;
    }
    throw new UnsupportedOperationException("No currencies in an EmptyFXMatrix");
  }

  @Override
  public boolean containsPair(Currency ccy1, Currency ccy2) {
    return false;
  }

  @Override
  public void updateRates(Currency ccyToUpdate, Currency ccyReference, double fxRate) {
    throw new UnsupportedOperationException("Cannot modify EmptyFXMatrix()");
  }

  @Override
  public Map<Currency, Integer> getCurrencies() {
    return CURRENCIES_MAP;
  }

  @Override
  public double[][] getRates() {
    return RATES_MATRIX;
  }

  @Override
  public int getNumberOfCurrencies() {
    return 0;
  }

  @Override
  public FXMatrix copy() {
    return this;
  }

  @Override
  public int hashCode() {
    // Every EmptyFXMatrix is identical. So this actually works.
    // Special Magic Number.
    return 19760811;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof EmptyFXMatrix) {
      return true;
    }
    return super.equals(obj);
  }

}
