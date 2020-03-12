package cs3500;

import java.awt.Color;
import java.util.Objects;

import cs3500.elements.Ellipse;

/**
 * Abstract class that implements the {@code IElement} interface.
 *<p>
 * In considering what the user of our Animation model might wish to animate, we decided that all
 * elements must be able to perform the following operations:
 * Move, Rotate, Scale, Change Color, Change Visibility
 *</p>
 *
 * <p>
 * From the methods inherited from {@code IElement}, functionality for movement, color change, and
 * visibility change are able to be implemented here. These methods will be the same for all
 * elements, regardless of future type. Note that changing the visibility of an element simply
 * comes down to using the setColor(..) method, and keeping the R, G, and B values the same while
 * altering the alpha value.
 *</p>
 *
 * <p>
 * It would not make sense to implement functionality for rotation or scaling here, as functionality
 * will differ considerably depending on the type of element. For example, a circle would simply
 * return null on a rotation, as it is the exact same no matter the angle of rotation. This would
 * not be the case for a pointed shape, however.
 * </p>
 */
public abstract class AbstractElement implements IElement {

  protected Color color;
  protected Posn center;
  protected String id;
  protected double angle;

  /**
   * Constructor for abstract element.
   * @param c color of element
   * @param p position of element
   * @param id id of element
   * @param angle angle of element
   */
  public AbstractElement(Color c, Posn p, String id, double angle) {
    this.color = c;
    this.center = new Posn(p);
    this.id = id;
    this.angle = angle;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Posn getPosn() {
    return center;
  }

  @Override
  public String getID() {
    return id;
  }

  @Override
  public void setColor(Color c) {
    this.color = c;
  }

  @Override
  public void setPosn(Posn p) {
    this.center = new Posn(p);
  }

  @Override
  public double getAngle() {
    return angle % 360;
  }

  @Override
  public void rotate(double da) {
    this.angle += da;
  }

  @Override
  public abstract void scale(double ds);

  @Override
  public abstract double[] getDimensions();
}
