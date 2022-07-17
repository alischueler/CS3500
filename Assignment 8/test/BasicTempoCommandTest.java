import static org.junit.Assert.*;

import cs3500.animator.model.BasicTempoCommand;
import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.ITempoCommands;
import org.junit.Test;

/**
 * a class to hold tests about a tempo change.
 */
public class BasicTempoCommandTest {

  //tests null string
  @Test(expected = IllegalArgumentException.class)
  public void testNullString() {
    new BasicTempoCommand(null, new ChangeTime(0, 3), 3);
  }

  //tests null change time
  @Test(expected = IllegalArgumentException.class)
  public void testNullCT() {
    new BasicTempoCommand("slow-mo", null, 3);
  }

  //tests name is not slowmo
  @Test(expected = IllegalArgumentException.class)
  public void testBadName() {
    new BasicTempoCommand("slo-mo", new ChangeTime(0, 3), 3);
  }

  //tests tempo < 1
  @Test(expected = IllegalArgumentException.class)
  public void testBadTempo() {
    new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 0);
  }

  //tests getting tempo
  @Test
  public void testGetTempo() {
    ITempoCommands t = new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 30);
    assertEquals(30, t.getTempo());
  }

  //tests getting shape
  @Test
  public void testGetShape() {
    ITempoCommands t = new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 30);
    assertEquals("slow-mo", t.getShape());
  }

  //tests getting tick
  @Test
  public void testGetTicks() {
    ITempoCommands t = new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 30);
    assertEquals(new ChangeTime(0, 3), t.getTicks());
  }

  //tests two objects are equal
  @Test
  public void testTwoEqual() {
    ITempoCommands t = new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 30);
    ITempoCommands t2 = new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 30);
    assertEquals(t, t2);
  }

  //tests two objects are not equal
  @Test
  public void testTwoNotEqual() {
    ITempoCommands t = new BasicTempoCommand("slow-mo", new ChangeTime(0, 3), 30);
    ITempoCommands t2 = new BasicTempoCommand("slow-mo", new ChangeTime(0, 31), 30);
    assertNotEquals(t, t2);
  }
}