package nio.read;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileReader extends Reader {

  public FileReader(FileChannel channel, int bufSize) {
    super(channel, bufSize);
  }

  public void read() {
    try {
      ByteBuffer buffer = ByteBuffer.allocate(bufSize);
      long start = System.currentTimeMillis();
      while (channel.read(buffer) > 0) {
        buffer.flip();
        buffer.clear();
      }
      System.out.println("FileReader cost time: " + (System.currentTimeMillis() - start));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
