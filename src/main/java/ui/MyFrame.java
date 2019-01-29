package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Panel;

public class MyFrame {
  private int width;
  private int height;

  public MyFrame(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void init() {
    Frame frame = new Frame();
    frame.setTitle("Hello");
    frame.setSize(new Dimension(width, height));
    Panel pane = new Panel();
    pane.setSize(width, height);
    frame.add(pane);
    pane.setBackground(Color.RED);
    frame.setVisible(true);
  }

  public void start() {
    int i = 0;
    while (true) {
      i++;
      if (i > 5)
        break;
    }
    System.out.println(i);
  }
}
