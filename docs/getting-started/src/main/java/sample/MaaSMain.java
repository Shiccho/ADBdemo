package sample;
import java.io.File;

public class MaaSMain {
  public static void main(String[] args) throws Exception{
    String scalarDBProperties = null;
    MaaSWebUI ui;
    if (scalarDBProperties != null) {
      ui = new MaaSWebUI(scalarDBProperties);
    } else {
      scalarDBProperties = System.getProperty("user.dir") + File.separator + "scalardb.properties";
      ui = new MaaSWebUI(scalarDBProperties);
    }
  }
}

