package wait;

import java.util.ArrayList;

public class WorkerA implements Runnable {
  private Object lock;
  private ArrayList<Integer> list;

  public WorkerA(Object lock, ArrayList<Integer> list) {
    this.lock = lock;
    this.list = list;
  }

  @Override
  public void run() {
    synchronized (lock) {
      try {
        if (list.size() != 5) {
          System.out.println("wait begin " + System.currentTimeMillis());
          lock.wait();
          System.out.println("A " + list.size());
          System.out.println("wait end " + System.currentTimeMillis());
          lock.notify();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
