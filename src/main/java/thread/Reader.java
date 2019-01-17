package thread;

import java.io.PipedReader;

import notify.ReadService;

public class Reader implements Runnable {
  private String name = "Reader";
  private PipedReader pr = new PipedReader();

  @Override
  public void run() {
    ReadService rs = new ReadService(pr);
    rs.read(name);
  }

  public PipedReader getPr() {
    return this.pr;
  }
}
