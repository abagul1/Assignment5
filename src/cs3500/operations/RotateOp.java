package cs3500.operations;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the incremental rotation of an element.
 */
public class RotateOp extends AbstractOp {

  private IElement element;
  private double da;

  /**
   * Constructor for a rotation.
   * @param e element to be rotated
   * @param da amount for the angle to be rotated
   * @param tick tick to start rotation
   */
  public RotateOp(IElement e, double da, int tick) {
    super(tick);
    this.element = e;
    this.da = da;
  }

  @Override
  public void fire() {
    element.rotate(da);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
