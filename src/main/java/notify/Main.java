package notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    try {
      ExecutorService executor = Executors.newCachedThreadPool();
      ReadService rs = new ReadService();
      Object lock = new Object();
      executor.submit(new Writer(rs, lock, executor));
      Thread.sleep(100);
      executor.submit(new Reader(rs, lock));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
