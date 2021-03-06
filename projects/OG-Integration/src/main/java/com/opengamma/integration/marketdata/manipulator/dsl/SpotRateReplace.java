/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.marketdata.manipulator.dsl;

import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.PropertyDefinition;

import com.opengamma.engine.function.FunctionExecutionContext;
import com.opengamma.engine.marketdata.manipulator.function.StructureManipulator;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.util.ArgumentChecker;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.joda.beans.Bean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 *
 */
@BeanDefinition
public final class SpotRateReplace implements StructureManipulator<Double>, ImmutableBean {

  @PropertyDefinition(validate = "notNull")
  private final Double _value;

  @ImmutableConstructor
  /* package */ SpotRateReplace(Number value) {
    _value = ArgumentChecker.notNull(value, "value").doubleValue();
  }

  @Override
  public Double execute(Double spotRate,
                        ValueSpecification valueSpecification,
                        FunctionExecutionContext executionContext) {
    return _value;
  }

  @Override
  public Class<Double> getExpectedType() {
    return Double.class;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SpotRateReplace}.
   * @return the meta-bean, not null
   */
  public static SpotRateReplace.Meta meta() {
    return SpotRateReplace.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SpotRateReplace.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static SpotRateReplace.Builder builder() {
    return new SpotRateReplace.Builder();
  }

  @Override
  public SpotRateReplace.Meta metaBean() {
    return SpotRateReplace.Meta.INSTANCE;
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
   * Gets the value.
   * @return the value of the property, not null
   */
  public Double getValue() {
    return _value;
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
      SpotRateReplace other = (SpotRateReplace) obj;
      return JodaBeanUtils.equal(getValue(), other.getValue());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getValue());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("SpotRateReplace{");
    buf.append("value").append('=').append(JodaBeanUtils.toString(getValue()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SpotRateReplace}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code value} property.
     */
    private final MetaProperty<Double> _value = DirectMetaProperty.ofImmutable(
        this, "value", SpotRateReplace.class, Double.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "value");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return _value;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public SpotRateReplace.Builder builder() {
      return new SpotRateReplace.Builder();
    }

    @Override
    public Class<? extends SpotRateReplace> beanType() {
      return SpotRateReplace.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code value} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> value() {
      return _value;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return ((SpotRateReplace) bean).getValue();
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
   * The bean-builder for {@code SpotRateReplace}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<SpotRateReplace> {

    private Double _value;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(SpotRateReplace beanToCopy) {
      this._value = beanToCopy.getValue();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          return _value;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 111972721:  // value
          this._value = (Double) newValue;
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
    public SpotRateReplace build() {
      return new SpotRateReplace(
          _value);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code value} property in the builder.
     * @param value  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder value(Double value) {
      JodaBeanUtils.notNull(value, "value");
      this._value = value;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("SpotRateReplace.Builder{");
      buf.append("value").append('=').append(JodaBeanUtils.toString(_value));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
