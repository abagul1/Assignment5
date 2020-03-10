package cs3500.Animator.hw05.Operations;

import cs3500.Animator.hw05.AbstractOp;
import cs3500.Animator.hw05.IElement;

public class RotateOp extends AbstractOp {

  private IElement element;
  private double da;

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
