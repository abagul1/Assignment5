package cs3500.Animator.hw05.Operations;

import cs3500.Animator.hw05.AbstractOp;
import cs3500.Animator.hw05.IElement;

public class ScaleOp extends AbstractOp {
  private IElement element;
  private double ds;

  public ScaleOp(IElement e, double ds, int tick) {
    super(tick);
    this.element = e;
    this.ds = ds;
  }

  @Override
  public void fire() {
    element.scale(ds);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
