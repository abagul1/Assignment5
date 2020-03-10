package cs3500.Animator.hw05.operations;

import java.awt.*;
import java.util.Map;

import cs3500.Animator.hw05.IElement;
import cs3500.Animator.hw05.IOperation;

public class InsertOp implements IOperation {

  private Map<String, IElement> elements;
  private IElement elementToAdd;
  private int alpha;
  private int TickToFireAt;

  public InsertOp(Map<String, IElement> elements, IElement elem, int alpha, int tick) {
    this.elements = elements;
    this.elementToAdd = elem;
    this.TickToFireAt = tick;
    this.alpha = alpha;
  }

  @Override
  public int getTickToFireAt() {
    return TickToFireAt;
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

  public int getAlpha() {
    return alpha;
  }
}
