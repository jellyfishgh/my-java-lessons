package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();
    Service service = new Service(0, new AtomicInteger(0));
    int i = 0;
    while (i++ < 1000) {
      executor.submit(new Worker(service));
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
    }
    System.out.println(service.toString());
  }
}
