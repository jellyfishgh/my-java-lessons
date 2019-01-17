package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Service {
  private Lock lock = new ReentrantLock();

  public void service() {
    lock.lock();
    try {
      int i = 0;
      while (i++ < 5) {
        System.out.println(Thread.currentThread().getName() + " -> " + i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
