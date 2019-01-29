package nio.file;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileTest {
  private String file;

  public FileTest(String file) {
    this.file = file;
  }

  public void start() {
    try {
      RandomAccessFile raf = new RandomAccessFile(this.file, "rw");
      FileChannel inChannel = raf.getChannel();
      ByteBuffer rBuf = ByteBuffer.allocate(8);
      int rBytes = inChannel.read(rBuf);
      ByteBuffer wBuf = ByteBuffer.allocate(48);
      wBuf.put("Hello Channel\n".getBytes());
      wBuf.flip();
      inChannel.write(wBuf);
      while (rBytes != -1) {
        rBuf.flip();
        while (rBuf.hasRemaining()) {
          System.out.print((char) rBuf.get());
        }
        rBuf.clear();
        rBytes = inChannel.read(rBuf);
      }
      raf.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
