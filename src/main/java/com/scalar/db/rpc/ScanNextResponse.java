// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scalardb.proto

package com.scalar.db.rpc;

/**
 * Protobuf type {@code rpc.ScanNextResponse}
 */
public final class ScanNextResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:rpc.ScanNextResponse)
    ScanNextResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ScanNextResponse.newBuilder() to construct.
  private ScanNextResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ScanNextResponse() {
    result_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ScanNextResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ScanNextResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              result_ = new java.util.ArrayList<com.scalar.db.rpc.Result>();
              mutable_bitField0_ |= 0x00000001;
            }
            result_.add(
                input.readMessage(com.scalar.db.rpc.Result.parser(), extensionRegistry));
            break;
          }
          case 16: {

            hasMoreResults_ = input.readBool();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        result_ = java.util.Collections.unmodifiableList(result_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.scalar.db.rpc.ScalarDbProto.internal_static_rpc_ScanNextResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.scalar.db.rpc.ScalarDbProto.internal_static_rpc_ScanNextResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.scalar.db.rpc.ScanNextResponse.class, com.scalar.db.rpc.ScanNextResponse.Builder.class);
  }

  public static final int RESULT_FIELD_NUMBER = 1;
  private java.util.List<com.scalar.db.rpc.Result> result_;
  /**
   * <code>repeated .rpc.Result result = 1;</code>
   */
  @java.lang.Override
  public java.util.List<com.scalar.db.rpc.Result> getResultList() {
    return result_;
  }
  /**
   * <code>repeated .rpc.Result result = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.scalar.db.rpc.ResultOrBuilder> 
      getResultOrBuilderList() {
    return result_;
  }
  /**
   * <code>repeated .rpc.Result result = 1;</code>
   */
  @java.lang.Override
  public int getResultCount() {
    return result_.size();
  }
  /**
   * <code>repeated .rpc.Result result = 1;</code>
   */
  @java.lang.Override
  public com.scalar.db.rpc.Result getResult(int index) {
    return result_.get(index);
  }
  /**
   * <code>repeated .rpc.Result result = 1;</code>
   */
  @java.lang.Override
  public com.scalar.db.rpc.ResultOrBuilder getResultOrBuilder(
      int index) {
    return result_.get(index);
  }

  public static final int HAS_MORE_RESULTS_FIELD_NUMBER = 2;
  private boolean hasMoreResults_;
  /**
   * <code>bool has_more_results = 2;</code>
   * @return The hasMoreResults.
   */
  @java.lang.Override
  public boolean getHasMoreResults() {
    return hasMoreResults_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < result_.size(); i++) {
      output.writeMessage(1, result_.get(i));
    }
    if (hasMoreResults_ != false) {
      output.writeBool(2, hasMoreResults_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < result_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, result_.get(i));
    }
    if (hasMoreResults_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, hasMoreResults_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.scalar.db.rpc.ScanNextResponse)) {
      return super.equals(obj);
    }
    com.scalar.db.rpc.ScanNextResponse other = (com.scalar.db.rpc.ScanNextResponse) obj;

    if (!getResultList()
        .equals(other.getResultList())) return false;
    if (getHasMoreResults()
        != other.getHasMoreResults()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getResultCount() > 0) {
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getResultList().hashCode();
    }
    hash = (37 * hash) + HAS_MORE_RESULTS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getHasMoreResults());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.scalar.db.rpc.ScanNextResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.scalar.db.rpc.ScanNextResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code rpc.ScanNextResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:rpc.ScanNextResponse)
      com.scalar.db.rpc.ScanNextResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.scalar.db.rpc.ScalarDbProto.internal_static_rpc_ScanNextResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.scalar.db.rpc.ScalarDbProto.internal_static_rpc_ScanNextResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.scalar.db.rpc.ScanNextResponse.class, com.scalar.db.rpc.ScanNextResponse.Builder.class);
    }

    // Construct using com.scalar.db.rpc.ScanNextResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getResultFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (resultBuilder_ == null) {
        result_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        resultBuilder_.clear();
      }
      hasMoreResults_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.scalar.db.rpc.ScalarDbProto.internal_static_rpc_ScanNextResponse_descriptor;
    }

    @java.lang.Override
    public com.scalar.db.rpc.ScanNextResponse getDefaultInstanceForType() {
      return com.scalar.db.rpc.ScanNextResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.scalar.db.rpc.ScanNextResponse build() {
      com.scalar.db.rpc.ScanNextResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.scalar.db.rpc.ScanNextResponse buildPartial() {
      com.scalar.db.rpc.ScanNextResponse result = new com.scalar.db.rpc.ScanNextResponse(this);
      int from_bitField0_ = bitField0_;
      if (resultBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          result_ = java.util.Collections.unmodifiableList(result_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.result_ = result_;
      } else {
        result.result_ = resultBuilder_.build();
      }
      result.hasMoreResults_ = hasMoreResults_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.scalar.db.rpc.ScanNextResponse) {
        return mergeFrom((com.scalar.db.rpc.ScanNextResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.scalar.db.rpc.ScanNextResponse other) {
      if (other == com.scalar.db.rpc.ScanNextResponse.getDefaultInstance()) return this;
      if (resultBuilder_ == null) {
        if (!other.result_.isEmpty()) {
          if (result_.isEmpty()) {
            result_ = other.result_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureResultIsMutable();
            result_.addAll(other.result_);
          }
          onChanged();
        }
      } else {
        if (!other.result_.isEmpty()) {
          if (resultBuilder_.isEmpty()) {
            resultBuilder_.dispose();
            resultBuilder_ = null;
            result_ = other.result_;
            bitField0_ = (bitField0_ & ~0x00000001);
            resultBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getResultFieldBuilder() : null;
          } else {
            resultBuilder_.addAllMessages(other.result_);
          }
        }
      }
      if (other.getHasMoreResults() != false) {
        setHasMoreResults(other.getHasMoreResults());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.scalar.db.rpc.ScanNextResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.scalar.db.rpc.ScanNextResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.scalar.db.rpc.Result> result_ =
      java.util.Collections.emptyList();
    private void ensureResultIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        result_ = new java.util.ArrayList<com.scalar.db.rpc.Result>(result_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.scalar.db.rpc.Result, com.scalar.db.rpc.Result.Builder, com.scalar.db.rpc.ResultOrBuilder> resultBuilder_;

    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public java.util.List<com.scalar.db.rpc.Result> getResultList() {
      if (resultBuilder_ == null) {
        return java.util.Collections.unmodifiableList(result_);
      } else {
        return resultBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public int getResultCount() {
      if (resultBuilder_ == null) {
        return result_.size();
      } else {
        return resultBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public com.scalar.db.rpc.Result getResult(int index) {
      if (resultBuilder_ == null) {
        return result_.get(index);
      } else {
        return resultBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder setResult(
        int index, com.scalar.db.rpc.Result value) {
      if (resultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResultIsMutable();
        result_.set(index, value);
        onChanged();
      } else {
        resultBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder setResult(
        int index, com.scalar.db.rpc.Result.Builder builderForValue) {
      if (resultBuilder_ == null) {
        ensureResultIsMutable();
        result_.set(index, builderForValue.build());
        onChanged();
      } else {
        resultBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder addResult(com.scalar.db.rpc.Result value) {
      if (resultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResultIsMutable();
        result_.add(value);
        onChanged();
      } else {
        resultBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder addResult(
        int index, com.scalar.db.rpc.Result value) {
      if (resultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureResultIsMutable();
        result_.add(index, value);
        onChanged();
      } else {
        resultBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder addResult(
        com.scalar.db.rpc.Result.Builder builderForValue) {
      if (resultBuilder_ == null) {
        ensureResultIsMutable();
        result_.add(builderForValue.build());
        onChanged();
      } else {
        resultBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder addResult(
        int index, com.scalar.db.rpc.Result.Builder builderForValue) {
      if (resultBuilder_ == null) {
        ensureResultIsMutable();
        result_.add(index, builderForValue.build());
        onChanged();
      } else {
        resultBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder addAllResult(
        java.lang.Iterable<? extends com.scalar.db.rpc.Result> values) {
      if (resultBuilder_ == null) {
        ensureResultIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, result_);
        onChanged();
      } else {
        resultBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder clearResult() {
      if (resultBuilder_ == null) {
        result_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        resultBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public Builder removeResult(int index) {
      if (resultBuilder_ == null) {
        ensureResultIsMutable();
        result_.remove(index);
        onChanged();
      } else {
        resultBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public com.scalar.db.rpc.Result.Builder getResultBuilder(
        int index) {
      return getResultFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public com.scalar.db.rpc.ResultOrBuilder getResultOrBuilder(
        int index) {
      if (resultBuilder_ == null) {
        return result_.get(index);  } else {
        return resultBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public java.util.List<? extends com.scalar.db.rpc.ResultOrBuilder> 
         getResultOrBuilderList() {
      if (resultBuilder_ != null) {
        return resultBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(result_);
      }
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public com.scalar.db.rpc.Result.Builder addResultBuilder() {
      return getResultFieldBuilder().addBuilder(
          com.scalar.db.rpc.Result.getDefaultInstance());
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public com.scalar.db.rpc.Result.Builder addResultBuilder(
        int index) {
      return getResultFieldBuilder().addBuilder(
          index, com.scalar.db.rpc.Result.getDefaultInstance());
    }
    /**
     * <code>repeated .rpc.Result result = 1;</code>
     */
    public java.util.List<com.scalar.db.rpc.Result.Builder> 
         getResultBuilderList() {
      return getResultFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.scalar.db.rpc.Result, com.scalar.db.rpc.Result.Builder, com.scalar.db.rpc.ResultOrBuilder> 
        getResultFieldBuilder() {
      if (resultBuilder_ == null) {
        resultBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.scalar.db.rpc.Result, com.scalar.db.rpc.Result.Builder, com.scalar.db.rpc.ResultOrBuilder>(
                result_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        result_ = null;
      }
      return resultBuilder_;
    }

    private boolean hasMoreResults_ ;
    /**
     * <code>bool has_more_results = 2;</code>
     * @return The hasMoreResults.
     */
    @java.lang.Override
    public boolean getHasMoreResults() {
      return hasMoreResults_;
    }
    /**
     * <code>bool has_more_results = 2;</code>
     * @param value The hasMoreResults to set.
     * @return This builder for chaining.
     */
    public Builder setHasMoreResults(boolean value) {
      
      hasMoreResults_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool has_more_results = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearHasMoreResults() {
      
      hasMoreResults_ = false;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:rpc.ScanNextResponse)
  }

  // @@protoc_insertion_point(class_scope:rpc.ScanNextResponse)
  private static final com.scalar.db.rpc.ScanNextResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.scalar.db.rpc.ScanNextResponse();
  }

  public static com.scalar.db.rpc.ScanNextResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ScanNextResponse>
      PARSER = new com.google.protobuf.AbstractParser<ScanNextResponse>() {
    @java.lang.Override
    public ScanNextResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ScanNextResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ScanNextResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ScanNextResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.scalar.db.rpc.ScanNextResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

