package cs3500;

import java.awt.Color;

/**
 * Interface for an Animation model within an M-V-C framework.
 * An animation consists of a collection of visual elements, where at least one element has its
 * properties translated over the running time of the animation. If no elements have their
 * properties translated over the running time of the animation, then the animation may be reduced
 * to a single frame. Alternatively, an animation may be viewed as a list of temporally-linked
 * frames. Each tick,the model calculates and redraws the necessary incremental translation for each
 * element. In this manner, elements can appear to instantly shift if the number of ticks specified
 * for the translation is zero, or they will gradually change over the specified number of ticks.
 * The model may be asked to return a frame given a number of ticks, and the model will perform
 * incremental translations of elements up to that point.
 */
public interface IAnimation {

  // Element commands

  /**
   * Converts a move operation into individual moves per tick,
   * allows the animation to increment the position in the move as a factor
   * of the number of ticks the move lasts for. Also converts the move into
   * a verbose text output.
   * @param id is the ID of the element to be
   * @param x is the destination x coordinate
   * @param y is the destination y coordinate
   * @param startTick start point of the move
   * @param endTick end point of the move
   */
  void move(String id, double x, double y, int startTick, int endTick);

  /**
   * Converts a rotate operation into individual rotations per tick,
   * allows animation to increment angle as a factor of the number of ticks left.
   * Also converts the move into a verbose text output.
   * @param id is the ID of the element to be rotated
   * @param angle is the end angle to be rotated
   * @param startTick start point of the rotation
   * @param endTick end point of the rotation
   */
  void rotate(String id, double angle, int startTick, int endTick);

  /**
   * Converts scale operation into scale per tick. Also creates a verbose text output
   * of the scale move.
   * @param id is the ID of the element to scale
   * @param scaleFactor factor to scale the element by
   * @param startTick start point of the scale
   * @param endTick end point of the scale
   */
  void scale(String id, double scaleFactor, int startTick, int endTick);

  /**
   * Converts rgb values of element to desired rgb values. Also creates a verbose
   * text output of the color change.
   * @param id is the ID of the element to change color of
   * @param color is the color to change to
   * @param startTick start point of the color change
   * @param endTick endpoint of the color change
   */
  void changeColor(String id, Color color, int startTick, int endTick);

  /**
   * Changes the visibility of element to the desired visibility through the
   * passed alpha value. Also creates a verbose text output of the visibility change
   * @param id is the ID of the element to change color of
   * @param alpha is the visibility to change to
   * @param startTick starting point of the visibility change
   * @param endTick endpoint of the visibility change
   */
  void changeVisibility(String id, int alpha, int startTick, int endTick);

  /**
   * Inserts an element into the animation at a certain tick.
   * @param element element to be inserted
   * @param tick tick to insert element
   */
  void insertElement(IElement element, int tick);

  /**
   * Deletes an element at a certain tick.
   * @param id id of element to be deleted
   * @param tick tick at which to delete element
   */
  void deleteElement(String id, int tick);

  /**
   * Converts all the moves into textual descriptions of the entire
   * animation. The motions are grouped by element.
   * @return a textual description of the animation
   */
  String getVerboseAnimation();

  /**
   * Executes the animations by running all the operations at the given ticks.
   */
  void executeOperations();

  /**
   * Executes the animation up till a certain tick.
   * @param tick tick at which to stop executing
   */
  void executeOperationsUntil(int tick);

  /**
   * Gets an element with the given id.
   * @param id id of element to return
   * @return an element from the animation
   */
  IElement getElement(String id);
}
