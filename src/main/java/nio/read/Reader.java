package nio.read;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public abstract class Reader {
  protected RandomAccessFile file;
  protected FileChannel channel;
  protected int bufSize;

  public Reader() {
  }

  public Reader(FileChannel channel, int bufSize) {
    this.channel = channel;
    this.bufSize = bufSize;
  }

  public Reader(RandomAccessFile file, FileChannel channel, int bufSize) {
    this.file = file;
    this.channel = channel;
    this.bufSize = bufSize;
  }

  public void read() {
  }
}
