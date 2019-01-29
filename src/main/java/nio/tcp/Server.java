package nio.tcp;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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
      ServerSocketChannel server = ServerSocketChannel.open();
      InetSocketAddress addr = new InetSocketAddress("127.0.0.1", this.port);
      server.socket().bind(addr);
      SocketChannel channel = null;
      while (true) {
        System.out.println("Wait client to connet...");
        channel = server.accept();
        ByteBuffer wBuf = ByteBuffer.allocate(128);
        String serverMsg = "Welcome to my ChatRoom";
        wBuf.put(serverMsg.getBytes());
        wBuf.flip();
        channel.write(wBuf);
        ByteBuffer rBuf = ByteBuffer.allocate(128);
        channel.read(rBuf);
        StringBuilder builder = new StringBuilder();
        rBuf.flip();
        while (rBuf.hasRemaining()) {
          builder.append((char) rBuf.get());
        }
        String clientMsg = builder.toString();
        System.out.println("Accept From Client: " + clientMsg);
        if ("exit".equals(clientMsg))
          break;
      }
      channel.close();
      server.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
