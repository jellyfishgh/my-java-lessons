package nio.tcp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
  private String addr;
  private int port;

  public Client(String addr, int port) {
    this.addr = addr;
    this.port = port;
  }

  public static void main(String[] args) {
    Client client = new Client("127.0.0.1", 3000);
    client.start();
  }

  public void start() {
    try {
      SocketChannel client = SocketChannel.open();
      InetSocketAddress remote = new InetSocketAddress(this.addr, this.port);
      client.connect(remote);
      ByteBuffer wBuf = ByteBuffer.allocate(128);
      String clientMsg = "Hello Sever";
      wBuf.put(clientMsg.getBytes());
      wBuf.flip();
      client.write(wBuf);
      ByteBuffer rBuf = ByteBuffer.allocate(128);
      client.read(rBuf);
      rBuf.flip();
      StringBuilder builder = new StringBuilder();
      while (rBuf.hasRemaining()) {
        builder.append((char) rBuf.get());
      }
      String serverMsg = builder.toString();
      System.out.println("Accept From Server: " + serverMsg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
