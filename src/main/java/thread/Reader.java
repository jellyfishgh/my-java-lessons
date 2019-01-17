package thread;

import java.io.IOException;
import java.io.PipedReader;

public class Reader implements Runnable {
  private String name = "Reader";
  private PipedReader pr = new PipedReader();

  @Override
  public void run() {
    try {
      StringBuffer buf = new StringBuffer();
      char[] charArr = new char[5];
      int readLenth = pr.read(charArr);
      while (readLenth != -1) {
        buf.append(new String(charArr, 0, readLenth));
        readLenth = pr.read(charArr);
      }
      System.out.println(this.name + " read <" + buf.toString() + ">");
      pr.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public PipedReader getPr() {
    return this.pr;
  }
}
