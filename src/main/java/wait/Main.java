package wait;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    try {
      Object lock = new Object();
      ArrayList<Integer> list = new ArrayList<Integer>();
      ExecutorService executor = Executors.newCachedThreadPool();
      executor.submit(new WorkerA(lock, list));
      Thread.sleep(100);
      executor.submit(new WorkerB(lock, list));
      executor.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
