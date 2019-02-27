package nio.app;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class Config {
  private static int port = 1234;
  private static String host = "localhost";
  private static int block = 1024 * 4;

  static InetSocketAddress addr = new InetSocketAddress(host, port);

  static ByteBuffer createByteBuffer() {
    return ByteBuffer.allocate(block);
  }
}
