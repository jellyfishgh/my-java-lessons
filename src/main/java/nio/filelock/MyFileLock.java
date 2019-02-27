package nio.filelock;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class MyFileLock {
  public void start() {
    try {
      String filePath = "src/main//java/nio/filelock/test.txt";
      RandomAccessFile file = new RandomAccessFile(filePath, "rw");
      FileChannel fileChannel = file.getChannel();
      fileChannel.write(ByteBuffer.wrap("abcd".getBytes()));
      FileLock lock = fileChannel.lock(0, 2, true);
      fileChannel.write(ByteBuffer.wrap("😊😀".getBytes()), 0);
      // lock.isShared();
      lock.close();
      fileChannel.close();
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
