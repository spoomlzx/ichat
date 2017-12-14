// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Reply.proto

package org.spoom.im.sdk.server.model.proto;

public final class ReplyProto {
  private ReplyProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ReplyOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.spoom.im.sdk.server.model.proto.Reply)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 action = 1;</code>
     */
    int getAction();

    /**
     * <code>int32 code = 2;</code>
     */
    int getCode();

    /**
     * <code>string message = 3;</code>
     */
    String getMessage();
    /**
     * <code>string message = 3;</code>
     */
    com.google.protobuf.ByteString
        getMessageBytes();

    /**
     * <code>int64 time = 4;</code>
     */
    long getTime();

    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */
    int getDataCount();
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */
    boolean containsData(
            String key);
    /**
     * Use {@link #getDataMap()} instead.
     */
    @Deprecated
    java.util.Map<String, String>
    getData();
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */
    java.util.Map<String, String>
    getDataMap();
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */

    String getDataOrDefault(
            String key,
            String defaultValue);
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */

    String getDataOrThrow(
            String key);
  }
  /**
   * Protobuf type {@code org.spoom.im.sdk.server.model.proto.Reply}
   */
  public  static final class Reply extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:org.spoom.im.sdk.server.model.proto.Reply)
      ReplyOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Reply.newBuilder() to construct.
    private Reply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Reply() {
      action_ = 0;
      code_ = 0;
      message_ = "";
      time_ = 0L;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Reply(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              action_ = input.readInt32();
              break;
            }
            case 16: {

              code_ = input.readInt32();
              break;
            }
            case 26: {
              String s = input.readStringRequireUtf8();

              message_ = s;
              break;
            }
            case 32: {

              time_ = input.readInt64();
              break;
            }
            case 42: {
              if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
                data_ = com.google.protobuf.MapField.newMapField(
                    DataDefaultEntryHolder.defaultEntry);
                mutable_bitField0_ |= 0x00000010;
              }
              com.google.protobuf.MapEntry<String, String>
              data__ = input.readMessage(
                  DataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              data_.getMutableMap().put(
                  data__.getKey(), data__.getValue());
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ReplyProto.internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 5:
          return internalGetData();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ReplyProto.internal_static_org_spoom_im_sdk_server_model_proto_Reply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ReplyProto.Reply.class, ReplyProto.Reply.Builder.class);
    }

    private int bitField0_;
    public static final int ACTION_FIELD_NUMBER = 1;
    private int action_;
    /**
     * <code>int32 action = 1;</code>
     */
    public int getAction() {
      return action_;
    }

    public static final int CODE_FIELD_NUMBER = 2;
    private int code_;
    /**
     * <code>int32 code = 2;</code>
     */
    public int getCode() {
      return code_;
    }

    public static final int MESSAGE_FIELD_NUMBER = 3;
    private volatile Object message_;
    /**
     * <code>string message = 3;</code>
     */
    public String getMessage() {
      Object ref = message_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        message_ = s;
        return s;
      }
    }
    /**
     * <code>string message = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIME_FIELD_NUMBER = 4;
    private long time_;
    /**
     * <code>int64 time = 4;</code>
     */
    public long getTime() {
      return time_;
    }

    public static final int DATA_FIELD_NUMBER = 5;
    private static final class DataDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          String, String> defaultEntry =
              com.google.protobuf.MapEntry
              .<String, String>newDefaultInstance(
                  ReplyProto.internal_static_org_spoom_im_sdk_server_model_proto_Reply_DataEntry_descriptor,
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "",
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "");
    }
    private com.google.protobuf.MapField<
        String, String> data_;
    private com.google.protobuf.MapField<String, String>
    internalGetData() {
      if (data_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            DataDefaultEntryHolder.defaultEntry);
      }
      return data_;
    }

    public int getDataCount() {
      return internalGetData().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */

    public boolean containsData(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      return internalGetData().getMap().containsKey(key);
    }
    /**
     * Use {@link #getDataMap()} instead.
     */
    @Deprecated
    public java.util.Map<String, String> getData() {
      return getDataMap();
    }
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */

    public java.util.Map<String, String> getDataMap() {
      return internalGetData().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */

    public String getDataOrDefault(
        String key,
        String defaultValue) {
      if (key == null) { throw new NullPointerException(); }
      java.util.Map<String, String> map =
          internalGetData().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; data = 5;</code>
     */

    public String getDataOrThrow(
        String key) {
      if (key == null) { throw new NullPointerException(); }
      java.util.Map<String, String> map =
          internalGetData().getMap();
      if (!map.containsKey(key)) {
        throw new IllegalArgumentException();
      }
      return map.get(key);
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (action_ != 0) {
        output.writeInt32(1, action_);
      }
      if (code_ != 0) {
        output.writeInt32(2, code_);
      }
      if (!getMessageBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, message_);
      }
      if (time_ != 0L) {
        output.writeInt64(4, time_);
      }
      com.google.protobuf.GeneratedMessageV3
        .serializeStringMapTo(
          output,
          internalGetData(),
          DataDefaultEntryHolder.defaultEntry,
          5);
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (action_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, action_);
      }
      if (code_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, code_);
      }
      if (!getMessageBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, message_);
      }
      if (time_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, time_);
      }
      for (java.util.Map.Entry<String, String> entry
           : internalGetData().getMap().entrySet()) {
        com.google.protobuf.MapEntry<String, String>
        data__ = DataDefaultEntryHolder.defaultEntry.newBuilderForType()
            .setKey(entry.getKey())
            .setValue(entry.getValue())
            .build();
        size += com.google.protobuf.CodedOutputStream
            .computeMessageSize(5, data__);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof ReplyProto.Reply)) {
        return super.equals(obj);
      }
      ReplyProto.Reply other = (ReplyProto.Reply) obj;

      boolean result = true;
      result = result && (getAction()
          == other.getAction());
      result = result && (getCode()
          == other.getCode());
      result = result && getMessage()
          .equals(other.getMessage());
      result = result && (getTime()
          == other.getTime());
      result = result && internalGetData().equals(
          other.internalGetData());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ACTION_FIELD_NUMBER;
      hash = (53 * hash) + getAction();
      hash = (37 * hash) + CODE_FIELD_NUMBER;
      hash = (53 * hash) + getCode();
      hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getMessage().hashCode();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTime());
      if (!internalGetData().getMap().isEmpty()) {
        hash = (37 * hash) + DATA_FIELD_NUMBER;
        hash = (53 * hash) + internalGetData().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ReplyProto.Reply parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ReplyProto.Reply parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ReplyProto.Reply parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ReplyProto.Reply parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ReplyProto.Reply parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ReplyProto.Reply parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ReplyProto.Reply parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ReplyProto.Reply parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ReplyProto.Reply parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ReplyProto.Reply parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ReplyProto.Reply parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ReplyProto.Reply parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(ReplyProto.Reply prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code org.spoom.im.sdk.server.model.proto.Reply}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.spoom.im.sdk.server.model.proto.Reply)
        ReplyProto.ReplyOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ReplyProto.internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 5:
            return internalGetData();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 5:
            return internalGetMutableData();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ReplyProto.internal_static_org_spoom_im_sdk_server_model_proto_Reply_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ReplyProto.Reply.class, ReplyProto.Reply.Builder.class);
      }

      // Construct using org.spoom.im.sdk.server.model.proto.ReplyProto.Reply.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        action_ = 0;

        code_ = 0;

        message_ = "";

        time_ = 0L;

        internalGetMutableData().clear();
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ReplyProto.internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor;
      }

      public ReplyProto.Reply getDefaultInstanceForType() {
        return ReplyProto.Reply.getDefaultInstance();
      }

      public ReplyProto.Reply build() {
        ReplyProto.Reply result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public ReplyProto.Reply buildPartial() {
        ReplyProto.Reply result = new ReplyProto.Reply(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.action_ = action_;
        result.code_ = code_;
        result.message_ = message_;
        result.time_ = time_;
        result.data_ = internalGetData();
        result.data_.makeImmutable();
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ReplyProto.Reply) {
          return mergeFrom((ReplyProto.Reply)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ReplyProto.Reply other) {
        if (other == ReplyProto.Reply.getDefaultInstance()) return this;
        if (other.getAction() != 0) {
          setAction(other.getAction());
        }
        if (other.getCode() != 0) {
          setCode(other.getCode());
        }
        if (!other.getMessage().isEmpty()) {
          message_ = other.message_;
          onChanged();
        }
        if (other.getTime() != 0L) {
          setTime(other.getTime());
        }
        internalGetMutableData().mergeFrom(
            other.internalGetData());
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        ReplyProto.Reply parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ReplyProto.Reply) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int action_ ;
      /**
       * <code>int32 action = 1;</code>
       */
      public int getAction() {
        return action_;
      }
      /**
       * <code>int32 action = 1;</code>
       */
      public Builder setAction(int value) {

        action_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 action = 1;</code>
       */
      public Builder clearAction() {

        action_ = 0;
        onChanged();
        return this;
      }

      private int code_ ;
      /**
       * <code>int32 code = 2;</code>
       */
      public int getCode() {
        return code_;
      }
      /**
       * <code>int32 code = 2;</code>
       */
      public Builder setCode(int value) {

        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 code = 2;</code>
       */
      public Builder clearCode() {

        code_ = 0;
        onChanged();
        return this;
      }

      private Object message_ = "";
      /**
       * <code>string message = 3;</code>
       */
      public String getMessage() {
        Object ref = message_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          message_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string message = 3;</code>
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string message = 3;</code>
       */
      public Builder setMessage(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string message = 3;</code>
       */
      public Builder clearMessage() {

        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <code>string message = 3;</code>
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        message_ = value;
        onChanged();
        return this;
      }

      private long time_ ;
      /**
       * <code>int64 time = 4;</code>
       */
      public long getTime() {
        return time_;
      }
      /**
       * <code>int64 time = 4;</code>
       */
      public Builder setTime(long value) {

        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 time = 4;</code>
       */
      public Builder clearTime() {

        time_ = 0L;
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          String, String> data_;
      private com.google.protobuf.MapField<String, String>
      internalGetData() {
        if (data_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              DataDefaultEntryHolder.defaultEntry);
        }
        return data_;
      }
      private com.google.protobuf.MapField<String, String>
      internalGetMutableData() {
        onChanged();;
        if (data_ == null) {
          data_ = com.google.protobuf.MapField.newMapField(
              DataDefaultEntryHolder.defaultEntry);
        }
        if (!data_.isMutable()) {
          data_ = data_.copy();
        }
        return data_;
      }

      public int getDataCount() {
        return internalGetData().getMap().size();
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */

      public boolean containsData(
          String key) {
        if (key == null) { throw new NullPointerException(); }
        return internalGetData().getMap().containsKey(key);
      }
      /**
       * Use {@link #getDataMap()} instead.
       */
      @Deprecated
      public java.util.Map<String, String> getData() {
        return getDataMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */

      public java.util.Map<String, String> getDataMap() {
        return internalGetData().getMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */

      public String getDataOrDefault(
          String key,
          String defaultValue) {
        if (key == null) { throw new NullPointerException(); }
        java.util.Map<String, String> map =
            internalGetData().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */

      public String getDataOrThrow(
          String key) {
        if (key == null) { throw new NullPointerException(); }
        java.util.Map<String, String> map =
            internalGetData().getMap();
        if (!map.containsKey(key)) {
          throw new IllegalArgumentException();
        }
        return map.get(key);
      }

      public Builder clearData() {
        internalGetMutableData().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */

      public Builder removeData(
          String key) {
        if (key == null) { throw new NullPointerException(); }
        internalGetMutableData().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @Deprecated
      public java.util.Map<String, String>
      getMutableData() {
        return internalGetMutableData().getMutableMap();
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */
      public Builder putData(
          String key,
          String value) {
        if (key == null) { throw new NullPointerException(); }
        if (value == null) { throw new NullPointerException(); }
        internalGetMutableData().getMutableMap()
            .put(key, value);
        return this;
      }
      /**
       * <code>map&lt;string, string&gt; data = 5;</code>
       */

      public Builder putAllData(
          java.util.Map<String, String> values) {
        internalGetMutableData().getMutableMap()
            .putAll(values);
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:org.spoom.im.sdk.server.model.proto.Reply)
    }

    // @@protoc_insertion_point(class_scope:org.spoom.im.sdk.server.model.proto.Reply)
    private static final ReplyProto.Reply DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ReplyProto.Reply();
    }

    public static ReplyProto.Reply getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Reply>
        PARSER = new com.google.protobuf.AbstractParser<Reply>() {
      public Reply parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Reply(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Reply> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<Reply> getParserForType() {
      return PARSER;
    }

    public ReplyProto.Reply getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_spoom_im_sdk_server_model_proto_Reply_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_spoom_im_sdk_server_model_proto_Reply_DataEntry_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_spoom_im_sdk_server_model_proto_Reply_DataEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\013Reply.proto\022#org.spoom.im.sdk.server.m" +
      "odel.proto\"\265\001\n\005Reply\022\016\n\006action\030\001 \001(\005\022\014\n\004" +
      "code\030\002 \001(\005\022\017\n\007message\030\003 \001(\t\022\014\n\004time\030\004 \001(" +
      "\003\022B\n\004data\030\005 \003(\01324.org.spoom.im.sdk.serve" +
      "r.model.proto.Reply.DataEntry\032+\n\tDataEnt" +
      "ry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001B\014B\nRe" +
      "plyProtob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_spoom_im_sdk_server_model_proto_Reply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor,
        new String[] { "Action", "Code", "Message", "Time", "Data", });
    internal_static_org_spoom_im_sdk_server_model_proto_Reply_DataEntry_descriptor =
      internal_static_org_spoom_im_sdk_server_model_proto_Reply_descriptor.getNestedTypes().get(0);
    internal_static_org_spoom_im_sdk_server_model_proto_Reply_DataEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_spoom_im_sdk_server_model_proto_Reply_DataEntry_descriptor,
        new String[] { "Key", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
