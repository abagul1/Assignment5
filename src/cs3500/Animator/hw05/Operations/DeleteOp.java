package cs3500.Animator.hw05.Operations;

import java.util.List;
import java.util.Map;

import cs3500.Animator.hw05.IElement;
import cs3500.Animator.hw05.IOperation;

public class DeleteOp implements IOperation {

  private Map<String, IElement> elements;
  private List<IOperation> operations;
  private IElement elementToDelete;
  private int TickToFireAt;

  public DeleteOp(Map<String, IElement> elements, List<IOperation> operations,
                  IElement del, int tick) {
    this.elements = elements;
    this.operations = operations;
    this.elementToDelete = del;
    this.TickToFireAt = tick;
  }

  @Override
  public int getTickToFireAt() {
    return TickToFireAt;
  }

  @Override
  public void fire() {
    for (IOperation op : operations) {
      if (op.getElementId().equals(elementToDelete.getID())) {
        operations.remove(op);
      }
    }
    elements.remove(elementToDelete);
  }

  @Override
  public String getElementId() {
    return elementToDelete.getID();
  }
}
