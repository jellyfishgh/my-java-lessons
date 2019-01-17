package singleton;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    int count = 15;
    ExecutorService executor = Executors.newFixedThreadPool((int) count / 3);
    ArrayList<Singleton> list1 = new ArrayList<Singleton>();
    ArrayList<Singleton> list2 = new ArrayList<Singleton>();
    for (int i = 0; i < count; i++) {
      list2.add(Singleton.getIntance());
      Worker worker = new Worker(list1);
      executor.execute(worker);
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
    }
    list1.forEach(instance -> {
      System.out.println(instance != null ? instance.hashCode() : instance);
    });
    list2.forEach(instance -> {
      System.out.println(instance.hashCode());
    });
  }

}
