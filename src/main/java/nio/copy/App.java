package nio.copy;

public class App {
  public static void main(String[] args) {
    String src = "E:\\space.zip";
    String dist = "E:\\space1.zip";
    CopyTool tool = new CopyTool(src, dist);
    tool.copy();
  }
}
