package atomic;

public class Worker implements Runnable {
  private Service servie;

  public Worker(Service service) {
    this.servie = service;
  }

  @Override
  public void run() {
    servie.increment();
  }

}
