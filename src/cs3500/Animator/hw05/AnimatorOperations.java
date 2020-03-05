package cs3500.Animator.hw05;

import java.awt.*;

/**
 * Interface for 2D model operations.
 */
public interface AnimatorOperations {

  /**
   * Initializes a new blank animation of the given size.
   * @param height height of the animation screen
   * @param width width of the animation screen
   */
  void createAnimation(int height, int width);

  /**
   *  Moves a shape located at x,y to a new position in a certain amount
   *  of time.
   * @param s shape that is to be moved by the animator
   * @param x x coordinate for end destination
   * @param y y coordinate for end destination
   * @param endTick end tick time
   */
  void moveXY(Shape s, int x, int y, int endTick, Color c);

  /**
   * Returns the world state and all the objects being animated in the model at the given
   * tick.
   * @param tick specific tick that the model will return
   * @return model in text format of the animation
   * @throws IllegalStateException if model has not been initialized
   */
   String getWorldState(int tick);

  /**
   * Creates a new shape at the specified location and of the specified parameters.
   * @param st shape type to be created
   * @param x initial x position of shape
   * @param y initial y position of shape
   * @param c color of shape
   * @param width width of shape
   * @param height height of shape
   */
  void createShape(ShapeType st, int x, int y, Color c, int width, int height);

  /**
   * Gets the shape located at the given coordinates on the animation board.
   * @param x x coordinate of shape
   * @param y y coordinate of shape
   * @param tick current time in the animation
   * @return shape at the x and y coordinate
   */
  Shape getShape(int x, int y, int tick);

}
