package sample;

import com.scalar.db.api.DistributedTransactionManager;

public class TrainData extends PathData {
    // private int nextId = 1;
    public TrainData(DistributedTransactionManager manager) {
      super(manager);
      super.setTableName("trains");
    }
  }