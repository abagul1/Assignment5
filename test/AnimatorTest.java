import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.*;

import cs3500.Animator.hw05.AnimationModel;
import cs3500.Animator.hw05.IAnimation;
import cs3500.Animator.hw05.IElement;
import cs3500.Animator.hw05.Posn;
import cs3500.Animator.hw05.elements.Rectangle;

public class AnimatorTest {
  private IAnimation am;
  private IElement r1 = new Rectangle("1", Color.GREEN,
          new Posn(50,50),0, 10,5);

  @Test
  public void testMoveOp() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.move("1", 60, 30, 3, 5);
    am.executeOperations();
    assertEquals(60, am.getElement("1").getPosn().getX(), 0.001);
    assertEquals(30, am.getElement("1").getPosn().getY(), 0.001);
  }

  @Test
  public void testInsertOp() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.executeOperations();
    assertEquals(am.getElement("1"), r1);
  }

  @Test
  public void testRotateOp() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.rotate("1", 90, 3, 6);
    am.executeOperationsUntil(4);
    assertEquals(30, am.getElement("1").getAngle(), 0.001);
  }

  @Test
  public void testScaleOp() {
    //TODO: Implement scale in the element class.
  }

  @Test
  public void testDeleteOp() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.deleteElement("1", 3);
    try {
      am.getElement("1");
    } catch (NullPointerException npe) {
      assertTrue(npe.getMessage().length() > 0);
    }
  }

  @Test
  public void testChangeColorOp() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    assertEquals(255, am.getElement("1").getColor().getGreen());
    assertEquals(0, am.getElement("1").getColor().getRed());
    assertEquals(0, am.getElement("1").getColor().getBlue());
    am.changeColor("1", new Color(34, 200, 145), 3, 4);
    am.executeOperations();
    assertEquals(200,am.getElement("1").getColor().getGreen());
    assertEquals(34,am.getElement("1").getColor().getRed());
    assertEquals(145,am.getElement("1").getColor().getBlue());
  }

  @Test
  public void testChangeVisibilityOp() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    assertEquals(0, am.getElement("1").getColor().getAlpha());
    am.executeOperationsUntil(3);
    assertEquals(255, am.getElement("1").getColor().getAlpha());
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.changeVisibility("1", 23, 4, 6);
    am.executeOperations();
    assertEquals(23, am.getElement("1").getColor().getAlpha());
  }
}
