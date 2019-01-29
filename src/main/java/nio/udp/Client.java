package nio.udp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

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
      DatagramChannel client = DatagramChannel.open();
      InetSocketAddress remote = new InetSocketAddress(this.addr, this.port);
      ByteBuffer wBuf = ByteBuffer.allocate(128);
      String clientMsg = "Hello World";
      wBuf.put(clientMsg.getBytes());
      wBuf.flip();
      client.send(wBuf, remote);
      client.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
