/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot.impl;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.opengamma.core.marketdatasnapshot.MarketDataSnapshotChangeListener;
import com.opengamma.core.marketdatasnapshot.MarketDataSnapshotSource;
import com.opengamma.core.marketdatasnapshot.NamedSnapshot;
import com.opengamma.id.ObjectId;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdSchemeDelegator;
import com.opengamma.id.VersionCorrection;

/**
 * A source of snapshots that uses the scheme of the unique identifier to determine which underlying source should handle the request.
 * <p>
 * If no scheme-specific handler has been registered, a default is used.
 */
public class DelegatingSnapshotSource extends UniqueIdSchemeDelegator<MarketDataSnapshotSource> implements MarketDataSnapshotSource {

  /**
   * Creates an instance specifying the default delegate.
   * 
   * @param defaultSource the source to use when no scheme matches, not null
   */
  public DelegatingSnapshotSource(MarketDataSnapshotSource defaultSource) {
    super(defaultSource);
  }

  /**
   * Creates an instance specifying the default delegate.
   * 
   * @param defaultSource the source to use when no scheme matches, not null
   * @param schemePrefixToSourceMap the map of sources by scheme to switch on, not null
   */
  public DelegatingSnapshotSource(MarketDataSnapshotSource defaultSource, Map<String, MarketDataSnapshotSource> schemePrefixToSourceMap) {
    super(defaultSource, schemePrefixToSourceMap);
  }

  @Override
  public NamedSnapshot get(ObjectId objectId, VersionCorrection versionCorrection) {
    return chooseDelegate(objectId.getScheme()).get(objectId, versionCorrection);
  }

  @Override
  public NamedSnapshot get(UniqueId uniqueId) {
    return chooseDelegate(uniqueId.getScheme()).get(uniqueId);
  }

  @Override
  public Map<UniqueId, NamedSnapshot> get(Collection<UniqueId> uniqueIds) {
    final ListMultimap<String, UniqueId> groups = ArrayListMultimap.create();
    for (UniqueId uniqueId : uniqueIds) {
      groups.put(uniqueId.getScheme(), uniqueId);
    }
    Map<UniqueId, NamedSnapshot> snapshots = newHashMap();
    for (Map.Entry<String, Collection<UniqueId>> entries : groups.asMap().entrySet()) {
      snapshots.putAll(chooseDelegate(entries.getKey()).get(entries.getValue()));
    }

    return snapshots;
  }

  @Override
  public Map<ObjectId, NamedSnapshot> get(final Collection<ObjectId> objectIds, final VersionCorrection versionCorrection) {
    final ListMultimap<String, ObjectId> groups = ArrayListMultimap.create();
    for (ObjectId objectId : objectIds) {
      groups.put(objectId.getScheme(), objectId);
    }
    final Map<ObjectId, NamedSnapshot> snapshots = newHashMap();
    for (Map.Entry<String, Collection<ObjectId>> entries : groups.asMap().entrySet()) {
      snapshots.putAll(chooseDelegate(entries.getKey()).get(entries.getValue(), versionCorrection));
    }
    return snapshots;
  }

  @Override
  public void addChangeListener(UniqueId uniqueId, MarketDataSnapshotChangeListener listener) {
    chooseDelegate(uniqueId.getScheme()).addChangeListener(uniqueId, listener);
  }

  @Override
  public void removeChangeListener(UniqueId uniqueId, MarketDataSnapshotChangeListener listener) {
    chooseDelegate(uniqueId.getScheme()).removeChangeListener(uniqueId, listener);
  }

  @Override
  public <S extends NamedSnapshot> S getSingle(Class<S> type,
                                               String snapshotName,
                                               VersionCorrection versionCorrection) {
    // As we have noi information about the scheme we can't do anything but use the default
    return getDefaultDelegate().getSingle(type, snapshotName, versionCorrection);
  }
}
