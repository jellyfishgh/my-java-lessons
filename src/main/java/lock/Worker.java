package lock;

public class Worker implements Runnable {
  private Service service;
  private int id;

  public Worker(Service service, int id) {
    this.service = service;
    this.id = id;
  }

  public String toString() {
    return "thread@" + id;
  }

  @Override
  public void run() {
    service.service();
  }
}
