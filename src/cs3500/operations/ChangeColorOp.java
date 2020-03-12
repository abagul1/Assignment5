package cs3500.operations;

import java.awt.Color;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the changing color of an element.
 */
public class ChangeColorOp extends AbstractOp {
  private IElement element;
  private double r;
  private double g;
  private double b;
  private double endTick;

  /**
   * Constructor for change color.
   * @param e element to change color
   * @param r red
   * @param g green
   * @param b blue
   * @param tick tick to fire at
   * @param endTick tick ot finish
   */
  public ChangeColorOp(IElement e, double r, double g, double b, int tick, int endTick) {
    super(tick);
    this.element = e;
    this.r = r;
    this.g = g;
    this.b = b;
    this.endTick = endTick;
  }

  @Override
  public void fire() {
    double dr = (r - element.getColor().getRed())
            / (endTick - tickToFireAt);
    double dg = (g - element.getColor().getGreen())
            / (endTick - tickToFireAt);
    double db = (b - element.getColor().getBlue())
            / (endTick - tickToFireAt);

    Color c = new Color((int) ((double) element.getColor().getRed() + dr),
            (int) ((double) element.getColor().getGreen() + dg),
            (int) ((double) element.getColor().getBlue() + db));
    element.setColor(c);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
