package nio.filelock;

public class App {
  public static void main(String[] args) {
    MyFileLock mfl = new MyFileLock();
    mfl.start();
  }
}
