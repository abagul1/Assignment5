package cs3500.operations;

import java.awt.Color;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the changing visibility of an element.
 */
public class ChangeVisibilityOp extends AbstractOp {
  private IElement element;
  private int dalpha;

  /**
   * Constructor for visibility.
   * @param e element to change
   * @param dalpha change in alpha per tick
   * @param tick tick to change visibility
   */
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
