package sample;

import com.scalar.db.api.DistributedTransactionManager;

public class BusData extends PathData {
    // private int nextId = 1;
    public BusData(DistributedTransactionManager manager) {
      super(manager);
      super.setTableName("buses");
    }
  }