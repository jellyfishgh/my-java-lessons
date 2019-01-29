package nio.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
  private int port;

  public Server(int port) {
    this.port = port;
  }

  public static void main(String[] args) {
    Server server = new Server(3000);
    server.start();
  }

  public void start() {
    try {
      DatagramChannel server = DatagramChannel.open();
      InetSocketAddress addr = new InetSocketAddress("127.0.0.1", this.port);
      server.socket().bind(addr);
      ByteBuffer rBuf = ByteBuffer.allocate(128);
      System.out.println("Wait client to send data");
      server.receive(rBuf);
      StringBuilder builder = new StringBuilder();
      rBuf.flip();
      while (rBuf.hasRemaining()) {
        builder.append((char) rBuf.get());
      }
      String clientMsg = builder.toString();
      System.out.println("Accept From Client: " + clientMsg);
      server.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
