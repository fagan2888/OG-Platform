/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
import org.threeten.bp.ZonedDateTime;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.opengamma.engine.marketdata.spec.MarketDataSpecification;
import com.opengamma.sesame.engine.CalculationArguments;

/**
 * The set of cycle options to be used for a single cycle within a view execution.
 *
 * @deprecated use {@link CalculationArguments}
 */
@Deprecated
@BeanDefinition
public final class IndividualCycleOptions implements ImmutableBean, CycleOptions {

  /**
   * The specification for the market data to use during the execution of the cycle.
   */
  @PropertyDefinition(validate = "notNull")
  private final List<MarketDataSpecification> _marketDataSpecs;

  /**
   * The valuation time to use during the execution of the cycle.
   */
  @PropertyDefinition(validate = "notNull")
  private final ZonedDateTime _valuationTime;

  /**
   * The valuation time to use during the execution of the cycle.
   */
  @PropertyDefinition
  private final boolean _captureInputs;

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<IndividualCycleOptions> iterator() {
    return Iterators.forArray(this);
  }

  @ImmutableConstructor
  public IndividualCycleOptions(List<MarketDataSpecification> marketDataSpecs,
                                ZonedDateTime valuationTime,
                                boolean captureInputs) {
    JodaBeanUtils.notNull(marketDataSpecs, "marketDataSpecs");
    JodaBeanUtils.notNull(valuationTime, "valuationTime");
    _marketDataSpecs = ImmutableList.copyOf(marketDataSpecs);
    _valuationTime = valuationTime;
    _captureInputs = captureInputs;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IndividualCycleOptions}.
   * @return the meta-bean, not null
   */
  public static IndividualCycleOptions.Meta meta() {
    return IndividualCycleOptions.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IndividualCycleOptions.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static IndividualCycleOptions.Builder builder() {
    return new IndividualCycleOptions.Builder();
  }

  @Override
  public IndividualCycleOptions.Meta metaBean() {
    return IndividualCycleOptions.Meta.INSTANCE;
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
   * Gets the specification for the market data to use during the execution of the cycle.
   * @return the value of the property, not null
   */
  public List<MarketDataSpecification> getMarketDataSpecs() {
    return _marketDataSpecs;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuation time to use during the execution of the cycle.
   * @return the value of the property, not null
   */
  public ZonedDateTime getValuationTime() {
    return _valuationTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuation time to use during the execution of the cycle.
   * @return the value of the property
   */
  public boolean isCaptureInputs() {
    return _captureInputs;
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
      IndividualCycleOptions other = (IndividualCycleOptions) obj;
      return JodaBeanUtils.equal(getMarketDataSpecs(), other.getMarketDataSpecs()) &&
          JodaBeanUtils.equal(getValuationTime(), other.getValuationTime()) &&
          (isCaptureInputs() == other.isCaptureInputs());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getMarketDataSpecs());
    hash = hash * 31 + JodaBeanUtils.hashCode(getValuationTime());
    hash = hash * 31 + JodaBeanUtils.hashCode(isCaptureInputs());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("IndividualCycleOptions{");
    buf.append("marketDataSpecs").append('=').append(getMarketDataSpecs()).append(',').append(' ');
    buf.append("valuationTime").append('=').append(getValuationTime()).append(',').append(' ');
    buf.append("captureInputs").append('=').append(JodaBeanUtils.toString(isCaptureInputs()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IndividualCycleOptions}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code marketDataSpecs} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<MarketDataSpecification>> _marketDataSpecs = DirectMetaProperty.ofImmutable(
        this, "marketDataSpecs", IndividualCycleOptions.class, (Class) List.class);
    /**
     * The meta-property for the {@code valuationTime} property.
     */
    private final MetaProperty<ZonedDateTime> _valuationTime = DirectMetaProperty.ofImmutable(
        this, "valuationTime", IndividualCycleOptions.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code captureInputs} property.
     */
    private final MetaProperty<Boolean> _captureInputs = DirectMetaProperty.ofImmutable(
        this, "captureInputs", IndividualCycleOptions.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "marketDataSpecs",
        "valuationTime",
        "captureInputs");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 363784626:  // marketDataSpecs
          return _marketDataSpecs;
        case 113591406:  // valuationTime
          return _valuationTime;
        case 1669810383:  // captureInputs
          return _captureInputs;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public IndividualCycleOptions.Builder builder() {
      return new IndividualCycleOptions.Builder();
    }

    @Override
    public Class<? extends IndividualCycleOptions> beanType() {
      return IndividualCycleOptions.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code marketDataSpecs} property.
     * @return the meta-property, not null
     */
    public MetaProperty<List<MarketDataSpecification>> marketDataSpecs() {
      return _marketDataSpecs;
    }

    /**
     * The meta-property for the {@code valuationTime} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ZonedDateTime> valuationTime() {
      return _valuationTime;
    }

    /**
     * The meta-property for the {@code captureInputs} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Boolean> captureInputs() {
      return _captureInputs;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 363784626:  // marketDataSpecs
          return ((IndividualCycleOptions) bean).getMarketDataSpecs();
        case 113591406:  // valuationTime
          return ((IndividualCycleOptions) bean).getValuationTime();
        case 1669810383:  // captureInputs
          return ((IndividualCycleOptions) bean).isCaptureInputs();
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
   * The bean-builder for {@code IndividualCycleOptions}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<IndividualCycleOptions> {

    private List<MarketDataSpecification> _marketDataSpecs = new ArrayList<MarketDataSpecification>();
    private ZonedDateTime _valuationTime;
    private boolean _captureInputs;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(IndividualCycleOptions beanToCopy) {
      this._marketDataSpecs = new ArrayList<MarketDataSpecification>(beanToCopy.getMarketDataSpecs());
      this._valuationTime = beanToCopy.getValuationTime();
      this._captureInputs = beanToCopy.isCaptureInputs();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 363784626:  // marketDataSpecs
          return _marketDataSpecs;
        case 113591406:  // valuationTime
          return _valuationTime;
        case 1669810383:  // captureInputs
          return _captureInputs;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 363784626:  // marketDataSpecs
          this._marketDataSpecs = (List<MarketDataSpecification>) newValue;
          break;
        case 113591406:  // valuationTime
          this._valuationTime = (ZonedDateTime) newValue;
          break;
        case 1669810383:  // captureInputs
          this._captureInputs = (Boolean) newValue;
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
    public IndividualCycleOptions build() {
      return new IndividualCycleOptions(
          _marketDataSpecs,
          _valuationTime,
          _captureInputs);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code marketDataSpecs} property in the builder.
     * @param marketDataSpecs  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder marketDataSpecs(List<MarketDataSpecification> marketDataSpecs) {
      JodaBeanUtils.notNull(marketDataSpecs, "marketDataSpecs");
      this._marketDataSpecs = marketDataSpecs;
      return this;
    }

    /**
     * Sets the {@code marketDataSpecs} property in the builder
     * from an array of objects.
     * @param marketDataSpecs  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder marketDataSpecs(MarketDataSpecification... marketDataSpecs) {
      return marketDataSpecs(Arrays.asList(marketDataSpecs));
    }

    /**
     * Sets the {@code valuationTime} property in the builder.
     * @param valuationTime  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder valuationTime(ZonedDateTime valuationTime) {
      JodaBeanUtils.notNull(valuationTime, "valuationTime");
      this._valuationTime = valuationTime;
      return this;
    }

    /**
     * Sets the {@code captureInputs} property in the builder.
     * @param captureInputs  the new value
     * @return this, for chaining, not null
     */
    public Builder captureInputs(boolean captureInputs) {
      this._captureInputs = captureInputs;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("IndividualCycleOptions.Builder{");
      buf.append("marketDataSpecs").append('=').append(JodaBeanUtils.toString(_marketDataSpecs)).append(',').append(' ');
      buf.append("valuationTime").append('=').append(JodaBeanUtils.toString(_valuationTime)).append(',').append(' ');
      buf.append("captureInputs").append('=').append(JodaBeanUtils.toString(_captureInputs));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
