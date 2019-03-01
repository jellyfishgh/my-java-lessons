package bio;

import java.net.Socket;
import java.util.Date;

public class Client {

  public static void main(String[] args) {
    Client client = new Client();
    client.start();
  }

  public void start() {
    Socket socket = null;
    try {
      socket = new Socket(Config.HOST, Config.PORT);
      while (true) {
        socket.getOutputStream().write((new Date() + ": hello server").getBytes());
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
