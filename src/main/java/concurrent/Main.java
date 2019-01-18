package concurrent;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    ConcurrentSkipListMap<String, Integer> map = new ConcurrentSkipListMap<String, Integer>();
    ExecutorService executor = Executors.newCachedThreadPool();
    int i = 0;
    while (i++ < 100) {
      executor.submit(new Worker(map, "" + i % 10, i, i % 2 == 0));
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
    }
    System.out.println(map.values());
  }
}
