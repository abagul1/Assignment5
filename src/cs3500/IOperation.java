package cs3500;

/**
 * Represents an operation that the animation will be handling at some point.
 * The concept of an Operation was developed after we ran into difficulties with deciding how an
 * animation would provide smooth translation of element states without locking down control to
 * a single element.
 * When the model is given a high-level command, such as moving an element from
 * position X to position Y over the course of T number of ticks, it must perform this action
 * gradually, unless of course the number of ticks T is equal to zero. During this gradual
 * transition of state, other elements must be allowed to transition to other states as well.
 * An operation represents a shift of state of an element from one frame to the next frame. When
 * a high-level command is given, the model breaks this command down to a number of smaller
 * operations. When the current tick of the model coincides with the activation tick of the
 * operation, then the operation 'fires', performing its incremental task before being removed from
 * the model state.
 */
public interface IOperation {

  /**
   * Gets the tick that the model is supposed to execute this operation at.
   * @return  the assigned tick.
   */
  int getTickToFireAt();

  /**
   * Perform the intended function of the operation.
   * This is supposed to occur only once; when the tick associated with the operation is equal to
   * the current tick of the model.
   */
  void fire();

  /**
   * Return the element ID relating to the operation.
   */
  String getElementId();
}
