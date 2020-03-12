package cs3500.operations;

import java.util.List;
import java.util.Map;

import cs3500.IElement;
import cs3500.IOperation;

/**
 * extends {@code AbstractOp} to handle the deletion of an element.
 */
public class DeleteOp implements IOperation {

  private Map<String, IElement> elements;
  private List<IOperation> operations;
  private IElement elementToDelete;
  private int tickToFireAt;

  /**
   * DeleteOp constructor.
   * @param elements List of elements
   * @param operations list of operations
   * @param del element to delete
   * @param tick tick to delete at
   */
  public DeleteOp(Map<String, IElement> elements, List<IOperation> operations,
                  IElement del, int tick) {
    this.elements = elements;
    this.operations = operations;
    this.elementToDelete = del;
    this.tickToFireAt = tick;
  }

  @Override
  public int getTickToFireAt() {
    return tickToFireAt;
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
