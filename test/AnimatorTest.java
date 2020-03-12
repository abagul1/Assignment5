import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import cs3500.AnimationModel;
import cs3500.IAnimation;
import cs3500.IElement;
import cs3500.Posn;
import cs3500.elements.Rectangle;

/**
 * Test class for animator model.
 */
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

  @Test
  public void testGetVerboseAnimationMove() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.move("1", 60, 30, 3, 5);
    String str = am.getVerboseAnimation();
    assertEquals("INSERT 1 2\nMOVE 1 3 50.0 50.0 5 60.0 30.0\n\n", str);
  }

  @Test
  public void testGetVerboseAnimationRotate() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.rotate(r1.getID(), 90, 3, 5 );
    String str = am.getVerboseAnimation();
    assertEquals("INSERT 1 2\nROTATE 1 3 0.0 5 90.0\n\n", str);
  }

  @Test
  public void testGetVerboseAnimationScale() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.scale("1", 2, 3,  5);
    String str = am.getVerboseAnimation();
    assertEquals("INSERT 1 2\nSCALE 1 3 5 2.0\n\n", str);
  }

  @Test
  public void testGetVerboseAnimationChangeColor() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.changeColor("1", Color.BLUE, 6, 8);
    String str = am.getVerboseAnimation();
    assertEquals("INSERT 1 2\nCHANGE COLOR 1 6 0.0 255.0 0.0 8 0 0 255\n\n", str);
  }

  @Test
  public void testGetVerboseAnimationChangeVisibility() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.changeVisibility("1", 60, 3, 5);
    String str = am.getVerboseAnimation();
    assertEquals("INSERT 1 2\nCHANGE VISIBILITY 1 3 0.0 5 60\n\n", str);
  }

  @Test
  public void testGetVerboseAnimationDelete() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.deleteElement("1", 5);
    String str = am.getVerboseAnimation();
    assertEquals("INSERT 1 2\nDELETE 1 5\n\n", str);
  }

  @Test
  public void testSimultaneousMove() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.move("1", 34, 25, 3, 5);
    am.move("1", 34, 25, 4, 7);
    try {
      am.executeOperations();
    } catch (IllegalArgumentException iae) {
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  @Test
  public void testMove2() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.move("1", 34, 25, 3, 5);
    am.move("1", 34, 27, 5, 7);
    am.executeOperations();
    assertEquals(34, am.getElement("1").getPosn().getX(), 0.001);
    assertEquals(27, am.getElement("1").getPosn().getY(), 0.001);
  }

  @Test
  public void testRotateOp2() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.rotate("1", 90, 3, 6);
    am.rotate("1", -90, 6,9);
    am.rotate("1", 360, 9,10);
    am.rotate("1", 16, 11,12);
    am.executeOperations();
    assertEquals(16, am.getElement("1").getAngle(), 0.001);
  }

  @Test
  public void testChangeColorOp2() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    assertEquals(255, am.getElement("1").getColor().getGreen());
    assertEquals(0, am.getElement("1").getColor().getRed());
    assertEquals(0, am.getElement("1").getColor().getBlue());
    am.changeColor("1", new Color(34, 200, 145), 3, 4);
    am.changeColor("1", new Color(76, 34, 12), 7, 10);
    am.executeOperations();
    assertEquals(34,am.getElement("1").getColor().getGreen());
    assertEquals(76,am.getElement("1").getColor().getRed());
    assertEquals(12,am.getElement("1").getColor().getBlue());
  }

  @Test
  public void testChangeVisibilityOp2() {
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    assertEquals(0, am.getElement("1").getColor().getAlpha());
    am.executeOperationsUntil(3);
    assertEquals(255, am.getElement("1").getColor().getAlpha());
    am = new AnimationModel(1, 500, 500);
    am.insertElement(r1, 2);
    am.changeVisibility("1", 23, 4, 6);
    am.changeVisibility("1", 56, 6, 8);
    am.executeOperations();
    assertEquals(56, am.getElement("1").getColor().getAlpha());
  }
}
