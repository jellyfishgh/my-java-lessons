package nio.reactor;

public class App {
  public static void main(String[] args) {
    NioServer server = new NioServer(1234);
    server.start();
  }
}
