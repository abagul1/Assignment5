package cs3500.operations;

import java.awt.Color;
import java.util.Map;

import cs3500.IElement;
import cs3500.IOperation;

/**
 * extends {@code AbstractOp} to handle the insertion of an element.
 */
public class InsertOp implements IOperation {

  private Map<String, IElement> elements;
  private IElement elementToAdd;
  private int alpha;
  private int tickToFireAt;

  /**
   * Constructor for insertion.
   * @param elements list of elements
   * @param elem to be inserted
   * @param alpha original alpha value
   * @param tick tick to insert at
   */
  public InsertOp(Map<String, IElement> elements, IElement elem, int alpha, int tick) {
    this.elements = elements;
    this.elementToAdd = elem;
    this.tickToFireAt = tick;
    this.alpha = alpha;
  }

  @Override
  public int getTickToFireAt() {
    return tickToFireAt;
  }

  @Override
  public void fire() {
    elementToAdd.setColor(new Color(elementToAdd.getColor().getRed(),
            elementToAdd.getColor().getGreen(),
            elementToAdd.getColor().getBlue(),
            alpha));
    elements.put(elementToAdd.getID(), elementToAdd);
  }

  @Override
  public String getElementId() {
    return elementToAdd.getID();
  }

  /**
   * Returns the initial alpha.
   * @return the alpha value
   */
  public int getAlpha() {
    return alpha;
  }
}
