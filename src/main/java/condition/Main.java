package condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
  public static void main(String[] args) {
    try {
      Worker[] arr = new Worker[3];
      ReentrantLock lock = new ReentrantLock();
      ExecutorService executor = Executors.newCachedThreadPool();
      int i = 0;
      while (i++ < arr.length) {
        Worker worker = new Worker(lock, i);
        arr[i - 1] = worker;
        executor.submit(worker);
      }
      Thread.sleep(3000);
      while (--i > 0) {
        arr[i - 1].signal();
      }
      executor.shutdown();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
