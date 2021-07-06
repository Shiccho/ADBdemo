// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scalardb.proto

package com.scalar.db.rpc;

public interface TransactionResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:rpc.TransactionResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.rpc.TransactionResponse.StartResponse start_response = 1;</code>
   * @return Whether the startResponse field is set.
   */
  boolean hasStartResponse();
  /**
   * <code>.rpc.TransactionResponse.StartResponse start_response = 1;</code>
   * @return The startResponse.
   */
  com.scalar.db.rpc.TransactionResponse.StartResponse getStartResponse();
  /**
   * <code>.rpc.TransactionResponse.StartResponse start_response = 1;</code>
   */
  com.scalar.db.rpc.TransactionResponse.StartResponseOrBuilder getStartResponseOrBuilder();

  /**
   * <code>.rpc.TransactionResponse.GetResponse get_response = 2;</code>
   * @return Whether the getResponse field is set.
   */
  boolean hasGetResponse();
  /**
   * <code>.rpc.TransactionResponse.GetResponse get_response = 2;</code>
   * @return The getResponse.
   */
  com.scalar.db.rpc.TransactionResponse.GetResponse getGetResponse();
  /**
   * <code>.rpc.TransactionResponse.GetResponse get_response = 2;</code>
   */
  com.scalar.db.rpc.TransactionResponse.GetResponseOrBuilder getGetResponseOrBuilder();

  /**
   * <code>.rpc.TransactionResponse.ScanResponse scan_response = 3;</code>
   * @return Whether the scanResponse field is set.
   */
  boolean hasScanResponse();
  /**
   * <code>.rpc.TransactionResponse.ScanResponse scan_response = 3;</code>
   * @return The scanResponse.
   */
  com.scalar.db.rpc.TransactionResponse.ScanResponse getScanResponse();
  /**
   * <code>.rpc.TransactionResponse.ScanResponse scan_response = 3;</code>
   */
  com.scalar.db.rpc.TransactionResponse.ScanResponseOrBuilder getScanResponseOrBuilder();

  /**
   * <code>.rpc.TransactionResponse.Error error = 4;</code>
   * @return Whether the error field is set.
   */
  boolean hasError();
  /**
   * <code>.rpc.TransactionResponse.Error error = 4;</code>
   * @return The error.
   */
  com.scalar.db.rpc.TransactionResponse.Error getError();
  /**
   * <code>.rpc.TransactionResponse.Error error = 4;</code>
   */
  com.scalar.db.rpc.TransactionResponse.ErrorOrBuilder getErrorOrBuilder();

  public com.scalar.db.rpc.TransactionResponse.ResponseCase getResponseCase();
}