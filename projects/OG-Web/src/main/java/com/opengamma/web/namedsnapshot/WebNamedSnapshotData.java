/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.namedsnapshot;

import java.util.Map;

import javax.ws.rs.core.UriInfo;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.opengamma.core.marketdatasnapshot.NamedSnapshot;
import com.opengamma.id.UniqueId;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotDocument;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotMaster;
import com.opengamma.web.WebPerRequestData;

/**
 * Data class for web-based snapshot management.
 */
@BeanDefinition
public class WebNamedSnapshotData extends WebPerRequestData {

  /**
   * The snapshot master.
   */
  @PropertyDefinition
  private MarketDataSnapshotMaster _snapshotMaster;
  /**
   * The type of data being stored.
   */
  @PropertyDefinition
  private Class<?> _type;
  /**
   * The snapshot id from the input URI.
   */
  @PropertyDefinition
  private String _uriSnapshotId;
  /**
   * The version id from the URI.
   */
  @PropertyDefinition
  private String _uriVersionId;
  /**
   * The snapshot.
   */
  @PropertyDefinition
  private MarketDataSnapshotDocument _snapshot;
  /**
   * The versioned snapshot.
   */
  @PropertyDefinition
  private MarketDataSnapshotDocument _versioned;
  /**
   * The valid map of types.
   */
  @PropertyDefinition(set = "setClearPutAll")
  private final BiMap<String, Class<? extends NamedSnapshot>> _typeMap = HashBiMap.create();

  /**
   * Creates an instance.
   */
  public WebNamedSnapshotData() {
  }

  /**
   * Creates an instance.
   * @param uriInfo  the URI information
   */
  public WebNamedSnapshotData(final UriInfo uriInfo) {
    setUriInfo(uriInfo);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the best available snapshot id.
   * @param overrideId  the override id, null derives the result from the data
   * @return the id, may be null
   */
  public String getBestSnapshotUriId(final UniqueId overrideId) {
    if (overrideId != null) {
      return overrideId.toLatest().toString();
    }
    return getSnapshot() != null ? getSnapshot().getUniqueId().toLatest().toString() : getUriSnapshotId();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code WebNamedSnapshotData}.
   * @return the meta-bean, not null
   */
  public static WebNamedSnapshotData.Meta meta() {
    return WebNamedSnapshotData.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(WebNamedSnapshotData.Meta.INSTANCE);
  }

  @Override
  public WebNamedSnapshotData.Meta metaBean() {
    return WebNamedSnapshotData.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the snapshot master.
   * @return the value of the property
   */
  public MarketDataSnapshotMaster getSnapshotMaster() {
    return _snapshotMaster;
  }

  /**
   * Sets the snapshot master.
   * @param snapshotMaster  the new value of the property
   */
  public void setSnapshotMaster(MarketDataSnapshotMaster snapshotMaster) {
    this._snapshotMaster = snapshotMaster;
  }

  /**
   * Gets the the {@code snapshotMaster} property.
   * @return the property, not null
   */
  public final Property<MarketDataSnapshotMaster> snapshotMaster() {
    return metaBean().snapshotMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the type of data being stored.
   * @return the value of the property
   */
  public Class<?> getType() {
    return _type;
  }

  /**
   * Sets the type of data being stored.
   * @param type  the new value of the property
   */
  public void setType(Class<?> type) {
    this._type = type;
  }

  /**
   * Gets the the {@code type} property.
   * @return the property, not null
   */
  public final Property<Class<?>> type() {
    return metaBean().type().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the snapshot id from the input URI.
   * @return the value of the property
   */
  public String getUriSnapshotId() {
    return _uriSnapshotId;
  }

  /**
   * Sets the snapshot id from the input URI.
   * @param uriSnapshotId  the new value of the property
   */
  public void setUriSnapshotId(String uriSnapshotId) {
    this._uriSnapshotId = uriSnapshotId;
  }

  /**
   * Gets the the {@code uriSnapshotId} property.
   * @return the property, not null
   */
  public final Property<String> uriSnapshotId() {
    return metaBean().uriSnapshotId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the version id from the URI.
   * @return the value of the property
   */
  public String getUriVersionId() {
    return _uriVersionId;
  }

  /**
   * Sets the version id from the URI.
   * @param uriVersionId  the new value of the property
   */
  public void setUriVersionId(String uriVersionId) {
    this._uriVersionId = uriVersionId;
  }

  /**
   * Gets the the {@code uriVersionId} property.
   * @return the property, not null
   */
  public final Property<String> uriVersionId() {
    return metaBean().uriVersionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the snapshot.
   * @return the value of the property
   */
  public MarketDataSnapshotDocument getSnapshot() {
    return _snapshot;
  }

  /**
   * Sets the snapshot.
   * @param snapshot  the new value of the property
   */
  public void setSnapshot(MarketDataSnapshotDocument snapshot) {
    this._snapshot = snapshot;
  }

  /**
   * Gets the the {@code snapshot} property.
   * @return the property, not null
   */
  public final Property<MarketDataSnapshotDocument> snapshot() {
    return metaBean().snapshot().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the versioned snapshot.
   * @return the value of the property
   */
  public MarketDataSnapshotDocument getVersioned() {
    return _versioned;
  }

  /**
   * Sets the versioned snapshot.
   * @param versioned  the new value of the property
   */
  public void setVersioned(MarketDataSnapshotDocument versioned) {
    this._versioned = versioned;
  }

  /**
   * Gets the the {@code versioned} property.
   * @return the property, not null
   */
  public final Property<MarketDataSnapshotDocument> versioned() {
    return metaBean().versioned().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valid map of types.
   * @return the value of the property, not null
   */
  public BiMap<String, Class<? extends NamedSnapshot>> getTypeMap() {
    return _typeMap;
  }

  /**
   * Sets the valid map of types.
   * @param typeMap  the new value of the property, not null
   */
  public void setTypeMap(BiMap<String, Class<? extends NamedSnapshot>> typeMap) {
    JodaBeanUtils.notNull(typeMap, "typeMap");
    this._typeMap.clear();
    this._typeMap.putAll(typeMap);
  }

  /**
   * Gets the the {@code typeMap} property.
   * @return the property, not null
   */
  public final Property<BiMap<String, Class<? extends NamedSnapshot>>> typeMap() {
    return metaBean().typeMap().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public WebNamedSnapshotData clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      WebNamedSnapshotData other = (WebNamedSnapshotData) obj;
      return JodaBeanUtils.equal(getSnapshotMaster(), other.getSnapshotMaster()) &&
          JodaBeanUtils.equal(getType(), other.getType()) &&
          JodaBeanUtils.equal(getUriSnapshotId(), other.getUriSnapshotId()) &&
          JodaBeanUtils.equal(getUriVersionId(), other.getUriVersionId()) &&
          JodaBeanUtils.equal(getSnapshot(), other.getSnapshot()) &&
          JodaBeanUtils.equal(getVersioned(), other.getVersioned()) &&
          JodaBeanUtils.equal(getTypeMap(), other.getTypeMap()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getSnapshotMaster());
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUriSnapshotId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getUriVersionId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSnapshot());
    hash = hash * 31 + JodaBeanUtils.hashCode(getVersioned());
    hash = hash * 31 + JodaBeanUtils.hashCode(getTypeMap());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("WebNamedSnapshotData{");
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
    buf.append("snapshotMaster").append('=').append(JodaBeanUtils.toString(getSnapshotMaster())).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType())).append(',').append(' ');
    buf.append("uriSnapshotId").append('=').append(JodaBeanUtils.toString(getUriSnapshotId())).append(',').append(' ');
    buf.append("uriVersionId").append('=').append(JodaBeanUtils.toString(getUriVersionId())).append(',').append(' ');
    buf.append("snapshot").append('=').append(JodaBeanUtils.toString(getSnapshot())).append(',').append(' ');
    buf.append("versioned").append('=').append(JodaBeanUtils.toString(getVersioned())).append(',').append(' ');
    buf.append("typeMap").append('=').append(JodaBeanUtils.toString(getTypeMap())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code WebNamedSnapshotData}.
   */
  public static class Meta extends WebPerRequestData.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code snapshotMaster} property.
     */
    private final MetaProperty<MarketDataSnapshotMaster> _snapshotMaster = DirectMetaProperty.ofReadWrite(
        this, "snapshotMaster", WebNamedSnapshotData.class, MarketDataSnapshotMaster.class);
    /**
     * The meta-property for the {@code type} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<?>> _type = DirectMetaProperty.ofReadWrite(
        this, "type", WebNamedSnapshotData.class, (Class) Class.class);
    /**
     * The meta-property for the {@code uriSnapshotId} property.
     */
    private final MetaProperty<String> _uriSnapshotId = DirectMetaProperty.ofReadWrite(
        this, "uriSnapshotId", WebNamedSnapshotData.class, String.class);
    /**
     * The meta-property for the {@code uriVersionId} property.
     */
    private final MetaProperty<String> _uriVersionId = DirectMetaProperty.ofReadWrite(
        this, "uriVersionId", WebNamedSnapshotData.class, String.class);
    /**
     * The meta-property for the {@code snapshot} property.
     */
    private final MetaProperty<MarketDataSnapshotDocument> _snapshot = DirectMetaProperty.ofReadWrite(
        this, "snapshot", WebNamedSnapshotData.class, MarketDataSnapshotDocument.class);
    /**
     * The meta-property for the {@code versioned} property.
     */
    private final MetaProperty<MarketDataSnapshotDocument> _versioned = DirectMetaProperty.ofReadWrite(
        this, "versioned", WebNamedSnapshotData.class, MarketDataSnapshotDocument.class);
    /**
     * The meta-property for the {@code typeMap} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<BiMap<String, Class<? extends NamedSnapshot>>> _typeMap = DirectMetaProperty.ofReadWrite(
        this, "typeMap", WebNamedSnapshotData.class, (Class) BiMap.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "snapshotMaster",
        "type",
        "uriSnapshotId",
        "uriVersionId",
        "snapshot",
        "versioned",
        "typeMap");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -2046916282:  // snapshotMaster
          return _snapshotMaster;
        case 3575610:  // type
          return _type;
        case -1254537077:  // uriSnapshotId
          return _uriSnapshotId;
        case 666567687:  // uriVersionId
          return _uriVersionId;
        case 284874180:  // snapshot
          return _snapshot;
        case -1407102089:  // versioned
          return _versioned;
        case -853107774:  // typeMap
          return _typeMap;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends WebNamedSnapshotData> builder() {
      return new DirectBeanBuilder<WebNamedSnapshotData>(new WebNamedSnapshotData());
    }

    @Override
    public Class<? extends WebNamedSnapshotData> beanType() {
      return WebNamedSnapshotData.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code snapshotMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MarketDataSnapshotMaster> snapshotMaster() {
      return _snapshotMaster;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Class<?>> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code uriSnapshotId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> uriSnapshotId() {
      return _uriSnapshotId;
    }

    /**
     * The meta-property for the {@code uriVersionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> uriVersionId() {
      return _uriVersionId;
    }

    /**
     * The meta-property for the {@code snapshot} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MarketDataSnapshotDocument> snapshot() {
      return _snapshot;
    }

    /**
     * The meta-property for the {@code versioned} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MarketDataSnapshotDocument> versioned() {
      return _versioned;
    }

    /**
     * The meta-property for the {@code typeMap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BiMap<String, Class<? extends NamedSnapshot>>> typeMap() {
      return _typeMap;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -2046916282:  // snapshotMaster
          return ((WebNamedSnapshotData) bean).getSnapshotMaster();
        case 3575610:  // type
          return ((WebNamedSnapshotData) bean).getType();
        case -1254537077:  // uriSnapshotId
          return ((WebNamedSnapshotData) bean).getUriSnapshotId();
        case 666567687:  // uriVersionId
          return ((WebNamedSnapshotData) bean).getUriVersionId();
        case 284874180:  // snapshot
          return ((WebNamedSnapshotData) bean).getSnapshot();
        case -1407102089:  // versioned
          return ((WebNamedSnapshotData) bean).getVersioned();
        case -853107774:  // typeMap
          return ((WebNamedSnapshotData) bean).getTypeMap();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -2046916282:  // snapshotMaster
          ((WebNamedSnapshotData) bean).setSnapshotMaster((MarketDataSnapshotMaster) newValue);
          return;
        case 3575610:  // type
          ((WebNamedSnapshotData) bean).setType((Class<?>) newValue);
          return;
        case -1254537077:  // uriSnapshotId
          ((WebNamedSnapshotData) bean).setUriSnapshotId((String) newValue);
          return;
        case 666567687:  // uriVersionId
          ((WebNamedSnapshotData) bean).setUriVersionId((String) newValue);
          return;
        case 284874180:  // snapshot
          ((WebNamedSnapshotData) bean).setSnapshot((MarketDataSnapshotDocument) newValue);
          return;
        case -1407102089:  // versioned
          ((WebNamedSnapshotData) bean).setVersioned((MarketDataSnapshotDocument) newValue);
          return;
        case -853107774:  // typeMap
          ((WebNamedSnapshotData) bean).setTypeMap((BiMap<String, Class<? extends NamedSnapshot>>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((WebNamedSnapshotData) bean)._typeMap, "typeMap");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
