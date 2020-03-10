package cs3500.Animator.hw05.Operations;

import java.awt.*;

import cs3500.Animator.hw05.AbstractOp;
import cs3500.Animator.hw05.IElement;

public class ChangeVisibilityOp extends AbstractOp {
  private IElement element;
  private double dalpha;

  public ChangeVisibilityOp(IElement e, double dalpha, int tick) {
    super(tick);
    this.element = e;
    this.dalpha = dalpha;
  }

  @Override
  public void fire() {
    Color c = new Color((int) ((double) element.getColor().getAlpha() + dalpha));
    element.setColor(c);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
