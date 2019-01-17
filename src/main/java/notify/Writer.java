package notify;

import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class Writer implements Runnable {
  private ReadService rs;
  private Object lock;
  private ExecutorService executor;

  public Writer(ReadService rs, Object lock, ExecutorService executor) {
    this.rs = rs;
    this.lock = lock;
    this.executor = executor;
  }

  @Override
  public void run() {
    Scanner scan = new Scanner(System.in);
    while (true) {
      String input = scan.nextLine();
      if ("exit".equals(input)) {
        executor.shutdown();
        scan.close();
        return;
      }
      synchronized (lock) {
        PipedWriter pw = new PipedWriter();
        PipedReader pr = new PipedReader();
        try {
          pr.connect(pw);
          rs.setPr(pr);
          pw.write(input);
          pw.close();
          lock.notify();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
