package cs3500.elements;

import java.awt.Color;
import java.util.Objects;

import cs3500.AbstractElement;
import cs3500.Posn;

/**
 * Element to represent a rectangle.
 */
public class Rectangle extends AbstractElement {
  private double height;
  private double width;

  /**
   * Constructor for a rectangle.
   * @param id element id
   * @param c color
   * @param p posn
   * @param angle angle
   * @param height height
   * @param width width
   */
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
    return (((Rectangle) that).height == this.height && ((Rectangle) that).width == this.width
            && ((Rectangle) that).getPosn().getX() == this.getPosn().getY()
            && ((Rectangle) that).getPosn().getY() == this.getPosn().getY()
            && ((Rectangle) that).color == this.color
            && ((Rectangle) that).angle == this.angle);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.height, this.width, this.angle, this.color,
            this.getPosn().getX(), this.getPosn().getY());
  }
}
