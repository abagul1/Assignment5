package cs3500.operations;

import java.awt.Color;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the changing color of an element.
 */
public class ChangeColorOp extends AbstractOp {
  private IElement element;
  private double dr;
  private double dg;
  private double db;

  /**
   * Constructor for change color.
   * @param e element to change color
   * @param dr delta red
   * @param dg delta green
   * @param db delta blue
   * @param tick tick to fire at
   */
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
