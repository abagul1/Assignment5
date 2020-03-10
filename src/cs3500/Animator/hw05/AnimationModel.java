package cs3500.Animator.hw05;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;
import javax.swing.text.html.HTMLDocument;

import cs3500.Animator.hw05.operations.ChangeColorOp;
import cs3500.Animator.hw05.operations.ChangeVisibilityOp;
import cs3500.Animator.hw05.operations.DeleteOp;
import cs3500.Animator.hw05.operations.InsertOp;
import cs3500.Animator.hw05.operations.MoveOp;
import cs3500.Animator.hw05.operations.RotateOp;
import cs3500.Animator.hw05.operations.ScaleOp;

public class AnimationModel implements IAnimation {

  private Map<String, IElement> elements;
  private List<IOperation> operations;
  private Map<Integer, List<String>> verboseOps;

  int currentTick;
  int ticksPerFrame;

  int windowWidth;
  int windowHeight;

  public AnimationModel(int numTicksPerFrame, int width, int height) {
    elements = new HashMap<>();
    operations = new ArrayList<>();
    verboseOps = new HashMap<>();

    currentTick = 0;
    ticksPerFrame = numTicksPerFrame;
    windowWidth = width;
    windowHeight = height;
  }

  @Override
  public void move(String id, double x, double y, int startTick, int endTick) {
    this.checkNotNull();

    double deltaX = (x - elements.get(id).getPosn().getX()) / (endTick - startTick);
    double deltaY = (y - elements.get(id).getPosn().getY()) / (endTick - startTick);
    for (int i = startTick; i < endTick; i++) {
      operations.add(new MoveOp(elements.get(id), deltaX, deltaY, i));
    }

    try {
      addVerboseMove(id, x, y, startTick, endTick);
    }
    catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void addVerboseMove(String id, double x, double y, int startTick, int endTick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    double startingX = elements.get(id).getPosn().getX();
    double startingY = elements.get(id).getPosn().getY();
    str.append("MOVE ").append(id).append(" ").append(startTick).append(" ").append(startingX)
            .append(" ").append(startingY).append(" ").append(endTick).append(" ").append(x)
            .append(" ").append(y);

    if (!verboseOps.containsKey(startTick)) {
      verboseOps.put(startTick, new ArrayList<>());
    }
    verboseOps.get(startTick).add(str.toString());
  }

  /**
   * Checks that the elements and operations fields are not null.
   */
  private void checkNotNull() {
    if (elements == null) {
      throw new IllegalStateException("Error: Element map is null");
    }
    if (operations == null) {
      throw new IllegalStateException("Error: Operations is null");
    }
  }

  /**
   * Checks that the elements and operations fields are not null.
   * @param id is the element id
   */
  private void checkIdExists(String id) {
    if (!elements.containsKey(id)) {
      throw new IllegalArgumentException("No element with that ID exists");
    }
  }

  @Override
  public void rotate(String id, double angle, int startTick, int endTick) {
    this.checkNotNull();
    this.checkIdExists(id);
    double da = angle / (endTick - startTick);
    for (int i = startTick; i < endTick; i++) {
      operations.add(new RotateOp(elements.get(id), da, i));
    }
  }

  @Override
  public void scale(String id, double scaleFactor, int startTick, int endTick) {
    this.checkNotNull();
    this.checkIdExists(id);
    double ds = scaleFactor / (endTick - startTick);
    for (int i = startTick; i < endTick; i++) {
      operations.add(new ScaleOp(elements.get(id), ds, i));
    }
  }

  @Override
  public void changeColor(String id, Color color, int startTick, int endTick) {
    this.checkNotNull();
    this.checkIdExists(id);
    double dr = (double) (color.getRed() - elements.get(id).getColor().getRed())
            / (double) (endTick - startTick);
    double dg = (double) (color.getGreen() - elements.get(id).getColor().getGreen())
            / (double) (endTick - startTick);
    double db = (double) (color.getBlue() - elements.get(id).getColor().getBlue())
            / (double) (endTick - startTick);
    for (int i = startTick; i < endTick; i++) {
      operations.add(new ChangeColorOp(elements.get(id), dr, dg, db, i));
    }
  }

  @Override
  public void changeVisibility(String id, int alpha, int startTick, int endTick) {
    this.checkNotNull();
    this.checkIdExists(id);
    int trueAlpha = 0;
    for (IOperation op : operations) {
      if (op.getElementId().equals(id) && op instanceof InsertOp) {
        trueAlpha = ((InsertOp) op).getAlpha();
      }
    }
    double dalpha = (double) (alpha - trueAlpha)
            / (endTick - startTick);
    for (int i = startTick; i < endTick; i++) {
      operations.add(new ChangeVisibilityOp(elements.get(id), dalpha, i));
    }
  }

  @Override
  public void insertElement(IElement element, int tick) {
    if (element == null) {
      throw new IllegalArgumentException("Cannot add a null element to the animation");
    }
    if (elements == null) {
      throw new IllegalStateException("Error: Element map is null");
    }
    if (operations == null) {
      throw new IllegalStateException("Error: Operations is null");
    }
    int alpha = element.getColor().getAlpha();
    operations.add(new InsertOp(elements, element, alpha, tick));
    element.setColor(new Color(element.getColor().getRed(),
            element.getColor().getGreen(),
            element.getColor().getBlue(),
            0));
    elements.put(element.getID(), element);

    try {
      addVerboseInsert(element, tick);
    }
    catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void addVerboseInsert(IElement element, int tick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    str.append("INSERT ").append(element.getID()).append(" ").append(tick);

    if (!verboseOps.containsKey(tick)) {
      verboseOps.put(tick, new ArrayList<>());
    }
    verboseOps.get(tick).add(str.toString());
  }

  @Override
  public void deleteElement(String id, int tick) {
    this.checkNotNull();
    this.checkIdExists(id);
    operations.add(new DeleteOp(elements, operations, elements.get(id), tick));
  }

  @Override
  public String getVerboseAnimation() {
    return null;
  }

  @Override
  public IFrame getFrameAtTick(int tick) {
    return null;
  }

  @Override
  public void executeOperations() {
    while (!operations.isEmpty()) {
      for (Iterator<IOperation> iterator = operations.iterator(); iterator.hasNext();) {
        IOperation op = iterator.next();
          if (op.getTickToFireAt() == currentTick) {
          op.fire();
          iterator.remove();
        }
      }
      currentTick++;
    }
  }

  @Override
  public void executeOperationsUntil(int tick) {
    for (currentTick = 0; currentTick < tick; currentTick++) {
      if (operations.isEmpty()) {
        break;
      }
      for (Iterator<IOperation> iterator = operations.iterator(); iterator.hasNext();) {
        IOperation op = iterator.next();
        if (op.getTickToFireAt() == currentTick) {
          op.fire();
          iterator.remove();
        }
      }
    }
  }

  @Override
  public IElement getElement(String id) {
    return elements.get(id);
  }
}
