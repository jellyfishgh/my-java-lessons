package nio.charset;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scan;
    while (true) {
      System.out.println("Please Enter:");
      scan = new Scanner(System.in);
      String str = scan.next();
      if ("exit".equals(str)) {
        break;
      } else {
        String[] charsetNames = { "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16" };
        for (int i = 0; i < charsetNames.length; i++) {
          Encoder encoder = new Encoder(str, charsetNames[i]);
          encoder.encode();
        }
      }
    }
    scan.close();
    System.out.println("exit");
  }
}
