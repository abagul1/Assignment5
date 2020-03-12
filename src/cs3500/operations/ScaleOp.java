package cs3500.operations;

import cs3500.AbstractOp;
import cs3500.IElement;

/**
 * extends {@code AbstractOp} to handle the incremental scaling of an element.
 */
public class ScaleOp extends AbstractOp {
  private IElement element;
  private double ds;

  /**
   * Constructor for a scale operation.
   * @param e element to scale
   * @param ds delta scale
   * @param tick tick to fire at
   */
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
