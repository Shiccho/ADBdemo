package sample;
import com.scalar.db.api.DistributedTransaction;
import com.scalar.db.api.DistributedTransactionManager;
import com.scalar.db.api.Get;
import com.scalar.db.api.Scan;
import com.scalar.db.api.Put;
import com.scalar.db.api.Result;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalar.db.service.TransactionFactory;
import java.io.IOException;
import java.util.*;

public class PathData {
    // Super Class of each trafficroot Database class
    private static final String NAMESPACE = "trafficroot";
    private String TABLENAME = "sample_table";
    private static final String ID = "id";
    private static final String SRC = "depature";
    private static final String DST = "destination";
    private static final String TIME = "required-time";
    private static final String FEE = "fee";
    private static final String VACANTSEAT = "vacant-seat";
    private int nextId = 1;

    private final DistributedTransactionManager manager;

    public PathData(DistributedTransactionManager manager) {
      this.manager = manager;
    }

    public void setTableName(String name) {
      this.TABLENAME = name;
    }

    public void add(String src, String dst, int time, int fee, int seat) throws TransactionException{
      DistributedTransaction tx = manager.start();
      try {
        // Add Path
        Put put =
            Put.newBuilder()
                .namespace(NAMESPACE)
                .table(TABLENAME)
                .partitionKey(Key.ofInt(ID, nextId++))
                .textValue(SRC, src)
                .textValue(DST, dst)
                .intValue(TIME, time)
                .intValue(FEE, fee)
                .intValue(VACANTSEAT, seat)
                .build();
        tx.put(put);
        
        tx.commit();

      } catch (Exception e) {
        tx.abort();
        throw e;
      }
    }
    public void book(int id) throws TransactionException{
      // reduce vacant seat
      DistributedTransaction tx = manager.start();
      try{
        Optional<Integer> result;
        int seat;
        result = this.getSeat(id);
        if (result.isPresent()) {
          seat = result.get(); 
          seat--;
          // Update the vacantseat
          Put put =
              Put.newBuilder()
                  .namespace(NAMESPACE)
                  .table(TABLENAME)
                  .partitionKey(Key.ofInt(ID, id))
                  .intValue(VACANTSEAT, seat)
                  .build();
          tx.put(put);
        }
        
        // Commit the transaction (records are automatically recovered in case of failure)
        tx.commit();
      } catch (Exception e) {
        tx.abort();
        throw e;
      }

    }

    public ArrayList<MyPath> getFromSrc(String src, int trafficroot) throws TransactionException{
      DistributedTransaction tx = manager.start();
      try{
        // Retrieve the path from src
        Scan scan =
            Scan.newBuilder()
                .namespace(NAMESPACE)
                .table(TABLENAME)
                .indexKey(Key.ofText(SRC, src))
                .limit(10)
                .build();
        List<Result> results = tx.scan(scan);
        // Get paths
        ArrayList<MyPath> paths = new ArrayList<MyPath>();
        if (results.size() != 0) {
          for(Result result: results) {
            MyPath path = new MyPath(
                result.getInt(ID), 
                result.getText(SRC), 
                result.getText(DST), 
                result.getInt(TIME),
                result.getInt(FEE),
                trafficroot
            );
            paths.add(path);
          }
        } else {
          System.out.println("empty");
        }
        return paths;
      } catch (Exception e) {
        tx.abort();
        throw e;
      }
    }
    
    public ArrayList<MyPath> getFromDst(String dst, int trafficroot) throws TransactionException{
      DistributedTransaction tx = manager.start();
      try{
        // Retrieve the path from src
        Scan scan =
            Scan.newBuilder()
                .namespace(NAMESPACE)
                .table(TABLENAME)
                .indexKey(Key.ofText(DST, dst))
                .projections(SRC, DST, TIME, FEE, VACANTSEAT)
                .limit(10)
                .build();
        List<Result> results = tx.scan(scan);
        // Get paths
        ArrayList<MyPath> paths = new ArrayList<MyPath>();
        if (results.size() != 0) {
          for(Result result: results) {
            MyPath path = new MyPath(
                result.getInt(ID), 
                result.getText(SRC), 
                result.getText(DST), 
                result.getInt(TIME),
                result.getInt(FEE),
                trafficroot
            );
            paths.add(path);
          }
        }
        return paths;
      } catch (Exception e) {
        tx.abort();
        throw e;
      }
    }

    public Optional<Integer> getTime(int id) throws TransactionException{
      DistributedTransaction tx = manager.start();
      // Retrieve the current vacantseat for id
      try {
        Get get =
            Get.newBuilder()
                .namespace(NAMESPACE)
                .table(TABLENAME)
                .partitionKey(Key.ofInt(ID, id))
                .build();
        Optional<Result> result = tx.get(get);
        // getTime
        Optional<Integer> time = null;
        if (result.isPresent()) {
          time = Optional.of((result.get().getInt(TIME)));
        }
        return time;
      } catch (Exception e) {
        tx.abort();
        throw e;
      }
    }
    
    public Optional<Integer> getFee(int id) throws TransactionException{
      DistributedTransaction tx = manager.start();
      // Retrieve the current vacantseat for id
      try {
        Get get =
            Get.newBuilder()
                .namespace(NAMESPACE)
                .table(TABLENAME)
                .partitionKey(Key.ofInt(ID, id))
                .build();
        Optional<Result> result = tx.get(get);
        // get fee
        Optional<Integer> time = null;
        if (result.isPresent()) {
          time = Optional.of((result.get().getInt(FEE)));
        }
        return time;
      } catch (Exception e) {
        tx.abort();
        throw e;
      }
    }
    
    public Optional<Integer> getSeat(int id) throws TransactionException{
      DistributedTransaction tx = manager.start();
      // Retrieve the current vacantseat for id
      Get get =
          Get.newBuilder()
              .namespace(NAMESPACE)
              .table(TABLENAME)
              .partitionKey(Key.ofInt(ID, id))
              .build();
      Optional<Result> result = tx.get(get);
      // Calculate the balance
      int current;
      Optional<Integer> time = null;
        if (result.isPresent()) {
          time = Optional.of((result.get().getInt(VACANTSEAT)));
        }
        return time;
    }
  }
