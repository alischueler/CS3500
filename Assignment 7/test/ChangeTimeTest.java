import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.ChangeTime;
import org.junit.Test;

/**
 * tests the change time class.
 */
public class ChangeTimeTest {

  //tests creating a change time with start less than 0
  @Test(expected = IllegalArgumentException.class)
  public void StartLess0() {
    new ChangeTime(-2, 3);
  }

  //tests creating a change time with end less than start
  @Test(expected = IllegalArgumentException.class)
  public void EndLessStart() {
    new ChangeTime(3, 1);
  }

  //tests creating a change time with end less than 0
  @Test(expected = IllegalArgumentException.class)
  public void EndLess0() {
    new ChangeTime(3, -4);
  }

  //tests getting the correct start value
  @Test
  public void getStart() {
    ChangeTime ct = new ChangeTime(34, 56);
    assertEquals(34, ct.getStart());
  }

  //tests getting the correct end value
  @Test
  public void getEnd() {
    ChangeTime ct = new ChangeTime(34, 56);
    assertEquals(56, ct.getEnd());
  }

  //tests that two change times are equal
  @Test
  public void testEquals() {
    ChangeTime ct = new ChangeTime(34, 56);
    ChangeTime ct1 = new ChangeTime(34, 56);
    assertTrue(ct.equals(ct1));
  }

  //tests that two change times are not equal
  @Test
  public void testEqualsNot() {
    ChangeTime ct = new ChangeTime(34, 56);
    ChangeTime ct1 = new ChangeTime(33, 56);
    assertFalse(ct.equals(ct1));
  }

  //tests putting a changetime toString
  @Test
  public void testToString() {
    ChangeTime ct = new ChangeTime(34, 56);
    assertEquals("34 56", ct.toString());
  }
}