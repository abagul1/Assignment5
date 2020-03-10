package cs3500.Animator.hw05;

import java.awt.Color;

/**
 * Interface for an element.
 *
 * An element is an entity that is capable of being represented visually.
 * This means that it possesses all of the information necessary to represent itself within a frame.
 */
public interface IElement {

  /**
   * Get the color of this element.
   *
   * Imported from {@code java.awt.Color}.
   * A Color is in RGBA format, meaning it possesses a red, green, blue, and alpha component.
   *
   * @return  the element's color.
   */
  Color getColor();

  /**
   * Get the position of this element.
   *
   * See {@code Posn} for a description of a Posn.
   *
   * @return  the position of the element.
   */
  Posn getPosn();

  /**
   * Get the ID of the element.
   *
   * The ID of an element is a String that identifies the element. This allows the element to be
   * referenced from within a data structure such as a Map.
   *
   * Once an element is created, its ID cannot be changed.
   *
   * @return  the ID.
   */
  String getID();

  /**
   * Sets the color of an element to the specified color.
   *
   * As Color is in RGBA format, this can be used to alter the alpha value as well as the actual
   * color. See {@code java.awt.Color} for different color constructors, as well as various
   * enumerated colors.
   *
   * @param c  the color to change the element to.
   */
  void setColor(Color c);

  /**
   * Sets the position of an element.
   *
   * As a {@code Posn} is immutable, a new one must be constructed whenever the position of an
   * element must be changed.
   *
   * @param p  the new position of the element.
   */
  void setPosn(Posn p);

  /**
   * Rotate the element. Positive angles are CCW, negative angles are CW.
   * @param da  the angle to rotate the element by
   */
  void rotate(double da);

  /**
   * Scale the element by the given scale factor.
   * 0 < scaleFactor < inf
   * @param ds the scale factor to multiply the size of the element by
   * @throws IllegalArgumentException  if the scaleFactor is out of bounds
   */
  void scale(double ds);
}
