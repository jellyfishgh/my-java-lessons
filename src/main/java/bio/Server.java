package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    Server server = new Server();
    server.start();
  }

  public void start() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(Config.PORT);
      while (true) {
        Socket socket = serverSocket.accept();
        new Thread(() -> {
          int len;
          byte[] data = new byte[1024];
          InputStream inputStream;
          try {
            inputStream = (InputStream) socket.getInputStream();
            while ((len = inputStream.read(data)) != -1) {
              System.out.println(new String(data, 0, len));
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
