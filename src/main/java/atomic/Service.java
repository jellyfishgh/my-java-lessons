package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Service {
  private int x;
  private AtomicInteger y;

  public Service(int x, AtomicInteger y) {
    this.x = x;
    this.y = y;
  }

  public void increment() {
    x++;
    y.incrementAndGet();
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
