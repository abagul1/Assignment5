package cs3500.Animator.hw05;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    if (elements == null) {
      throw new IllegalStateException("Error: Element map is null");
    }
    if (operations == null) {
      throw new IllegalStateException("Error: Operations is null");
    }

    try {
      double deltaX = (x - elements.get(id).getPosn().getX()) / (endTick - startTick);
      double deltaY = (y - elements.get(id).getPosn().getY()) / (endTick - startTick);

      for (int i = startTick; i <= endTick; i++) {
        operations.add(new MoveOp(elements.get(id), deltaX, deltaY, i));
      }

      try {
        addVerboseMove(id, x, y, startTick, endTick);
      }
      catch (IllegalStateException e) {
        throw new IllegalStateException(e.getMessage());
      }

    }
    catch (NullPointerException e) {
      throw new IllegalArgumentException("No element with that ID exists");
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

  @Override
  public void rotate(String id, double angle, int startTick, int endTick) {

  }

  @Override
  public void scale(String id, double scaleFactor, int startTick, int endTick) {

  }

  @Override
  public void changeColor(String id, Color color, int startTick, int endTick) {

  }

  @Override
  public void changeVisibility(String id, int alpha, int startTick, int endTick) {

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

    operations.add(new InsertOp(elements, element, tick));

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

  }

  @Override
  public String getVerboseAnimation() {
    return null;
  }

  @Override
  public IFrame getFrameAtTick(int tick) {
    return null;
  }

  public void executeOperations() {
    while (!operations.isEmpty()) {
      for (IOperation op : operations) {
        if (op.getTickToFireAt() == currentTick) {
          op.fire();
          operations.remove(op);
        }
        currentTick++;
      }
    }
  }

  public void executeOperationsUntil(int tick) {
    while (!operations.isEmpty()) {
      for (; currentTick <= tick; currentTick++) {
        for (IOperation op : operations) {
          if (op.getTickToFireAt() == currentTick) {
            op.fire();
            operations.remove(op);
          }
        }
      }
    }
  }
}
