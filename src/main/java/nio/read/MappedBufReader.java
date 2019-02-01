package nio.read;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedBufReader extends Reader {
  public MappedBufReader(RandomAccessFile file, FileChannel channel, int bufSize) {
    super(file, channel, bufSize);
  }

  public void read() {
    try {
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
      byte[] b = new byte[bufSize];
      long len = file.length();
      long start = System.currentTimeMillis();
      for (int i = 0; i < len; i += bufSize * 10) {
        if (len - i > 1024) {
          buffer.get(b);
        } else {
          buffer.get(new byte[(int) (len - i)]);
        }
      }
      System.out.println("MappedBufReader cost time: " + (System.currentTimeMillis() - start));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
