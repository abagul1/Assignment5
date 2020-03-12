package cs3500.elements;

import java.awt.Color;
import java.util.Objects;

import cs3500.AbstractElement;
import cs3500.Posn;

/**
 * Element to represent an ellipse.
 */
public class Ellipse extends AbstractElement {
  private double height;
  private double width;

  /**
   * Constructor for a ellipse.
   * @param id element id
   * @param c color
   * @param p posn
   * @param angle angle
   * @param height height
   * @param width width
   */
  public Ellipse(String id, Color c, Posn p, double angle, double height, double width) {
    super(c, p, id, angle);
    this.height = height;
    this.width = width;
  }

  /**
   * Gets the height (major axis) of an ellipse.
   * @return height
   */
  private double getHeight() {
    return this.height;
  }

  /**
   * Gets the width (minor axis) of an ellipse.
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
    double[] dim = {getHeight(), getWidth()};
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
    if (!(that instanceof Ellipse)) {
      return false;
    }
    return (this.getID().equals(((Ellipse) that).getID()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getID());
  }
}
