// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RequestMsg.proto

package org.spoom.im.sdk.server.model.proto;

public final class RequestMsgProto {
  private RequestMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RequestMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.spoom.im.sdk.server.model.proto.RequestMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 msgId = 1;</code>
     */
    long getMsgId();

    /**
     * <code>int32 chatType = 2;</code>
     */
    int getChatType();

    /**
     * <code>string body = 3;</code>
     */
    String getBody();
    /**
     * <code>string body = 3;</code>
     */
    com.google.protobuf.ByteString
        getBodyBytes();

    /**
     * <code>string from = 4;</code>
     */
    String getFrom();
    /**
     * <code>string from = 4;</code>
     */
    com.google.protobuf.ByteString
        getFromBytes();

    /**
     * <code>string to = 5;</code>
     */
    String getTo();
    /**
     * <code>string to = 5;</code>
     */
    com.google.protobuf.ByteString
        getToBytes();

    /**
     * <code>int64 time = 6;</code>
     */
    long getTime();
  }
  /**
   * Protobuf type {@code org.spoom.im.sdk.server.model.proto.RequestMsg}
   */
  public  static final class RequestMsg extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:org.spoom.im.sdk.server.model.proto.RequestMsg)
      RequestMsgOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use RequestMsg.newBuilder() to construct.
    private RequestMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private RequestMsg() {
      msgId_ = 0L;
      chatType_ = 0;
      body_ = "";
      from_ = "";
      to_ = "";
      time_ = 0L;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private RequestMsg(
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

              msgId_ = input.readInt64();
              break;
            }
            case 16: {

              chatType_ = input.readInt32();
              break;
            }
            case 26: {
              String s = input.readStringRequireUtf8();

              body_ = s;
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();

              from_ = s;
              break;
            }
            case 42: {
              String s = input.readStringRequireUtf8();

              to_ = s;
              break;
            }
            case 48: {

              time_ = input.readInt64();
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
      return RequestMsgProto.internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return RequestMsgProto.internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              RequestMsgProto.RequestMsg.class, RequestMsgProto.RequestMsg.Builder.class);
    }

    public static final int MSGID_FIELD_NUMBER = 1;
    private long msgId_;
    /**
     * <code>int64 msgId = 1;</code>
     */
    public long getMsgId() {
      return msgId_;
    }

    public static final int CHATTYPE_FIELD_NUMBER = 2;
    private int chatType_;
    /**
     * <code>int32 chatType = 2;</code>
     */
    public int getChatType() {
      return chatType_;
    }

    public static final int BODY_FIELD_NUMBER = 3;
    private volatile Object body_;
    /**
     * <code>string body = 3;</code>
     */
    public String getBody() {
      Object ref = body_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        body_ = s;
        return s;
      }
    }
    /**
     * <code>string body = 3;</code>
     */
    public com.google.protobuf.ByteString
        getBodyBytes() {
      Object ref = body_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        body_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int FROM_FIELD_NUMBER = 4;
    private volatile Object from_;
    /**
     * <code>string from = 4;</code>
     */
    public String getFrom() {
      Object ref = from_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        from_ = s;
        return s;
      }
    }
    /**
     * <code>string from = 4;</code>
     */
    public com.google.protobuf.ByteString
        getFromBytes() {
      Object ref = from_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        from_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TO_FIELD_NUMBER = 5;
    private volatile Object to_;
    /**
     * <code>string to = 5;</code>
     */
    public String getTo() {
      Object ref = to_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        to_ = s;
        return s;
      }
    }
    /**
     * <code>string to = 5;</code>
     */
    public com.google.protobuf.ByteString
        getToBytes() {
      Object ref = to_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        to_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIME_FIELD_NUMBER = 6;
    private long time_;
    /**
     * <code>int64 time = 6;</code>
     */
    public long getTime() {
      return time_;
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
      if (msgId_ != 0L) {
        output.writeInt64(1, msgId_);
      }
      if (chatType_ != 0) {
        output.writeInt32(2, chatType_);
      }
      if (!getBodyBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, body_);
      }
      if (!getFromBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, from_);
      }
      if (!getToBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, to_);
      }
      if (time_ != 0L) {
        output.writeInt64(6, time_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (msgId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, msgId_);
      }
      if (chatType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, chatType_);
      }
      if (!getBodyBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, body_);
      }
      if (!getFromBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, from_);
      }
      if (!getToBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, to_);
      }
      if (time_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, time_);
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
      if (!(obj instanceof RequestMsgProto.RequestMsg)) {
        return super.equals(obj);
      }
      RequestMsgProto.RequestMsg other = (RequestMsgProto.RequestMsg) obj;

      boolean result = true;
      result = result && (getMsgId()
          == other.getMsgId());
      result = result && (getChatType()
          == other.getChatType());
      result = result && getBody()
          .equals(other.getBody());
      result = result && getFrom()
          .equals(other.getFrom());
      result = result && getTo()
          .equals(other.getTo());
      result = result && (getTime()
          == other.getTime());
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
      hash = (37 * hash) + MSGID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMsgId());
      hash = (37 * hash) + CHATTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getChatType();
      hash = (37 * hash) + BODY_FIELD_NUMBER;
      hash = (53 * hash) + getBody().hashCode();
      hash = (37 * hash) + FROM_FIELD_NUMBER;
      hash = (53 * hash) + getFrom().hashCode();
      hash = (37 * hash) + TO_FIELD_NUMBER;
      hash = (53 * hash) + getTo().hashCode();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTime());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static RequestMsgProto.RequestMsg parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RequestMsgProto.RequestMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static RequestMsgProto.RequestMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static RequestMsgProto.RequestMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static RequestMsgProto.RequestMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static RequestMsgProto.RequestMsg parseFrom(
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
    public static Builder newBuilder(RequestMsgProto.RequestMsg prototype) {
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
     * Protobuf type {@code org.spoom.im.sdk.server.model.proto.RequestMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.spoom.im.sdk.server.model.proto.RequestMsg)
        RequestMsgProto.RequestMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return RequestMsgProto.internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return RequestMsgProto.internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                RequestMsgProto.RequestMsg.class, RequestMsgProto.RequestMsg.Builder.class);
      }

      // Construct using org.spoom.im.sdk.server.model.proto.RequestMsgProto.RequestMsg.newBuilder()
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
        msgId_ = 0L;

        chatType_ = 0;

        body_ = "";

        from_ = "";

        to_ = "";

        time_ = 0L;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return RequestMsgProto.internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_descriptor;
      }

      public RequestMsgProto.RequestMsg getDefaultInstanceForType() {
        return RequestMsgProto.RequestMsg.getDefaultInstance();
      }

      public RequestMsgProto.RequestMsg build() {
        RequestMsgProto.RequestMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public RequestMsgProto.RequestMsg buildPartial() {
        RequestMsgProto.RequestMsg result = new RequestMsgProto.RequestMsg(this);
        result.msgId_ = msgId_;
        result.chatType_ = chatType_;
        result.body_ = body_;
        result.from_ = from_;
        result.to_ = to_;
        result.time_ = time_;
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
        if (other instanceof RequestMsgProto.RequestMsg) {
          return mergeFrom((RequestMsgProto.RequestMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(RequestMsgProto.RequestMsg other) {
        if (other == RequestMsgProto.RequestMsg.getDefaultInstance()) return this;
        if (other.getMsgId() != 0L) {
          setMsgId(other.getMsgId());
        }
        if (other.getChatType() != 0) {
          setChatType(other.getChatType());
        }
        if (!other.getBody().isEmpty()) {
          body_ = other.body_;
          onChanged();
        }
        if (!other.getFrom().isEmpty()) {
          from_ = other.from_;
          onChanged();
        }
        if (!other.getTo().isEmpty()) {
          to_ = other.to_;
          onChanged();
        }
        if (other.getTime() != 0L) {
          setTime(other.getTime());
        }
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
        RequestMsgProto.RequestMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (RequestMsgProto.RequestMsg) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private long msgId_ ;
      /**
       * <code>int64 msgId = 1;</code>
       */
      public long getMsgId() {
        return msgId_;
      }
      /**
       * <code>int64 msgId = 1;</code>
       */
      public Builder setMsgId(long value) {

        msgId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 msgId = 1;</code>
       */
      public Builder clearMsgId() {

        msgId_ = 0L;
        onChanged();
        return this;
      }

      private int chatType_ ;
      /**
       * <code>int32 chatType = 2;</code>
       */
      public int getChatType() {
        return chatType_;
      }
      /**
       * <code>int32 chatType = 2;</code>
       */
      public Builder setChatType(int value) {

        chatType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 chatType = 2;</code>
       */
      public Builder clearChatType() {

        chatType_ = 0;
        onChanged();
        return this;
      }

      private Object body_ = "";
      /**
       * <code>string body = 3;</code>
       */
      public String getBody() {
        Object ref = body_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          body_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string body = 3;</code>
       */
      public com.google.protobuf.ByteString
          getBodyBytes() {
        Object ref = body_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          body_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string body = 3;</code>
       */
      public Builder setBody(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string body = 3;</code>
       */
      public Builder clearBody() {

        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }
      /**
       * <code>string body = 3;</code>
       */
      public Builder setBodyBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        body_ = value;
        onChanged();
        return this;
      }

      private Object from_ = "";
      /**
       * <code>string from = 4;</code>
       */
      public String getFrom() {
        Object ref = from_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          from_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string from = 4;</code>
       */
      public com.google.protobuf.ByteString
          getFromBytes() {
        Object ref = from_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          from_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string from = 4;</code>
       */
      public Builder setFrom(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        from_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string from = 4;</code>
       */
      public Builder clearFrom() {

        from_ = getDefaultInstance().getFrom();
        onChanged();
        return this;
      }
      /**
       * <code>string from = 4;</code>
       */
      public Builder setFromBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        from_ = value;
        onChanged();
        return this;
      }

      private Object to_ = "";
      /**
       * <code>string to = 5;</code>
       */
      public String getTo() {
        Object ref = to_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          to_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string to = 5;</code>
       */
      public com.google.protobuf.ByteString
          getToBytes() {
        Object ref = to_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          to_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string to = 5;</code>
       */
      public Builder setTo(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }

        to_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string to = 5;</code>
       */
      public Builder clearTo() {

        to_ = getDefaultInstance().getTo();
        onChanged();
        return this;
      }
      /**
       * <code>string to = 5;</code>
       */
      public Builder setToBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

        to_ = value;
        onChanged();
        return this;
      }

      private long time_ ;
      /**
       * <code>int64 time = 6;</code>
       */
      public long getTime() {
        return time_;
      }
      /**
       * <code>int64 time = 6;</code>
       */
      public Builder setTime(long value) {

        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 time = 6;</code>
       */
      public Builder clearTime() {

        time_ = 0L;
        onChanged();
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


      // @@protoc_insertion_point(builder_scope:org.spoom.im.sdk.server.model.proto.RequestMsg)
    }

    // @@protoc_insertion_point(class_scope:org.spoom.im.sdk.server.model.proto.RequestMsg)
    private static final RequestMsgProto.RequestMsg DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new RequestMsgProto.RequestMsg();
    }

    public static RequestMsgProto.RequestMsg getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RequestMsg>
        PARSER = new com.google.protobuf.AbstractParser<RequestMsg>() {
      public RequestMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new RequestMsg(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<RequestMsg> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<RequestMsg> getParserForType() {
      return PARSER;
    }

    public RequestMsgProto.RequestMsg getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\020RequestMsg.proto\022#org.spoom.im.sdk.ser" +
      "ver.model.proto\"c\n\nRequestMsg\022\r\n\005msgId\030\001" +
      " \001(\003\022\020\n\010chatType\030\002 \001(\005\022\014\n\004body\030\003 \001(\t\022\014\n\004" +
      "from\030\004 \001(\t\022\n\n\002to\030\005 \001(\t\022\014\n\004time\030\006 \001(\003B\021B\017" +
      "RequestMsgProtob\006proto3"
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
    internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_spoom_im_sdk_server_model_proto_RequestMsg_descriptor,
        new String[] { "MsgId", "ChatType", "Body", "From", "To", "Time", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
