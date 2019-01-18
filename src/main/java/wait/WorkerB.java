package wait;

import java.util.ArrayList;

public class WorkerB implements Runnable {
  private Object lock;
  private ArrayList<Integer> list;

  public WorkerB(Object lock, ArrayList<Integer> list) {
    this.lock = lock;
    this.list = list;
  }

  @Override
  public void run() {
    try {
      synchronized (lock) {
        for (int i = 0; i < 10; i++) {
          list.add(i);
          if (list.size() == 5) {
            lock.notify();
            System.out.println("B notify");
//            lock.wait();
          }
          System.out.println("size: " + list.size() + ", 添加了(" + (i + 1) + " )个元素");
          Thread.sleep(1000);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
