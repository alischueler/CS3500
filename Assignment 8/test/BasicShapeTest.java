

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.BasicShape;
import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.Color;
import cs3500.animator.model.Command;
import cs3500.animator.model.ICommands;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.TotalSize;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * a class to tests the basic shape class.
 */
public class BasicShapeTest {

  Shape s;
  Shape duplicateS;
  Shape s2;
  Shape s3;
  Shape noCurrValues;

  /**
   * holds all example objects for testing.
   */
  protected void examples() {
    s = new BasicShape("s1", ShapeType.RECTANGLE);
    duplicateS = new BasicShape(s.getShapeName(), s.getShapeType());
    s2 = new BasicShape("s2", ShapeType.ELLIPSE);
    s3 = new BasicShape("s3", ShapeType.ELLIPSE);
    noCurrValues = new BasicShape("s11", ShapeType.ELLIPSE);
  }


  //tests changing the current pos
  @Test
  public void testChangingCurrPos() {
    examples();
    s3.updateCurrPos(345, 234.1);
    assertEquals(new Position(345, 234.1), s3.getCurrentPosition());
  }


  //tests changing the current color
  @Test
  public void testChangingCurrCol() {
    examples();
    s3.updateCurrColor(34, 234.1, 1);
    assertTrue(new Color(34, 234.1, 1).equals(s3.getCurrColor()));
  }


  //tests changing the current dimensions
  @Test
  public void testChangingCurrDimensions() {
    examples();
    s3.updateCurrSize(34, 234.1);
    assertEquals(new TotalSize(34, 234.1), s3.getCurrentDimension());
  }


  //tests that the current dimensions cannot be updated to have height less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeCurrHeightLess0() {
    examples();
    s.updateCurrSize(-.5, 467);
  }

  //tests that the current dimensions cannot be updated to have width less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeCurrWidthLess0() {
    examples();
    s3.updateCurrSize(.5, -.4);
  }

  //tests that the current color cannot be updated to have r, b, g values less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeCurrColorLess0() {
    examples();
    s2.updateCurrColor(.5, -1, 56);
  }

  //tests that the current color cannot be updated to have r, b, g values greater than 250
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeCurrColorGreater255() {
    examples();
    s.updateCurrColor(256, .4, 56);
  }


  //tests to make sure the correct name of a shape is returned
  @Test
  public void testCorrectName() {
    examples();
    assertEquals("s1", s.getShapeName());
  }

  //tests to make sure the correct shape type of a rectangle is returned
  @Test
  public void testCorrectShapeRectangle() {
    examples();
    assertEquals(ShapeType.RECTANGLE, s.getShapeType());
  }

  //tests to make sure the correct shape type of an oval is returned
  @Test
  public void testCorrectShapeOval() {
    examples();
    assertEquals(ShapeType.ELLIPSE, s3.getShapeType());
  }


  //tests two shapes that are equal
  @Test
  public void testEqualShapes() {
    examples();
    assertTrue(s.equals(duplicateS));
    assertTrue(duplicateS.equals(s));
  }

  //tests two shapes that are not equal
  @Test
  public void testNotEqualShapes() {
    examples();
    assertFalse(s.equals(s2));
    assertFalse(s2.equals(s));
  }

  //tests getting commands with empty command shape
  @Test
  public void testShapeNoCommands() {
    examples();
    assertEquals(new ArrayList<ICommands>(), s3.getCommands());
  }

  //tests getting commands with actual commands in shape
  @Test
  public void testShapeCommands() {
    examples();
    List<ICommands> comList = new ArrayList<>();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    comList.add(com);
    s2.addCommand(com);
    assertEquals(comList, s2.getCommands());
  }

  //getting shapes empty info
  @Test
  public void testEmptyShapesInfo() {
    examples();
    List<String[]> list = new ArrayList<>();
    assertEquals(list, s.getCommands());
  }

  //getting the current command of a shape with no command
  @Test(expected = IllegalStateException.class)
  public void testCurrCommandEmpty() {
    examples();
    s.getCurrCommand();
  }

  //getting the current command of a shape with no current command
  @Test(expected = IllegalStateException.class)
  public void testCurrCommandNotSet() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.getCurrCommand();
  }

  //getting the current command of a shape with a current command
  @Test
  public void testCurrentCommand() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.updateShape(1);
    assertEquals(s2.getCurrCommand(), com);
  }

  //removing null command
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullCommand() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.removeCommand(null);
  }

  //remove command that does not exist
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWrongCommand() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    ICommands com2 = new Command("s2", new ChangeTime(1, 12), new Color(2, 24, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.removeCommand(com2);
  }

  //remove command with a different shape name
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWrongName() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    ICommands com2 = new Command("s3", new ChangeTime(1, 12), new Color(2, 24, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.removeCommand(com2);
  }

  //tests removing valid command
  @Test
  public void testRemoveCommand() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.removeCommand(com);
    assertEquals(new ArrayList<ICommands>(), s2.getCommands());
  }

  //tests adding a null command
  @Test(expected = IllegalArgumentException.class)
  public void testAddNullCommand() {
    examples();
    s2.addCommand(null);
  }

  //tests adding command that already exists
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandAlreadyExists() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    s2.addCommand(com);
  }

  //tests adding a command that overlaps with an existing command
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandOverlap() {
    examples();
    ICommands com = new Command("s3", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com);
    ICommands com2 = new Command("s3", new ChangeTime(4, 56), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com);
    s3.addCommand(com2);
  }

  //tests adding command that does not overlap correctly with command right next to it
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandOverlapAdjacent() {
    examples();
    ICommands com = new Command("s3", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com);
    ICommands com2 = new Command("s3", new ChangeTime(12, 56), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(45, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com2);
  }

  //tests adding a command that has a different shape name
  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandWrongName() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com);
  }

  //tests adding a good command
  @Test
  public void testAddCommandGood() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(1, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    List<ICommands> list = new ArrayList<>();
    list.add(com);
    assertEquals(list, s2.getCommands());
  }

  //comparing ticks of a shape with no commands
  @Test
  public void testComapreTickNoCommands() {
    examples();
    assertFalse(s3.compareTick(5));
  }

  //comparing tick of a shape that has not begun yet
  @Test
  public void testComapreTickNotStarted() {
    examples();
    ICommands com = new Command("s1", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s.addCommand(com);
    assertFalse(s.compareTick(2));
  }

  //comparing tick of a shape with multiple commands
  @Test
  public void testComapreTickMultCommands() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    ICommands com2 = new Command("s2", new ChangeTime(3, 3), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(23, 45),
        new TotalSize(34, 34), new TotalSize(34, 34));
    s2.addCommand(com);
    s2.addCommand(com2);
    assertTrue(s2.compareTick(3));
  }

  //comparing tick of a shape with a good command
  @Test
  public void testComapreTickGoodCommands() {
    examples();
    ICommands com = new Command("s1", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s.addCommand(com);
    assertTrue(s.compareTick(10));
  }

  //comparing a tick of a shape with a last tick that has already passed
  @Test
  public void testComapreEnded() {
    examples();
    ICommands com = new Command("s1", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s.addCommand(com);
    assertFalse(s.compareTick(22));
  }


  //getting very last tick of a shape with no commands
  @Test(expected = IllegalArgumentException.class)
  public void testVeryLastNoCommands() {
    examples();
    s3.getVeryLastTick();
  }

  //getting very last tick of a shape
  @Test
  public void testVeryLastCommands() {
    examples();
    ICommands com = new Command("s3", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com);
    assertEquals(12, s3.getVeryLastTick());
  }

  //checking gaps of shapes with no gaps in commands
  @Test
  public void testCheckGapsNone() {
    examples();
    ICommands com = new Command("s3", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s3.addCommand(com);
    ICommands com1 = new Command("s3", new ChangeTime(12, 24), new Color(2, 23, 45),
        new Color(2, 233, 45), new Position(45, 234), new Position(45, 24),
        new TotalSize(34, 345), new TotalSize(34, 33));
    s3.addCommand(com1);
    s3.checkGaps();
    assertEquals(2, s3.getCommands().size());
  }

  //checking gaps of shapes with gaps in commands
  @Test
  public void testCheckGaps() {
    examples();
    ICommands com = new Command("s2", new ChangeTime(3, 12), new Color(2, 23, 45),
        new Color(2, 23, 45), new Position(23, 45), new Position(45, 234),
        new TotalSize(34, 34), new TotalSize(34, 345));
    s2.addCommand(com);
    ICommands com1 = new Command("s2", new ChangeTime(20, 24), new Color(2, 23, 45),
        new Color(2, 233, 45), new Position(23, 234), new Position(45, 24),
        new TotalSize(34, 345), new TotalSize(34, 33));
    s2.addCommand(com1);
    s2.checkGaps();
    assertEquals(3, s2.getCommands().size());
  }
}