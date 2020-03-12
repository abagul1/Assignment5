package cs3500;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cs3500.operations.ChangeColorOp;
import cs3500.operations.ChangeVisibilityOp;
import cs3500.operations.DeleteOp;
import cs3500.operations.InsertOp;
import cs3500.operations.MoveOp;
import cs3500.operations.RotateOp;
import cs3500.operations.ScaleOp;

/**
 * Represents the Animation with all the elements and operations.
 */
public class AnimationModel implements IAnimation {

  private Map<String, IElement> elements;
  private List<IOperation> operations;
  private Map<String, List<String>> verboseOps;

  int currentTick;
  int ticksPerFrame;

  int windowWidth;
  int windowHeight;

  /**
   * Constructor for animation model.
   * @param numTicksPerFrame speed of animation
   * @param width width of animation panel
   * @param height height of animation panel
   */
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

  /**
   * Creates a textual description of the move operation in the animation.
   * @param id id of the move to be created
   * @param x destination x coordinate
   * @param y destination y coordinate
   * @param startTick start tick to move
   * @param endTick end tick to move
   */
  private void addVerboseMove(String id, double x, double y, int startTick, int endTick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    double startingX = elements.get(id).getPosn().getX();
    double startingY = elements.get(id).getPosn().getY();
    str.append("MOVE").append(" ").append(id).append(" ").append(startTick)
            .append(" ").append(startingX)
            .append(" ").append(startingY).append(" ").append(endTick).append(" ").append(x)
            .append(" ").append(y);

    if (!verboseOps.containsKey(id)) {
      throw new IllegalStateException("Element can't be moved before it exists");
    }
    verboseOps.get(id).add(str.toString());
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
    this.addVerboseRotate(id, angle, startTick, endTick);
  }

  /**
   * Create a textual description of the rotate operation.
   * @param id id of the move to be created
   * @param angle angle to be rotated
   * @param startTick start tick to rotate
   * @param endTick end tick to rotate
   */
  private void addVerboseRotate(String id, double angle, int startTick, int endTick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();

    double startingAngle = elements.get(id).getAngle();

    str.append("ROTATE").append(" ").append(id).append(" ").append(startTick).append(" ")
            .append(startingAngle)
            .append(" ").append(endTick).append(" ").append(angle);

    if (!verboseOps.containsKey(id)) {
      throw new IllegalStateException("Element can't be rotated before it exists");
    }
    verboseOps.get(id).add(str.toString());
  }

  @Override
  public void scale(String id, double scaleFactor, int startTick, int endTick) {
    this.checkNotNull();
    this.checkIdExists(id);
    double ds = scaleFactor / (endTick - startTick);
    for (int i = startTick; i < endTick; i++) {
      operations.add(new ScaleOp(elements.get(id), ds, i));
    }
    this.addVerboseScale(id, scaleFactor, startTick, endTick);
  }

  /**
   * Create a textual description of the rotate operation.
   * @param id id of the move to be created
   * @param scaleFactor angle to be rotated
   * @param startTick start tick to rotate
   * @param endTick end tick to rotate
   */
  private void addVerboseScale(String id, double scaleFactor, int startTick, int endTick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();

    str.append("SCALE").append(" ").append(id).append(" ").append(startTick).append(" ")
            .append(endTick).append(" ").append(scaleFactor);

    if (!verboseOps.containsKey(id)) {
      throw new IllegalStateException("Element can't be scaled before it exists");
    }
    verboseOps.get(id).add(str.toString());
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

    this.addVerboseColor(id, color, startTick, endTick);
  }

  /**
   * Create a textual description of the color operation.
   * @param id ID of the color needed to change
   * @param color color to change to
   * @param startTick start of operation
   * @param endTick end of operation
   */
  private void addVerboseColor(String id, Color color, int startTick, int endTick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    double startingR = elements.get(id).getColor().getRed();
    double startingG = elements.get(id).getColor().getGreen();
    double startingB = elements.get(id).getColor().getBlue();

    str.append("CHANGE COLOR").append(" ").append(id).append(" ").append(startTick).append(" ")
            .append(startingR).append(" ")
            .append(startingG).append(" ")
            .append(startingB).append(" ")
            .append(endTick).append(" ")
            .append(color.getRed()).append(" ")
            .append(color.getGreen()).append(" ").append(color.getBlue());

    if (!verboseOps.containsKey(id)) {
      throw new IllegalStateException("Element color can't be changed before it exists");
    }
    verboseOps.get(id).add(str.toString());
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
    this.addVerboseVisibility(id, alpha, startTick, endTick);
  }

  /**
   * Create a textual description of the visibility operation.
   * @param id ID of the alpha needed to change
   * @param alpha alpha to change to
   * @param startTick start of operation
   * @param endTick end of operation
   */
  private void addVerboseVisibility(String id, int alpha, int startTick, int endTick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    double startingAlpha = elements.get(id).getColor().getAlpha();

    str.append("CHANGE VISIBILITY").append(" ").append(id).append(" ").append(startTick).append(" ")
            .append(startingAlpha).append(" ").append(endTick).append(" ").append(alpha);

    if (!verboseOps.containsKey(id)) {
      throw new IllegalStateException("Element visibility can't be changed before it exists");
    }
    verboseOps.get(id).add(str.toString());
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

  /**
   * Create a textual description of the insert operation.
   * @param element element to insert
   * @param tick tick to insert at
   */
  private void addVerboseInsert(IElement element, int tick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    str.append("INSERT ").append(element.getID()).append(" ").append(tick);

    if (verboseOps.containsKey(element.getID())) {
      throw new IllegalArgumentException("Element ID already exists");
    }
    else {
      verboseOps.put(element.getID(), new ArrayList<>());
      verboseOps.get(element.getID()).add(str.toString());
    }
  }

  @Override
  public void deleteElement(String id, int tick) {
    this.checkNotNull();
    this.checkIdExists(id);
    operations.add(new DeleteOp(elements, operations, elements.get(id), tick));
    this.addVerboseDelete(elements.get(id), tick);
  }

  /**
   * Create a textual description of the delete operation.
   * @param element element to delete
   * @param tick tick to delete at
   */
  private void addVerboseDelete(IElement element, int tick) {
    if (verboseOps == null) {
      throw new IllegalStateException("Error: Verbose Ops is null");
    }

    StringBuilder str = new StringBuilder();
    str.append("DELETE ").append(element.getID()).append(" ").append(tick);

    if (!verboseOps.containsKey(element.getID())) {
      throw new IllegalArgumentException("Element can't be deleted before it exists");
    }
    verboseOps.get(element.getID()).add(str.toString());
  }

  @Override
  public String getVerboseAnimation() {
    StringBuilder str = new StringBuilder();
    for (String id : verboseOps.keySet()) {
      for (String i : verboseOps.get(id)) {
        str.append(i).append("\n");
      }
      str.append("\n");
    }

    return str.toString();
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
