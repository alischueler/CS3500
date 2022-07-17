import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.Color;
import cs3500.animator.model.Command;
import cs3500.animator.model.Position;
import cs3500.animator.model.TotalSize;
import org.junit.Test;

/**
 * class for testing the Command object.
 */
public class TestCommands {

  Command stringCommand;
  String bob010;
  String positionInc2;
  String colorIncreasing2;
  String dimSquareIncreasing2;

  /**
   * holds objects for testing.
   */
  private void examples() {

    bob010 = "bob 0 10";
    positionInc2 = " 10 10 30 20";
    colorIncreasing2 = " 20 20 20 40 30 60";
    dimSquareIncreasing2 = " 20 20 40 30";

    stringCommand = new Command(bob010 + positionInc2 + colorIncreasing2 + dimSquareIncreasing2);
  }

  /**
   * testing the time getter.
   */
  @Test
  public void testTime() {
    examples();
    assertEquals(stringCommand.getTicks(), new ChangeTime(0, 10));
  }

  /**
   * testing the time getter.
   */
  @Test
  public void testPosition() {
    examples();
    assertEquals(stringCommand.getStartPosition(), new Position(10, 10));
    assertEquals(stringCommand.getEndPosition(), new Position(30, 20));
  }

  /**
   * testing the size getter.
   */
  @Test
  public void testSize() {
    examples();
    assertEquals(stringCommand.getStartSize(), new TotalSize(20, 20));
    assertEquals(stringCommand.getEndSize(), new TotalSize(20, 40));
  }

  /**
   * testing the color getter.
   */
  @Test
  public void testColor() {
    examples();
    assertEquals(stringCommand.getStartColor(), new Color(30, 60, 20));
    assertEquals(stringCommand.getEndColor(), new Color(20, 40, 30));
  }
}
