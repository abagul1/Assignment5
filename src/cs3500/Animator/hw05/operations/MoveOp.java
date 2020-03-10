package cs3500.Animator.hw05.operations;

import cs3500.Animator.hw05.AbstractOp;
import cs3500.Animator.hw05.IElement;
import cs3500.Animator.hw05.Posn;

/**
 * Extends {@code AbstractOp} to handle the incremental movement of an element.
 */
public class MoveOp extends AbstractOp {

  private IElement element;
  private double dx;
  private double dy;

  /**
   * Movement Operation constructor.
   * @param e     the element to move.
   * @param dx    the change in the x-coordinate of the element.
   * @param dy    the change in the y-coordinate of the element.
   * @param tick  the tick at which the move should be executed.
   */
  public MoveOp(IElement e, double dx, double dy, int tick) {
    super(tick);
    this.element = e;
    this.dx = dx;
    this.dy = dy;
  }

  @Override
  public void fire() {
    double currentX = element.getPosn().getX();
    double currentY = element.getPosn().getY();
    element.setPosn(new Posn(currentX + dx, currentY + dy));
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
