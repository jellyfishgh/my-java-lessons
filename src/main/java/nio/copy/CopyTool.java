package nio.copy;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CopyTool {
  private String src;
  private String dist;
  private int len = 0;

  public CopyTool(String src, String dist) {
    this.src = src;
    this.dist = dist;
  }

  private ByteBuffer read() {
    try {
      RandomAccessFile file = new RandomAccessFile(src, "rw");
      len = (int) file.length();
      FileChannel channel = file.getChannel();
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, len);
      channel.close();
      file.close();
      return buffer.get(new byte[len]);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void copy() {
    try {
      RandomAccessFile file = new RandomAccessFile(dist, "rw");
      FileChannel channel = file.getChannel();
      ByteBuffer buffer = this.read();
      MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_WRITE, 9, len);
      long startTime = System.currentTimeMillis();
      for (int i = 0; i < len; i++) {
        buf.put(i, buffer.get(i));
      }
      buf.flip();
      long endTime = System.currentTimeMillis();
      System.out.println("写文件耗时： " + (endTime - startTime));
      channel.close();
      file.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
