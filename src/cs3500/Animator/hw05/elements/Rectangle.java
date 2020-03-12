package cs3500.Animator.hw05.elements;

import java.awt.*;
import java.util.Objects;

import cs3500.Animator.hw05.AbstractElement;
import cs3500.Animator.hw05.Posn;

public class Rectangle extends AbstractElement {
  private double height;
  private double width;

  public Rectangle(String id, Color c, Posn p, double angle, double height, double width) {
    super(c, p, id, angle);
    this.height = height;
    this.width = width;
  }

  @Override
  public void scale(double ds) {
    //Implement Scale with Swing
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Rectangle)) {
      return false;
    }
    if (((Rectangle) that).height == this.height && ((Rectangle) that).width == this.width
            && ((Rectangle) that).getPosn().getX() == this.getPosn().getY()
            && ((Rectangle) that).getPosn().getY() == this.getPosn().getY()
            && ((Rectangle) that).color == this.color
            && ((Rectangle) that).angle == this.angle) {
      return true;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.width, this.angle, this.color,
            this.getPosn().getX(), this.getPosn().getY());
  }
}
