package cs3500.Animator.hw05;

import java.awt.*;

public class Ellipse extends Shape {
  private int height;
  private int width;

  public Ellipse(int tick, int x, int y, int h, int w, Color c) {
    super(tick, x, y, c);
    this.height = h;
    this.width = w;
  }
}
