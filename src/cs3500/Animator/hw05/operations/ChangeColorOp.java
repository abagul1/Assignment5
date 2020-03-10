package cs3500.Animator.hw05.operations;

import java.awt.*;

import cs3500.Animator.hw05.AbstractOp;
import cs3500.Animator.hw05.IElement;

public class ChangeColorOp extends AbstractOp {
  private IElement element;
  private double dr;
  private double dg;
  private double db;

  public ChangeColorOp(IElement e, double dr, double dg, double db, int tick) {
    super(tick);
    this.element = e;
    this.dr = dr;
    this.dg = dg;
    this.db = db;
  }

  @Override
  public void fire() {
    Color c = new Color((int) ((double) element.getColor().getRed() + dr),
            (int) ((double) element.getColor().getGreen() + dg),
            (int) ((double)element.getColor().getBlue() + db));
    element.setColor(c);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
