/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.minimization;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.analytics.math.FunctionUtils;
import com.opengamma.analytics.math.MathException;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.util.test.TestGroup;

/**
 * Test.
 */
@Test(groups = TestGroup.UNIT)
public class ParabolicMinimumBracketerTest extends MinimumBracketerTestCase {
  private static final MinimumBracketer BRACKETER = new ParabolicMinimumBracketer();
  private static final Function1D<Double, Double> LINEAR = new Function1D<Double, Double>() {

    @Override
    public Double evaluate(final Double x) {
      return 2 * x - 4;
    }

  };
  private static final Function1D<Double, Double> QUADRATIC = new Function1D<Double, Double>() {

    @Override
    public Double evaluate(final Double x) {
      return x * x + 7 * x + 12;
    }

  };
  private static final Function1D<Double, Double> MOD_QUADRATIC = new Function1D<Double, Double>() {

    @Override
    public Double evaluate(final Double x) {
      return Math.abs(x * x - 4);
    }
  };

  private static final Function1D<Double, Double> STRETCHED_QUADRATIC = new Function1D<Double, Double>() {

    @Override
    public Double evaluate(final Double x) {
      return FunctionUtils.square((x - 50) / 50.0);
    }
  };

  @Override
  protected MinimumBracketer getBracketer() {
    return BRACKETER;
  }

  @Test(expectedExceptions = MathException.class)
  public void test() {
    BRACKETER.getBracketedPoints(LINEAR, 0., 1.);
  }

  @Test
  public void testQuadratic() {
    assertFunction(QUADRATIC, -100, 100);
    assertFunction(QUADRATIC, 100, -100);
    assertFunction(QUADRATIC, 100, 50);
    assertFunction(QUADRATIC, -100, -50);
  }

  @Test
  public void testInitialGuessBracketsTwoMinima() {
    assertFunction(MOD_QUADRATIC, -3, -1);
    assertFunction(MOD_QUADRATIC, -3, 3.5);
  }

  @Test
  public void testStretchedQuadratic() {
    assertFunction(STRETCHED_QUADRATIC, 0, 1);
  }

  private void assertFunction(final Function1D<Double, Double> f, final double xLower, final double xUpper) {
    final double[] result = BRACKETER.getBracketedPoints(f, xLower, xUpper);
    if (result[0] < result[1]) {
      assertTrue(result[1] < result[2]);
    } else {
      assertTrue(result[2] < result[1]);
    }
    final double f2 = f.evaluate(result[1]);
    assertTrue(f.evaluate(result[0]) > f2);
    assertTrue(f.evaluate(result[2]) > f2);
  }
}
