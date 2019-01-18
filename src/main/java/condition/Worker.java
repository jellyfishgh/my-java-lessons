package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable {
  private ReentrantLock lock;
  private Condition condition;
  private int i;

  public Worker(ReentrantLock lock, int i) {
    this.lock = lock;
    this.condition = lock.newCondition();
    this.i = i;
  }

  @Override
  public void run() {
    lock.lock();
    try {
      System.out.println(getLabel() + " wait " + System.currentTimeMillis());
      condition.await();
      System.out.println(getLabel() + " resume by signal");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void signal() {
    lock.lock();
    try {
      System.out.println(getLabel() + " signal " + System.currentTimeMillis());
      condition.signal();
      System.out.println(getLabel() + " after signal");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public String getLabel() {
    return Thread.currentThread().getName();
  }

  public String toString() {
    return "Worker" + i;
  }
}
