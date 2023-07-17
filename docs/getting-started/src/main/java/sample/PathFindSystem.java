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

public class PathFindSystem {
  // PathFindSystem Class 
  private PathData trainData;
  private PathData busData;
  private PathData planeData;
  private final DistributedTransactionManager manager;
  public PathFindSystem(String scalarDBProperties) throws IOException {
    TransactionFactory factory = TransactionFactory.create(scalarDBProperties);
    this.manager = factory.getTransactionManager();
    this.trainData = new TrainData(this.manager);
    this.busData = new BusData(this.manager);
    this.planeData = new PlaneData(this.manager);
  }

  //DFS
  public ArrayList<ArrayList<MyPath>> getRoots(String src, String dst) throws TransactionException{
    // return [[path, path, ..., path], root, root]
    ArrayList<ArrayList<MyPath>> roots = new ArrayList<ArrayList<MyPath>>();
    ArrayList<MyPath> root = new ArrayList<MyPath>();
    searchForPath(src, dst, roots, root);
    return roots;
  }

  private void searchForPath(String src, String dst, ArrayList<ArrayList<MyPath>> roots, ArrayList<MyPath> root) throws TransactionException{
    if (src.equals(dst)){
      roots.add(root);
    } else {
      ArrayList<MyPath> currentRoot;
      ArrayList<MyPath> trainPaths = this.trainData.getFromSrc(src, 0);
      ArrayList<MyPath> busPaths = this.busData.getFromSrc(src, 1);
      ArrayList<MyPath> planePaths = this.planeData.getFromSrc(src, 2);
      if (trainPaths.size() != 0) {
        for (MyPath path : trainPaths) {
          currentRoot = new ArrayList<MyPath>(root);
          currentRoot.add(path);
          searchForPath(path.getDst(), dst, roots, currentRoot);
        }
      }
      if (busPaths.size() != 0) {
        for (MyPath path : busPaths) {
          currentRoot = new ArrayList<MyPath>(root);
          currentRoot.add(path);
          searchForPath(path.getDst(), dst, roots, currentRoot);
        }
      }
      if (planePaths.size() != 0) {
        for (MyPath path : planePaths) {
          currentRoot = new ArrayList<MyPath>(root);
          currentRoot.add(path);
          searchForPath(path.getDst(), dst, roots, currentRoot);
        }
      }
    }
  }
}
