/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.impl.DataConfigMasterResource;
import com.opengamma.master.config.impl.DataTrackingConfigMaster;
import com.opengamma.master.config.impl.RemoteConfigMaster;
import com.opengamma.masterdb.config.DbConfigMaster;
import com.opengamma.util.metric.OpenGammaMetricRegistry;
import com.opengamma.util.rest.AbstractDataResource;

/**
 * Component factory for the database config master.
 */
@BeanDefinition
public class DbConfigMasterComponentFactory extends AbstractDocumentDbMasterComponentFactory<ConfigMaster, DbConfigMaster> {

  public DbConfigMasterComponentFactory() {
    super("cfg", ConfigMaster.class, RemoteConfigMaster.class);
  }

  @Override
  protected DbConfigMaster createDbDocumentMaster() {
    DbConfigMaster master = new DbConfigMaster(getDbConnector());
    master.registerMetrics(OpenGammaMetricRegistry.getSummaryInstance(), OpenGammaMetricRegistry.getDetailedInstance(), "DbConfigMaster-" + getClassifier());
    return master;
  }

  @Override
  protected AbstractDataResource createPublishedResource(DbConfigMaster dbMaster, ConfigMaster postProcessedMaster) {
    return new DataConfigMasterResource(postProcessedMaster);
  }


  @Override
  protected ConfigMaster wrapMasterWithTrackingInterface(ConfigMaster postProcessedMaster) {
    return new DataTrackingConfigMaster(postProcessedMaster);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbConfigMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static DbConfigMasterComponentFactory.Meta meta() {
    return DbConfigMasterComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(DbConfigMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public DbConfigMasterComponentFactory.Meta metaBean() {
    return DbConfigMasterComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public DbConfigMasterComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("DbConfigMasterComponentFactory{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbConfigMasterComponentFactory}.
   */
  public static class Meta extends AbstractDocumentDbMasterComponentFactory.Meta<ConfigMaster, DbConfigMaster> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends DbConfigMasterComponentFactory> builder() {
      return new DirectBeanBuilder<DbConfigMasterComponentFactory>(new DbConfigMasterComponentFactory());
    }

    @Override
    public Class<? extends DbConfigMasterComponentFactory> beanType() {
      return DbConfigMasterComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}