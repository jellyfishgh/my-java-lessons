package threadpool;

import java.util.Date;

public class Worker implements Runnable {
  private String name;

  public Worker(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " start time = " + new Date());
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " end time = " + new Date());
  }

  public String toString() {
    return "worker " + name;
  }
}
