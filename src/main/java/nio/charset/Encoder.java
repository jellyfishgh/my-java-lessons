package nio.charset;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Encoder {
  private String str;
  private String code;

  public Encoder(String str, String code) {
    this.str = str;
    this.code = code;
  }

  public void encode() {
    Charset cs = Charset.forName(code);
    System.out.println(cs.name() + " -> " + str + " -> Encoded:");
    ByteBuffer buf = cs.encode(str);
    for (int i = 0; buf.hasRemaining(); i++) {
      int b = buf.get();
      int ival = ((int) b) & 0xff;
      char c = (char) ival;
      if (i < 10)
        System.out.print(" ");
      System.out.print(" " + i + ": ");
      if (ival < 16)
        System.out.print("0");
      System.out.print(Integer.toHexString(ival));
      if (Character.isWhitespace(c) || Character.isISOControl(c)) {
        System.out.println("");
      } else {
        System.out.println(" (" + c + ")");
      }
    }
  }

}
