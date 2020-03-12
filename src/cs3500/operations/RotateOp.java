package cs3500.operations;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the incremental rotation of an element.
 */
public class RotateOp extends AbstractOp {

  private IElement element;
  private double angle;
  private int endTick;
  private  int startTick;

  /**
   * Constructor for a rotation.
   * @param e element to be rotated
   * @param angle amount for the angle to be rotated
   * @param tick tick to start rotation
   * @param endTick ending point for rotation
   */
  public RotateOp(IElement e, double angle, int tick, int startTick, int endTick) {
    super(tick);
    this.element = e;
    this.angle = angle;
    this.endTick = endTick;
    this.startTick = startTick;
  }

  @Override
  public void fire() {
    double da = angle / (endTick - startTick);
    element.rotate(da);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
