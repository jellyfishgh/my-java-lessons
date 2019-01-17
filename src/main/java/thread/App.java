package thread;

import java.io.IOException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    try {
      System.out.println("Please input your content:");
      Scanner scan = new Scanner(System.in);
      while (true) {
        String input = scan.nextLine();
        if ("exit".equals(input)) {
          System.out.println("bye");
          scan.close();
          return;
        }
        Writer writer = new Writer(input);
        Thread wt = new Thread(writer);
        Reader reader = new Reader();
        Thread rt = new Thread(reader);
        reader.getPr().connect(writer.getPw());
        wt.start();
        Thread.sleep(100);
        rt.start();
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
