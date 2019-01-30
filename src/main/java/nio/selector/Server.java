package nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
  private String addr;
  private int port;

  public Server(String addr, int port) {
    this.addr = addr;
    this.port = port;
  }

  public static void main(String[] args) {
    Server server = new Server("localhost", 3001);
    server.init();
  }

  public void init() {
    try {
      ServerSocketChannel server = ServerSocketChannel.open();
      InetSocketAddress address = new InetSocketAddress(addr, port);
      server.socket().bind(address);
      server.configureBlocking(false);
      Selector selector = Selector.open();
      server.register(selector, SelectionKey.OP_ACCEPT);
      ByteBuffer readBuf = ByteBuffer.allocate(1024);
      ByteBuffer writeBuf = ByteBuffer.allocate(1024);
      writeBuf.put("received".getBytes());
      writeBuf.flip();
      while (true) {
        int nReady = selector.select();
        System.out.println("nReady: " + nReady);
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> it = keys.iterator();
        while (it.hasNext()) {
          SelectionKey key = it.next();
          it.remove();
          if (key.isAcceptable()) {
            SocketChannel readChannel = server.accept();
            readChannel.configureBlocking(false);
            readChannel.register(selector, SelectionKey.OP_READ);
          } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            readBuf.clear();
            channel.read(readBuf);
            readBuf.flip();
            System.out.println("received: " + new String(readBuf.array()));
            key.interestOps(SelectionKey.OP_WRITE);
          } else if (key.isWritable()) {
            writeBuf.rewind();
            SocketChannel writeChannel = (SocketChannel) key.channel();
            writeChannel.write(writeBuf);
            key.interestOps(SelectionKey.OP_READ);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
