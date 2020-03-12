package cs3500.operations;

import java.awt.Color;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the changing visibility of an element.
 */
public class ChangeVisibilityOp extends AbstractOp {
  private IElement element;
  private int alpha;
  private int endTick;

  /**
   * Constructor for visibility.
   * @param e element to change
   * @param alpha change in alpha
   * @param tick tick to change visibility
   * @param endTick tick that operation ends
   */
  public ChangeVisibilityOp(IElement e, int alpha, int tick, int endTick) {
    super(tick);
    this.element = e;
    this.alpha = alpha;
    this.endTick = endTick;
  }

  @Override
  public void fire() {
    int dalpha = (alpha - element.getColor().getAlpha()) / (endTick - tickToFireAt);
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
