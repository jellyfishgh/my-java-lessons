package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    Service service = new Service();
    ExecutorService executor = Executors.newCachedThreadPool();
    int i = 0;
    while (i++ < 3) {
      executor.submit(new Worker(service, i));
    }
    executor.shutdown();
  }
}
