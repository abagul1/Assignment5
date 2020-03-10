package cs3500.Animator.hw05;

import java.util.Map;

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
}
