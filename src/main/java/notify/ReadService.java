package notify;

import java.io.PipedReader;
import java.io.IOException;

public class ReadService {
  private PipedReader pr;
  private boolean isLive = true;

  public ReadService() {
  }

  public ReadService(PipedReader pr) {
    this.setPr(pr);
  }

  public void setPr(PipedReader pr) {
    this.pr = pr;
  }

  public void read(String master) {
    if(!isLive) return;
    try {
      StringBuffer buf = new StringBuffer();
      char[] charArr = new char[5];
      int readLenth = pr.read(charArr);
      while (readLenth != -1) {
        buf.append(new String(charArr, 0, readLenth));
        readLenth = pr.read(charArr);
      }
      System.out.println(master + " read <" + buf.toString() + ">");
      pr.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public boolean getIsLive() {
    return isLive;
  }
  
  public void close() {
    isLive = false;
  }
}
