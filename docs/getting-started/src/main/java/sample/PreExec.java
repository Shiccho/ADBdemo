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
import java.io.File;

public class PreExec {
    public static void main(String[] args) {
        try {
            String scalarDBProperties = null;
            if (scalarDBProperties == null) scalarDBProperties = System.getProperty("user.dir") + File.separator + "scalardb.properties";
           
            DistributedTransactionManager manager;
            TransactionFactory factory = TransactionFactory.create(scalarDBProperties);
            manager = factory.getTransactionManager();
    
            TrainData trainData = new TrainData(manager);
            BusData busData = new BusData(manager);
            PlaneData planeData = new PlaneData(manager);
            planeData.add("J.F.K","Dallas", 300, 100000, 300);
            planeData.add("Dallas","Haneda",720, 200000, 300);
            trainData.add("Haneda","Shinagawa", 10, 200, 100000);
            trainData.add("Shinagawa","Shibuya", 10, 200, 100000);
            trainData.add("Shibuya","Hiyoshi", 20, 200, 1000000);
            busData.add("Shinagawa", "Shibuya", 40, 150, 100);
            busData.add("Shibuya", "Jiyugaoka", 20, 150, 100);
            busData.add("Jiyugaoka", "Hiyoshi", 10, 200, 100);

            planeData.add("Paris","Singapore", 480, 100000, 300);
            planeData.add("Singapore","Osaka", 180, 50000, 300);
            trainData.add("Osaka", "Yokohama", 120, 12500, 300);
            trainData.add("Yokohama", "Hiyoshi", 15, 200, 100000);
            trainData.add("Shinjuku", "Hiyoshi", 40, 400, 100000);
            trainData.add("Shinjuku", "Shibuya", 5, 150, 100000);
            busData.add("Osaka","Yokohama", 480, 5000, 60);
            busData.add("Osaka","Shinjuku", 480, 6000, 60);
        } catch(TransactionException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
