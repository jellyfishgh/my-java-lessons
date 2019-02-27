package nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Processor {
  private static final ExecutorService service = Executors.newFixedThreadPool(16);

  public void process(final SelectionKey selectionKey) {
    Runnable task = new Runnable() {
      @Override
      public void run() {
        ByteBuffer buffer = null;
        SocketChannel channel = null;
        try {
          buffer = ByteBuffer.allocate(1024);
          channel = (SocketChannel) selectionKey.channel();
          int count = channel.read(buffer);
          if (count < 0) {
            channel.close();
            selectionKey.cancel();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.out.print(channel);
        System.out.print(" Read Message ");
        System.out.println(new String(buffer.array()));
      }
    };
    service.submit(task);
  }
}
