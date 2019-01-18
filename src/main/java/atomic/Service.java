package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Service {
  private int x;
  private AtomicInteger y;
  private int z;
  private volatile int m;
  private volatile long n;

  public Service(int x, AtomicInteger y, int z, int m, long n) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.m = m;
    this.n = n;
  }

  public void increment() {
    x++;
    y.incrementAndGet();
    incrementZ();
    m++;
    n++;
  }

  public synchronized void incrementZ() {
    z++;
  }

  public String toString() {
    return x + ", " + y + ", " + z + ", " + m + ", " + n;
  }
}
