/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata.scenarios;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableSet;
import com.opengamma.sesame.MulticurveBundle;
import com.opengamma.sesame.marketdata.MarketDataId;
import com.opengamma.sesame.marketdata.MulticurveId;
import com.opengamma.util.ArgumentChecker;

/**
 * Filter that matches individual curves in a {@link MulticurveBundle} based on the name of the curve's index.
 */
@BeanDefinition
public final class IndexNameMulticurveFilter implements MarketDataFilter, ImmutableBean {

  /** The index matched by this filter. */
  @PropertyDefinition(validate = "notNull")
  private final String _indexName;

  /**
   * Creates a filter that will match curves for the specified index.
   *
   * @param indexName the filter matches curves for indices with this name
   */
  @ImmutableConstructor
  public IndexNameMulticurveFilter(String indexName) {
    _indexName = ArgumentChecker.notNull(indexName, "indexName");
  }

  private Set<MulticurveMatchDetails> apply(MulticurveMetadata metadata) {
    String curveName = metadata.getCurveNamesByIndexName().get(_indexName);

    if (curveName != null) {
      return ImmutableSet.of(StandardMatchDetails.multicurve(curveName));
    } else {
      return ImmutableSet.of();
    }
  }

  @Override
  public Set<MulticurveMatchDetails> apply(MarketDataId<?> marketDataId) {
    MulticurveId id = (MulticurveId) marketDataId;
    MulticurveMetadata metadata = MulticurveMetadata.forConfiguration(id.resolveConfig());
    return apply(metadata);
  }

  @Override
  public Set<MulticurveMatchDetails> apply(MarketDataId<?> marketDataId, Object marketData) {
    MulticurveBundle multicurve = (MulticurveBundle) marketData;
    MulticurveMetadata metadata = MulticurveMetadata.forMulticurve(multicurve);
    return apply(metadata);
  }

  @Override
  public Class<?> getMarketDataType() {
    return MulticurveBundle.class;
  }

  @Override
  public Class<MulticurveId> getMarketDataIdType() {
    return MulticurveId.class;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IndexNameMulticurveFilter}.
   * @return the meta-bean, not null
   */
  public static IndexNameMulticurveFilter.Meta meta() {
    return IndexNameMulticurveFilter.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IndexNameMulticurveFilter.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static IndexNameMulticurveFilter.Builder builder() {
    return new IndexNameMulticurveFilter.Builder();
  }

  @Override
  public IndexNameMulticurveFilter.Meta metaBean() {
    return IndexNameMulticurveFilter.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the index matched by this filter.
   * @return the value of the property, not null
   */
  public String getIndexName() {
    return _indexName;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IndexNameMulticurveFilter other = (IndexNameMulticurveFilter) obj;
      return JodaBeanUtils.equal(getIndexName(), other.getIndexName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getIndexName());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("IndexNameMulticurveFilter{");
    buf.append("indexName").append('=').append(JodaBeanUtils.toString(getIndexName()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IndexNameMulticurveFilter}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code indexName} property.
     */
    private final MetaProperty<String> _indexName = DirectMetaProperty.ofImmutable(
        this, "indexName", IndexNameMulticurveFilter.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "indexName");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -807707011:  // indexName
          return _indexName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public IndexNameMulticurveFilter.Builder builder() {
      return new IndexNameMulticurveFilter.Builder();
    }

    @Override
    public Class<? extends IndexNameMulticurveFilter> beanType() {
      return IndexNameMulticurveFilter.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code indexName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> indexName() {
      return _indexName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -807707011:  // indexName
          return ((IndexNameMulticurveFilter) bean).getIndexName();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code IndexNameMulticurveFilter}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<IndexNameMulticurveFilter> {

    private String _indexName;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(IndexNameMulticurveFilter beanToCopy) {
      this._indexName = beanToCopy.getIndexName();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -807707011:  // indexName
          return _indexName;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -807707011:  // indexName
          this._indexName = (String) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public IndexNameMulticurveFilter build() {
      return new IndexNameMulticurveFilter(
          _indexName);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code indexName} property in the builder.
     * @param indexName  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder indexName(String indexName) {
      JodaBeanUtils.notNull(indexName, "indexName");
      this._indexName = indexName;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("IndexNameMulticurveFilter.Builder{");
      buf.append("indexName").append('=').append(JodaBeanUtils.toString(_indexName));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}