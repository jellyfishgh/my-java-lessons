package nio.path;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.List;
import java.nio.file.attribute.BasicFileAttributes;

public class MyVisitor extends SimpleFileVisitor<Path> {
  private List<Path> result;

  public MyVisitor(List<Path> result) {
    this.result = result;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    result.add(file.toAbsolutePath());
    return FileVisitResult.CONTINUE;
  }
}
