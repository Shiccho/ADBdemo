package com.scalar.db.exception.transaction;

/**
 * An exception thrown when preparing a transaction fails due to transient or nontransient faults.
 * You can try retrying the transaction from the beginning, but the transaction may still fail if
 * the cause is nontranient.
 */
public class PreparationException extends TransactionException {

  public PreparationException(String message, String transactionId) {
    super(message, transactionId);
  }

  public PreparationException(String message, Throwable cause, String transactionId) {
    super(message, cause, transactionId);
  }
}
