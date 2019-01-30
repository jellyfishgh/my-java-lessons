package nio.selector;

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
    Client client = new Client("localhost", 3001);
    client.init();
  }

  public void init() {
    try {
      SocketChannel client = SocketChannel.open();
      InetSocketAddress remote = new InetSocketAddress(addr, port);
      client.connect(remote);
      ByteBuffer wBuffer = ByteBuffer.allocate(32);
      ByteBuffer rBuffer = ByteBuffer.allocate(32);
      wBuffer.put("hello".getBytes());
      wBuffer.flip();
      while (true) {
        wBuffer.rewind();
        client.write(wBuffer);
        rBuffer.clear();
        client.read(rBuffer);
        System.out.println(new String(rBuffer.array()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
