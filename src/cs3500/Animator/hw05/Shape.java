package cs3500.Animator.hw05;

import java.awt.*;

/**
 * Abstract class to represent shapes that can be modelled in the animator.
 */
public abstract class Shape {
  private Color color;
  private int x;
  private int y;
  private int tick;

  public Shape(int tick, int x, int y, Color c) {
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.color = c;
  }


}
