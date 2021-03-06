/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.cache;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.inject.Provider;

import org.testng.annotations.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import com.google.common.cache.Cache;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Callables;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.ObjectId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.sesame.EngineTestUtils;
import com.opengamma.sesame.config.EngineUtils;
import com.opengamma.sesame.graph.FunctionId;
import com.opengamma.util.test.TestGroup;

@Test(groups = TestGroup.UNIT)
public class CacheInvalidatorTest {

  private static final MethodInvocationKey METHOD_KEY_1 =
      methodKey(FunctionId.of(1), new ArrayList<>(), "subList", new Object[]{1, 2});

  private static final MethodInvocationKey METHOD_KEY_2 =
      methodKey(FunctionId.of(1), new LinkedList<>(), "set", new Object[]{3, "foo"});

  private static final MethodInvocationKey METHOD_KEY_3 =
      methodKey(FunctionId.of(1), new ArrayList<>(), "size", null);

  private static final Callable<Object> CALLABLE = Callables.returning(null);

  private final Cache<Object, Object> _cache = EngineTestUtils.createCacheProvider().get();

  private void populateCache() {
    _cache.invalidateAll();
    _cache.put(METHOD_KEY_1, new FutureTask<>(CALLABLE));
    _cache.put(METHOD_KEY_2, new FutureTask<>(CALLABLE));
    _cache.put(METHOD_KEY_3, new FutureTask<>(CALLABLE));
  }

  private static MethodInvocationKey methodKey(FunctionId functionId,
                                               Object receiver,
                                               String methodName,
                                               Object[] args) {
    Method method = EngineUtils.getMethod(receiver.getClass(), methodName);
    return new MethodInvocationKey(functionId, method, args);
  }

  /**
   * test the invalidator in isolation
   */
  @Test
  public void registerAndInvalidate() {
    // doesn't matter what this is as long as it doesn't change
    ZonedDateTime valuationTime = ZonedDateTime.now();
    final LinkedList<MethodInvocationKey> keys = Lists.newLinkedList();
    Provider<Collection<MethodInvocationKey>> provider = new Provider<Collection<MethodInvocationKey>>() {
      @Override
      public Collection<MethodInvocationKey> get() {
        return keys;
      }
    };
    _cache.invalidateAll();
    CacheInvalidator invalidator = new DefaultCacheInvalidator(provider, _cache);
    // this makes sure the market data factory is set before adding any data
    invalidator.invalidate(
        valuationTime,
        VersionCorrection.LATEST,
        Collections.<ExternalId>emptyList(),
        Collections.<ObjectId>emptyList());
    // doesn't matter what the methods are
    ObjectId abc2 = ObjectId.of("abc", "2");
    ExternalId abc1 = ExternalId.of("abc", "1");
    ExternalId bnd1 = ExternalId.of("bnd", "1");
    ExternalId bnd2 = ExternalId.of("bnd", "2");

    keys.add(METHOD_KEY_1);
    invalidator.register(abc1);
    keys.add(METHOD_KEY_2);
    invalidator.register(abc2);
    keys.removeLast();
    invalidator.register(ExternalIdBundle.of(bnd1, bnd2));

    populateCache();
    invalidator.invalidate(
        valuationTime,
        VersionCorrection.LATEST,
        Lists.newArrayList(abc1),
        Collections.<ObjectId>emptyList());
    assertNull(_cache.getIfPresent(METHOD_KEY_1));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_2));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_3));

    populateCache();
    invalidator.invalidate(
        valuationTime,
        VersionCorrection.LATEST,
        Collections.<ExternalId>emptyList(),
        Lists.newArrayList(abc2));
    assertNull(_cache.getIfPresent(METHOD_KEY_1));
    assertNull(_cache.getIfPresent(METHOD_KEY_2));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_3));

    populateCache();
    invalidator.invalidate(
        valuationTime,
        VersionCorrection.LATEST,
        Lists.newArrayList(bnd1),
        Collections.<ObjectId>emptyList());
    assertNull(_cache.getIfPresent(METHOD_KEY_1));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_2));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_3));

    populateCache();
    invalidator.invalidate(
        valuationTime,
        VersionCorrection.LATEST,
        Lists.newArrayList(bnd2),
        Collections.<ObjectId>emptyList());
    assertNull(_cache.getIfPresent(METHOD_KEY_1));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_2));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_3));
  }

  @Test
  public void valuationTime() {
    ZonedDateTime valuationTime = ZonedDateTime.of(2011, 3, 8, 2, 18, 0, 0, ZoneId.of("Europe/London"));
    final LinkedList<MethodInvocationKey> keys = Lists.newLinkedList();
    Provider<Collection<MethodInvocationKey>> provider = new Provider<Collection<MethodInvocationKey>>() {
      @Override
      public Collection<MethodInvocationKey> get() {
        return keys;
      }
    };
    populateCache();
    CacheInvalidator invalidator = new DefaultCacheInvalidator(provider, _cache);

    // this key is only valid at the instant it was calculated (i.e. 1 cycle)
    keys.add(METHOD_KEY_1);
    invalidator.register(new ValuationTimeCacheEntry.ValidAtCalculationInstant(valuationTime));
    keys.clear();
    // this key is valid for the whole day on which is was calculated
    keys.add(METHOD_KEY_2);
    invalidator.register(new ValuationTimeCacheEntry.ValidOnCalculationDay(valuationTime.toLocalDate()));

    invalidator.invalidate(
        valuationTime,
        VersionCorrection.LATEST,
        Collections.<ExternalId>emptyList(),
        Collections.<ObjectId>emptyList());
    assertNotNull(_cache.getIfPresent(METHOD_KEY_1));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_2));

    invalidator.invalidate(
        valuationTime.plusHours(1),
        VersionCorrection.LATEST,
        Collections.<ExternalId>emptyList(),
        Collections.<ObjectId>emptyList());
    assertNull(_cache.getIfPresent(METHOD_KEY_1));
    assertNotNull(_cache.getIfPresent(METHOD_KEY_2));

    invalidator.invalidate(
        valuationTime.plusDays(1),
        VersionCorrection.LATEST,
        Collections.<ExternalId>emptyList(),
        Collections.<ObjectId>emptyList());
    assertNull(_cache.getIfPresent(METHOD_KEY_2));
  }
}
