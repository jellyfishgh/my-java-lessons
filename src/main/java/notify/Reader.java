package notify;

public class Reader implements Runnable {
  private String name = "Reader";
  private ReadService rs;
  private Object lock;

  public Reader(ReadService rs, Object lock) {
    this.rs = rs;
    this.lock = lock;
  }

  @Override
  public void run() {
    try {
      synchronized (lock) {
        while (rs.getIsLive()) {
          System.out.println("等待数据...");
          lock.wait();
          System.out.println("reader resume");
          rs.read(name);
          lock.notify();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
