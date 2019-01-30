package nio.path;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;

public class Walker {
  private String dir;

  public Walker(String dir) {
    this.dir = dir;
  }

  public void visit() {
    try {
      Path file = Paths.get(dir);
      LinkedList<Path> result = new LinkedList<Path>();
      Files.walkFileTree(file, new MyVisitor(result));
      Iterator<Path> it = result.iterator();
      while (it.hasNext()) {
        System.out.println(it.next());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
