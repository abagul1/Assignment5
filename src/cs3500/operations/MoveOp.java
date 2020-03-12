package cs3500.operations;

import cs3500.IElement;
import cs3500.Posn;
import cs3500.AbstractOp;

/**
 * Extends {@code AbstractOp} to handle the incremental movement of an element.
 */
public class MoveOp extends AbstractOp {

  private IElement element;
  private double x;
  private double y;
  private int endTick;

  /**
   * Movement Operation constructor.
   * @param e     the element to move.
   * @param x     destination x-coordinate of the element.
   * @param y     destination y-coordinate of the element.
   * @param tick  the tick at which the move should be executed.
   * @param endTick end tick of the operation
   */
  public MoveOp(IElement e, double x, double y, int tick, int endTick) {
    super(tick);
    this.element = e;
    this.x = x;
    this.y = y;
    this.endTick = endTick;
  }

  @Override
  public void fire() {
    double currentX = element.getPosn().getX();
    double currentY = element.getPosn().getY();
    double dx = (x - currentX) / (endTick - super.tickToFireAt);
    double dy = (y - currentY) / (endTick - super.tickToFireAt);
    element.setPosn(new Posn(currentX + dx, currentY + dy));
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
