package com.scalar.db.transaction.common;

import com.scalar.db.api.Delete;
import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.api.Get;
import com.scalar.db.api.Mutation;
import com.scalar.db.api.Put;
import com.scalar.db.api.Scan;
import com.scalar.db.util.ScalarDbUtils;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDistributedTransaction implements DistributedTransaction {

  private Optional<String> namespace;
  private Optional<String> tableName;

  public AbstractDistributedTransaction() {
    namespace = Optional.empty();
    tableName = Optional.empty();
  }

  @Deprecated
  @Override
  public void with(String namespace, String tableName) {
    this.namespace = Optional.ofNullable(namespace);
    this.tableName = Optional.ofNullable(tableName);
  }

  @Deprecated
  @Override
  public void withNamespace(String namespace) {
    this.namespace = Optional.ofNullable(namespace);
  }

  @Deprecated
  @Override
  public Optional<String> getNamespace() {
    return namespace;
  }

  @Deprecated
  @Override
  public void withTable(String tableName) {
    this.tableName = Optional.ofNullable(tableName);
  }

  @Deprecated
  @Override
  public Optional<String> getTable() {
    return tableName;
  }

  protected <T extends Mutation> List<T> copyAndSetTargetToIfNot(List<T> mutations) {
    return ScalarDbUtils.copyAndSetTargetToIfNot(mutations, namespace, tableName);
  }

  protected Get copyAndSetTargetToIfNot(Get get) {
    return ScalarDbUtils.copyAndSetTargetToIfNot(get, namespace, tableName);
  }

  protected Scan copyAndSetTargetToIfNot(Scan scan) {
    return ScalarDbUtils.copyAndSetTargetToIfNot(scan, namespace, tableName);
  }

  protected Put copyAndSetTargetToIfNot(Put put) {
    return ScalarDbUtils.copyAndSetTargetToIfNot(put, namespace, tableName);
  }

  protected Delete copyAndSetTargetToIfNot(Delete delete) {
    return ScalarDbUtils.copyAndSetTargetToIfNot(delete, namespace, tableName);
  }
}