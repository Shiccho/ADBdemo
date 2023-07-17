package sample;

public class MyPath {
    private int id;
    private String src;
    private String dst;
    private int time;
    private int fee;
    private int trafficroot; // train or bus or plane
    public MyPath(int id, String src, String dst, int time, int fee, int trafficroot) {
      this.id = id;
      this.src = src;
      this.dst = dst;
      this.time = time;
      this.fee = fee;
      this.trafficroot = trafficroot;
    }
    int getId(){
      return this.id;
    }
    String getSrc(){
      return this.src;
    }
    String getDst(){
      return this.dst;
    }
    int getTime(){
      return this.time;
    }
    int getFee(){
      return this.fee;
    }
    int getTrafficroot(){
      return this.trafficroot;
    }
  }
