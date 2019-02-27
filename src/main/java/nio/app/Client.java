package nio.app;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
  private ByteBuffer sendBuffer = Config.createByteBuffer();
  private ByteBuffer receiveBuffer = Config.createByteBuffer();
  private int msgIndex = 0;
  private Selector selector;

  public static void main(String[] args) {
    Client client = new Client();
    client.start();
  }

  public void start() {
    try {
      SocketChannel socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
      selector = Selector.open();
      socketChannel.register(selector, SelectionKey.OP_CONNECT);
      socketChannel.connect(Config.addr);
      while (true) {
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          this.handleKey(iterator.next());
        }
        selectionKeys.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void handleKey(SelectionKey selectionKey) throws IOException {
    SocketChannel client;
    String reveiveText;
    String sendText;
    int count = 0;
    if (selectionKey.isConnectable()) {
      System.out.println("client is connecting...");
      client = (SocketChannel) selectionKey.channel();
      if (client.isConnectionPending()) {
        client.finishConnect();
        System.out.println("connected");
        sendBuffer.clear();
        sendBuffer.put("Hello Server".getBytes());
        sendBuffer.flip();
        client.write(sendBuffer);
      }
      client.register(selector, SelectionKey.OP_READ);
    } else if (selectionKey.isReadable()) {
      client = (SocketChannel) selectionKey.channel();
      receiveBuffer.clear();
      count = client.read(receiveBuffer);
      if (count > 0) {
        reveiveText = new String(receiveBuffer.array(), 0, count);
        System.out.println("Data from Server: " + reveiveText);
        client.register(selector, SelectionKey.OP_WRITE);
      }
    } else if (selectionKey.isWritable()) {
      sendBuffer.clear();
      client = (SocketChannel) selectionKey.channel();
      sendText = "data from client " + msgIndex++;
      sendBuffer.put(sendText.getBytes());
      sendBuffer.flip();
      client.write(sendBuffer);
      client.register(selector, SelectionKey.OP_READ);
    }
  }
}
