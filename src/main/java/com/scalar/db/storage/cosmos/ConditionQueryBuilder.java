package com.scalar.db.storage.cosmos;

import com.scalar.db.api.ConditionalExpression;
import com.scalar.db.api.DeleteIf;
import com.scalar.db.api.DeleteIfExists;
import com.scalar.db.api.MutationConditionVisitor;
import com.scalar.db.api.PutIf;
import com.scalar.db.api.PutIfExists;
import com.scalar.db.api.PutIfNotExists;
import javax.annotation.concurrent.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Makes a query statement for a stored procedure of Cosmos DB from conditions
 *
 * @author Yuji Ito
 */
@NotThreadSafe
public class ConditionQueryBuilder implements MutationConditionVisitor {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConditionQueryBuilder.class);
  private final StringBuilder builder;
  private final ValueBinder binder;

  public ConditionQueryBuilder(String id) {
    // TODO: replace StringBuilder with SQL statement builder
    builder = new StringBuilder();
    builder.append("SELECT * FROM Record r WHERE r.id = '" + id + "'");
    binder = new ValueBinder(builder);
  }

  public String build() {
    return new String(builder);
  }

  /**
   * Adds {@code PutIf}-specific conditions to the query
   *
   * @param condition {@code PutIf} condition
   */
  @Override
  public void visit(PutIf condition) {
    condition
        .getExpressions()
        .forEach(
            e -> {
              builder.append(" AND r.values." + e.getName());
              appendOperator(e);
              e.getValue().accept(binder);
            });
  }

  /**
   * Adds {@code PutIfExists}-specific conditions to the query
   *
   * @param condition {@code PutIfExists} condition
   */
  @Override
  public void visit(PutIfExists condition) {
    // nothing to do
  }

  /**
   * Adds {@code PutIfNotExists}-specific conditions to the query
   *
   * @param condition {@code PutIfNotExists} condition
   */
  @Override
  public void visit(PutIfNotExists condition) {
    // nothing to do
  }

  /**
   * Adds {@code DeleteIf}-specific conditions to the query
   *
   * @param condition {@code DeleteIf} condition
   */
  @Override
  public void visit(DeleteIf condition) {
    condition
        .getExpressions()
        .forEach(
            e -> {
              builder.append(" AND r." + e.getName());
              appendOperator(e);
              e.getValue().accept(binder);
            });
  }

  /**
   * Adds {@code DeleteIfExists}-specific conditions to the query
   *
   * @param condition {@code DeleteIfExists} condition
   */
  @Override
  public void visit(DeleteIfExists condition) {
    // nothing to do
  }

  private void appendOperator(ConditionalExpression e) {
    switch (e.getOperator()) {
      case EQ:
        builder.append(" = ");
        break;
      case NE:
        builder.append(" != ");
        break;
      case GT:
        builder.append(" > ");
        break;
      case GTE:
        builder.append(" >= ");
        break;
      case LT:
        builder.append(" < ");
        break;
      case LTE:
        builder.append(" <= ");
        break;
      default:
        // never comes here because ConditionalExpression accepts only above operators
        throw new IllegalArgumentException(e.getOperator() + " is not supported");
    }
  }
}
