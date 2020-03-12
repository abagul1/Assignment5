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

  /**
   * Gets the height of an rectangle.
   * @return height
   */
  private double getHeight() {
    return this.height;
  }

  /**
   * Gets the width of an rectangle.
   * @return width
   */
  private double getWidth() {
    return this.width;
  }

  /**
   * Returns an array of doubles of length 2, in the following order: Height, Width.
   * @return  the array of dimensions.
   */
  @Override
  public double[] getDimensions() {
    double dim[] = {getHeight(), getWidth()};
    return dim;
  }

  @Override
  public void scale(double ds) {
    this.height = height * ds;
    this.width = width * ds;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Rectangle)) {
      return false;
    }
    return (this.getID().equals(((Rectangle) that).getID()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getID());
  }
}
