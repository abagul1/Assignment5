package cs3500.Animator.hw05.operations;

import java.awt.*;

import cs3500.Animator.hw05.AbstractOp;
import cs3500.Animator.hw05.IElement;

public class ChangeVisibilityOp extends AbstractOp {
  private IElement element;
  private int dalpha;

  public ChangeVisibilityOp(IElement e, double dalpha, int tick) {
    super(tick);
    this.element = e;
    this.dalpha = (int) dalpha;
  }

  @Override
  public void fire() {
    Color c = new Color(element.getColor().getRed(),
            element.getColor().getGreen(),
            element.getColor().getBlue(), element.getColor().getAlpha() + dalpha);
    element.setColor(c);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
