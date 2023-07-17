package sample;

import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.api.DistributedTransactionManager;
import com.scalar.db.api.Get;
import com.scalar.db.api.Put;
import com.scalar.db.api.Result;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalar.db.service.TransactionFactory;
import java.io.IOException;
import java.util.Optional;
import java.util.ArrayList;


public class UserSystem {
  private final DistributedTransactionManager manager;

  public UserSystem(String scalarDBProperties) throws IOException {
    TransactionFactory factory = TransactionFactory.create(scalarDBProperties);
    manager = factory.getTransactionManager();
  }

  public boolean signup(String id, String password) throws TransactionException {
    // Start a transaction
    DistributedTransaction tx = manager.start();
    String NAMESPACE = "user"; //UserNAMESPACE
    String TABLENAME = "users";
    String ID = "user-id";
    String PASSWORD = "password";
    
    try {
      // check id
      Get get =
          Get.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofText(ID, id))
              .build();
      Optional<Result> result = tx.get(get);
      
      if (result.isPresent()) {
        return false; // already registered the id
      }

      Put put =
          Put.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofText(ID, id))
              .textValue(PASSWORD, password)
              .build();
      tx.put(put);
      // Commit the transaction (records are automatically recovered in case of failure)
      tx.commit();
      return true;
    } catch (Exception e) {
      tx.abort();
      throw e;
    }
  }

  /* compare the password and login. Need register user */
  public boolean login(String id, String password) throws TransactionException {
    // Start a transaction
    DistributedTransaction tx = manager.start();
    String NAMESPACE = "user"; //UserNAMESPACE
    String TABLENAME = "users";
    String ID = "user-id";
    String PASSWORD = "password";
    boolean isLogIn = false;
    
    try {
      // Retrieve the current balance for id
      Get get =
          Get.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofText(ID, id))
              .build();
      Optional<Result> result = tx.get(get);

      if (result.isPresent()) {
        String correct_pass = result.get().getText(PASSWORD);
        if (correct_pass.equals(password)) {
          isLogIn = true;
        } else {
          isLogIn = false;
        }
      }
      // Commit the transaction (records are automatically recovered in case of failure)
      tx.commit();
      return isLogIn;
    } catch (Exception e) {
      tx.abort();
      throw e;
    }
  }


  /* show the reservationinfo. it is possible only from reserved-id for now */
  public ArrayList<String> showReservationInfo(int id) throws TransactionException { 
    // Start a transaction
    DistributedTransaction tx = manager.start();
    String NAMESPACE = "reservedinfo";
    String TABLENAME = "reservedinfos";
    String ID = "reserved-id";
    String DATE = "date";
    String DEPATURE = "depature";
    String DESTINATION = "destination";

    try {
      // Retrieve the current balances for id
      Get get =
          Get.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofInt(ID, id))
              .build();
      Optional<Result> result = tx.get(get);

      ArrayList<String> ret = new ArrayList<String>();
      if (result.isPresent()) {
        ret.add(result.get().getText(DATE));
        ret.add(result.get().getText(DEPATURE));
        ret.add(result.get().getText(DESTINATION));
      }

      // Commit the transaction
      tx.commit();

      return ret; //if the id dosen't exit, return the empty array. 
    } catch (Exception e) {
      tx.abort();
      throw e;
    }
  }

  /* register the reservation info */
  public void putResevertionInfo(int user_id, String date, String depature, String destination) throws TransactionException { 
    // Start a transaction
    DistributedTransaction tx = manager.start();
    String NAMESPACE = "reservedinfo";
    String TABLENAME = "reservedinfos";
    String ID = "reserved-id";
    String USERID = "user-id";
    String DATE = "date";
    String DEPATURE = "depature";
    String DESTINATION = "destination";
    int cnt = 0;

    try {
      cnt++;

      Put put =
          Put.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofInt(ID, cnt))
              .intValue(USERID, user_id)
              .textValue(DATE, date)
              .textValue(DEPATURE, depature)
              .textValue(DESTINATION, destination)
              .build();
      tx.put(put);

      // Commit the transaction (records are automatically recovered in case of failure)
      tx.commit();
    } catch (Exception e) {
      tx.abort();
      throw e;
    }
  }

  /* register the payment info */
  public void putPayInfo(int user_id, String password, String card_number) throws TransactionException { 
    // Start a transaction
    DistributedTransaction tx = manager.start();
    String NAMESPACE = "pay";
    String TABLENAME = "pays";
    String ID = "credit-id";
    String USERID = "user-id";
    String PASSWORD = "credit-password";
    String CARDNUMBER = "card-number";
    int cnt = 0;

    try {
      // Update the balance
      cnt++;
      
      Put put =
          Put.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofInt(ID, cnt))
              .intValue(USERID, user_id)
              .textValue(PASSWORD, password)
              .textValue(CARDNUMBER, card_number)
              .build();
      tx.put(put);

      // Commit the transaction (records are automatically recovered in case of failure)
      tx.commit();
    } catch (Exception e) {
      tx.abort();
      throw e;
    }
  }

}
