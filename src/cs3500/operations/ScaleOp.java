package cs3500.operations;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the incremental scaling of an element.
 */
public class ScaleOp extends AbstractOp {
  private IElement element;
  private double scaleFactor;
  private double endTick;
  private double startTick;

  /**
   * Constructor for a scale operation.
   * @param e element to scale
   * @param scaleFactor Factor to scale by
   * @param tick tick to fire at
   */
  public ScaleOp(IElement e, double scaleFactor, int tick, int endTick, int startTick) {
    super(tick);
    this.element = e;
    this.scaleFactor = scaleFactor;
    this.endTick = endTick;
    this.startTick = startTick;
  }

  @Override
  public void fire() {
    double ds = Math.pow(scaleFactor, 1 / (endTick - startTick));
    element.scale(ds);
  }

  @Override
  public String getElementId() {
    return element.getID();
  }
}
