package cs3500.Animator.hw05;
import java.awt.*;
import java.util.LinkedHashSet;

/**
 * Model for a 2D animator.
 */
public class AnimatorModel implements AnimatorOperations {
  private LinkedHashSet<Shape> shapes;

  public AnimatorModel() {

  }

  @Override
  public void createAnimation(int height, int width) {

  }

  @Override
  public void moveXY(Shape s, int x, int y, int endTick, Color c) {

  }

  @Override
  public String getWorldState(int tick) {
    return null;
  }

  @Override
  public void createShape(ShapeType st, int x, int y, Color c, int width, int height) {

  }

  @Override
  public Shape getShape(int x, int y, int tick) {
    return null;
  }
}
