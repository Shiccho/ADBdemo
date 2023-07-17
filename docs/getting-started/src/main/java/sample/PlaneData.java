package sample;

import com.scalar.db.api.DistributedTransactionManager;

public class PlaneData extends PathData {
    // private int nextId = 1;
    public PlaneData(DistributedTransactionManager manager) {
        super(manager);
        super.setTableName("planes");
    }
}