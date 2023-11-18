/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package avro.alert;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Alert extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4128464964256628023L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Alert\",\"namespace\":\"avro.alert\",\"fields\":[{\"name\":\"sensor_id\",\"type\":\"long\",\"doc\":\"The unique id that identifies the sensor\"},{\"name\":\"time\",\"type\":\"long\",\"doc\":\"Time the alert was generated as UTC milliseconds from the epoch\"},{\"name\":\"status\",\"type\":{\"type\":\"enum\",\"name\":\"AlertStatus\",\"symbols\":[\"Critical\",\"Major\",\"Minor\",\"Warning\"]},\"doc\":\"The allowed values that our sensors will use to emit current status\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Alert> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Alert> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Alert> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Alert> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Alert> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Alert to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Alert from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Alert instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Alert fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** The unique id that identifies the sensor */
  private long sensor_id;
  /** Time the alert was generated as UTC milliseconds from the epoch */
  private long time;
  /** The allowed values that our sensors will use to emit current status */
  private avro.alert.AlertStatus status;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Alert() {}

  /**
   * All-args constructor.
   * @param sensor_id The unique id that identifies the sensor
   * @param time Time the alert was generated as UTC milliseconds from the epoch
   * @param status The allowed values that our sensors will use to emit current status
   */
  public Alert(java.lang.Long sensor_id, java.lang.Long time, avro.alert.AlertStatus status) {
    this.sensor_id = sensor_id;
    this.time = time;
    this.status = status;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sensor_id;
    case 1: return time;
    case 2: return status;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sensor_id = (java.lang.Long)value$; break;
    case 1: time = (java.lang.Long)value$; break;
    case 2: status = (avro.alert.AlertStatus)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'sensor_id' field.
   * @return The unique id that identifies the sensor
   */
  public long getSensorId() {
    return sensor_id;
  }


  /**
   * Sets the value of the 'sensor_id' field.
   * The unique id that identifies the sensor
   * @param value the value to set.
   */
  public void setSensorId(long value) {
    this.sensor_id = value;
  }

  /**
   * Gets the value of the 'time' field.
   * @return Time the alert was generated as UTC milliseconds from the epoch
   */
  public long getTime() {
    return time;
  }


  /**
   * Sets the value of the 'time' field.
   * Time the alert was generated as UTC milliseconds from the epoch
   * @param value the value to set.
   */
  public void setTime(long value) {
    this.time = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return The allowed values that our sensors will use to emit current status
   */
  public avro.alert.AlertStatus getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * The allowed values that our sensors will use to emit current status
   * @param value the value to set.
   */
  public void setStatus(avro.alert.AlertStatus value) {
    this.status = value;
  }

  /**
   * Creates a new Alert RecordBuilder.
   * @return A new Alert RecordBuilder
   */
  public static avro.alert.Alert.Builder newBuilder() {
    return new avro.alert.Alert.Builder();
  }

  /**
   * Creates a new Alert RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Alert RecordBuilder
   */
  public static avro.alert.Alert.Builder newBuilder(avro.alert.Alert.Builder other) {
    if (other == null) {
      return new avro.alert.Alert.Builder();
    } else {
      return new avro.alert.Alert.Builder(other);
    }
  }

  /**
   * Creates a new Alert RecordBuilder by copying an existing Alert instance.
   * @param other The existing instance to copy.
   * @return A new Alert RecordBuilder
   */
  public static avro.alert.Alert.Builder newBuilder(avro.alert.Alert other) {
    if (other == null) {
      return new avro.alert.Alert.Builder();
    } else {
      return new avro.alert.Alert.Builder(other);
    }
  }

  /**
   * RecordBuilder for Alert instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Alert>
    implements org.apache.avro.data.RecordBuilder<Alert> {

    /** The unique id that identifies the sensor */
    private long sensor_id;
    /** Time the alert was generated as UTC milliseconds from the epoch */
    private long time;
    /** The allowed values that our sensors will use to emit current status */
    private avro.alert.AlertStatus status;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(avro.alert.Alert.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sensor_id)) {
        this.sensor_id = data().deepCopy(fields()[0].schema(), other.sensor_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.time)) {
        this.time = data().deepCopy(fields()[1].schema(), other.time);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing Alert instance
     * @param other The existing instance to copy.
     */
    private Builder(avro.alert.Alert other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.sensor_id)) {
        this.sensor_id = data().deepCopy(fields()[0].schema(), other.sensor_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.time)) {
        this.time = data().deepCopy(fields()[1].schema(), other.time);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'sensor_id' field.
      * The unique id that identifies the sensor
      * @return The value.
      */
    public long getSensorId() {
      return sensor_id;
    }


    /**
      * Sets the value of the 'sensor_id' field.
      * The unique id that identifies the sensor
      * @param value The value of 'sensor_id'.
      * @return This builder.
      */
    public avro.alert.Alert.Builder setSensorId(long value) {
      validate(fields()[0], value);
      this.sensor_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'sensor_id' field has been set.
      * The unique id that identifies the sensor
      * @return True if the 'sensor_id' field has been set, false otherwise.
      */
    public boolean hasSensorId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'sensor_id' field.
      * The unique id that identifies the sensor
      * @return This builder.
      */
    public avro.alert.Alert.Builder clearSensorId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'time' field.
      * Time the alert was generated as UTC milliseconds from the epoch
      * @return The value.
      */
    public long getTime() {
      return time;
    }


    /**
      * Sets the value of the 'time' field.
      * Time the alert was generated as UTC milliseconds from the epoch
      * @param value The value of 'time'.
      * @return This builder.
      */
    public avro.alert.Alert.Builder setTime(long value) {
      validate(fields()[1], value);
      this.time = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'time' field has been set.
      * Time the alert was generated as UTC milliseconds from the epoch
      * @return True if the 'time' field has been set, false otherwise.
      */
    public boolean hasTime() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'time' field.
      * Time the alert was generated as UTC milliseconds from the epoch
      * @return This builder.
      */
    public avro.alert.Alert.Builder clearTime() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * The allowed values that our sensors will use to emit current status
      * @return The value.
      */
    public avro.alert.AlertStatus getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * The allowed values that our sensors will use to emit current status
      * @param value The value of 'status'.
      * @return This builder.
      */
    public avro.alert.Alert.Builder setStatus(avro.alert.AlertStatus value) {
      validate(fields()[2], value);
      this.status = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * The allowed values that our sensors will use to emit current status
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'status' field.
      * The allowed values that our sensors will use to emit current status
      * @return This builder.
      */
    public avro.alert.Alert.Builder clearStatus() {
      status = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Alert build() {
      try {
        Alert record = new Alert();
        record.sensor_id = fieldSetFlags()[0] ? this.sensor_id : (java.lang.Long) defaultValue(fields()[0]);
        record.time = fieldSetFlags()[1] ? this.time : (java.lang.Long) defaultValue(fields()[1]);
        record.status = fieldSetFlags()[2] ? this.status : (avro.alert.AlertStatus) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Alert>
    WRITER$ = (org.apache.avro.io.DatumWriter<Alert>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Alert>
    READER$ = (org.apache.avro.io.DatumReader<Alert>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.sensor_id);

    out.writeLong(this.time);

    out.writeEnum(this.status.ordinal());

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.sensor_id = in.readLong();

      this.time = in.readLong();

      this.status = avro.alert.AlertStatus.values()[in.readEnum()];

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.sensor_id = in.readLong();
          break;

        case 1:
          this.time = in.readLong();
          break;

        case 2:
          this.status = avro.alert.AlertStatus.values()[in.readEnum()];
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










