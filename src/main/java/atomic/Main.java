package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    Service service = new Service(0, new AtomicInteger(0), 0, 0, 0);
    int i = 0;
    while (i++ < 100000) {
      executor.submit(new Worker(service));
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
    }
    // eg: 965122, 1000000, 1000000, 999888, 999173
    System.out.println(service.toString());
  }
}
