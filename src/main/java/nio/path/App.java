package nio.path;

public class App {
  public static void main(String[] args) {
    Walker walker = new Walker("src/main/java");
    walker.visit();
  }
}
