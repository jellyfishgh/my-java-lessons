package thread;

import java.io.IOException;
import java.io.PipedWriter;

public class Writer implements Runnable {
  private String name = "Writer";
  private PipedWriter pw = new PipedWriter();
  private String input;

  public Writer(String input) {
    this.input = input;
  }

  @Override
  public void run() {
    try {
      System.out.println(name + " write <" + input + ">");
      pw.write(input);
      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PipedWriter getPw() {
    return this.pw;
  }
}