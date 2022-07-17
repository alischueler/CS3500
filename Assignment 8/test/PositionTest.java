import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.Position;
import org.junit.Test;

/**
 * a class to test the Position class.
 */
public class PositionTest {

  //tests that the correct x is obtained
  @Test
  public void testCorrectX() {
    Position pos = new Position(8.00, 3.0);
    assertEquals(8, pos.getX(), .01);
  }

  //tests the correct y value is obtained
  @Test
  public void testCorrectY() {
    Position pos = new Position(8.00, 3.0);
    assertEquals(3, pos.getY(), .01);
  }

  //tests the correct y value is returned when only x updated
  @Test
  public void testCorrectYUpdateX() {
    Position pos = new Position(8.00, 3.0);
    pos.setX(108.2);
    assertEquals(3, pos.getY(), .01);
  }

  //tests the correct x value is returned when only y is updated
  @Test
  public void testCorrectXUpdateY() {
    Position pos = new Position(8.00, 3.0);
    pos.setY(323.7);
    assertEquals(8, pos.getX(), .01);
  }

  //tests correct x value is returned when x is updated
  @Test
  public void testCorrectXUpdateX() {
    Position pos = new Position(8.00, 3.0);
    pos.setX(138.2);
    assertEquals(138.2, pos.getX(), .01);
  }

  //tests correct y is returned when y is updated
  @Test
  public void testCorrectYUpdateY() {
    Position pos = new Position(8.00, 3.0);
    pos.setY(-345.3);
    assertEquals(-345.3, pos.getY(), .01);
  }

  //tests the correct X is returned when position is updated
  @Test
  public void testCorrectXUpdatePos() {
    Position pos = new Position(8.00, 3.0);
    pos.updatePos(-345.3, 945);
    assertEquals(-345.3, pos.getX(), .01);
  }

  //tests the correct Y is returned when position is updated
  @Test
  public void testCorrectYUpdatePos() {
    Position pos = new Position(8.00, 3.0);
    pos.updatePos(-345.3, 945);
    assertEquals(945, pos.getY(), .01);
  }

  //tests that two positions are equal to each other
  @Test
  public void testTwoPosEqual() {
    Position pos = new Position(8.00, 3.0);
    Position pos2 = new Position(8, 3);
    assertTrue(pos.equals(pos2));
  }

  //tests that two different positions are equal to each other
  @Test
  public void testTwoPosNotEqual() {
    Position pos = new Position(8.00, 3.0);
    Position pos2 = new Position(881, -326);
    assertFalse(pos.equals(pos2));
  }

  //tests the toString of a position
  @Test
  public void testToStringPos() {
    Position pos = new Position(8.00, 3.0);
    assertEquals("8 3", pos.toString());
  }

  //tests the toString of a position that has been updated
  @Test
  public void testToStringPosUpdated() {
    Position pos = new Position(8.00, 3.0);
    pos.updatePos(255, -928);
    assertEquals("255 -928", pos.toString());
  }
}