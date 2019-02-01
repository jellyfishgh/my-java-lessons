package nio.read;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class App {
  public static void main(String[] args) {
    try {
      RandomAccessFile file = new RandomAccessFile("E:\\space.zip", "rw");
      FileChannel channel = file.getChannel();
      int bufSize = 1024;
      FileReader reader1 = new FileReader(channel, bufSize);
      reader1.read();
      MappedBufReader reader2 = new MappedBufReader(file, channel, bufSize);
      reader2.read();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
