package cs3500.Animator.hw05;

import java.awt.*;

public class Rectangle extends Shape {
  private int height;
  private int width;

  public Rectangle(int tick, int x, int y, int w, int h, Color c) {
    super(tick, x, y, c);
    this.width = w;
    this.height = h;
  }
}
