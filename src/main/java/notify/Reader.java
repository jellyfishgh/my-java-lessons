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
    synchronized (lock) {
      while (true) {
        try {
          System.out.println("等待数据...");
          lock.wait();
          rs.read(name);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

}
