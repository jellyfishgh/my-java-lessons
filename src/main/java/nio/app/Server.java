package nio.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
  private ByteBuffer sendBuffer = Config.createByteBuffer();
  private ByteBuffer receiveBuffer = Config.createByteBuffer();
  private int msgIndex = 0;
  private Selector selector;
  private ServerSocketChannel serverSocketChannel;

  public static void main(String[] args) {
    Server server = new Server();
    server.start();
  }

  public void start() {
    try {
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      ServerSocket serverSocket = serverSocketChannel.socket();
      serverSocket.bind(Config.addr);
      selector = Selector.open();
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("server is running at " + Config.addr.toString());
      this.listen();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void listen() throws IOException {
    while (true) {
      selector.select();
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        SelectionKey selectionKey = iterator.next();
        iterator.remove();
        this.handleKey(selectionKey);
      }
    }
  }

  public void handleKey(SelectionKey selectionKey) throws IOException {
    ServerSocketChannel server = null;
    SocketChannel client = null;
    String receiveText;
    String sendText;
    int count = 0;
    if (selectionKey.isAcceptable()) {
      server = (ServerSocketChannel) selectionKey.channel();
      client = server.accept();
      client.configureBlocking(false);
      client.register(selector, SelectionKey.OP_READ);
    } else if (selectionKey.isReadable()) {
      client = (SocketChannel) selectionKey.channel();
      receiveBuffer.clear();
      count = client.read(receiveBuffer);
      if (count > 0) {
        receiveText = new String(receiveBuffer.array(), 0, count);
        System.out.println("Receive data from client: " + receiveText);
        client.register(selector, SelectionKey.OP_WRITE);
      }
    } else if (selectionKey.isWritable()) {
      sendBuffer.clear();
      client = (SocketChannel) selectionKey.channel();
      sendText = "hello client " + msgIndex++;
      sendBuffer.put(sendText.getBytes());
      sendBuffer.flip();
      client.write(sendBuffer);
      System.out.println("Send data from server: " + sendText);
      client.register(selector, SelectionKey.OP_READ);
    }
  }
}
