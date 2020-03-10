package cs3500.Animator.hw05.Operations;

import java.util.Map;

import cs3500.Animator.hw05.IElement;
import cs3500.Animator.hw05.IOperation;

public class InsertOp implements IOperation {

  private Map<String, IElement> elements;
  private IElement elementToAdd;
  private int TickToFireAt;

  public InsertOp(Map<String, IElement> elements, IElement elem, int tick) {
    this.elements = elements;
    this.elementToAdd = elem;
    this.TickToFireAt = tick;
  }

  @Override
  public int getTickToFireAt() {
    return TickToFireAt;
  }

  @Override
  public void fire() {
    elements.put(elementToAdd.getID(), elementToAdd);
  }

  @Override
  public String getElementId() {
    return elementToAdd.getID();
  }
}
