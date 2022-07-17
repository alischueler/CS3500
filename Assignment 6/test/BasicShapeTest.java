

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.BasicShape;
import model.Color;
import model.Position;
import model.Shape;
import model.TotalSize;
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
    s = new BasicShape("Shape 1", "rectangle", 2, 21,
        new Position(5, 6), new Position(80, 200), new Color(56, 78, 123),
        new Color(56, 78, 123), new TotalSize(2, 5), new TotalSize(2, 5));
    duplicateS = new BasicShape(s.getShapeName(), s.getShapeType(), s.getStartTick(),
        s.getEndTick(),
        s.getStartPosition(), s.getEndPosition(), s.getStartColor(), s.getEndColor(),
        s.getStartDimension(), s.getEndDimension());
    s2 = new BasicShape("Shape 2", "oval", 2, 21, new Position(500, 61),
        new Position(80, 200), new Color(56, 78, 123),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(2, 5));
    s3 = new BasicShape("Shape 3", "oval", 2, 21, new Position(500, 61),
        new Position(80, 200), new Color(56, 78, 123),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
    noCurrValues = new BasicShape("Shape 11", "oval");
  }


  //tests correct startTick
  @Test
  public void testCorrectStartTick() {
    examples();
    assertEquals(2, s.getStartTick());
  }

  //tests correct endTick
  @Test
  public void testCorrectEndTick() {
    examples();
    assertEquals(21, s.getEndTick());
  }

  //tests correct startPos
  @Test
  public void testCorrectStartPos() {
    examples();
    assertEquals(new Position(5, 6), s.getStartPosition());
  }

  //tests that startpos and current pos are equal because just initialized
  @Test
  public void testStartPosIsCurrentPos() {
    examples();
    assertTrue(s.getStartPosition().equals(s.getCurrentPosition()));
  }

  //tests that startpos and current pos are not equal
  @Test
  public void testEndPosIsNotCurrentPos() {
    examples();
    assertFalse(s.getEndPosition().equals(s.getCurrentPosition()));
  }

  //tests that the correct end pos is returned
  @Test
  public void testCorrectEndPos() {
    examples();
    assertEquals(new Position(80, 200), s.getEndPosition());
  }

  //tests that the  starting color is correct
  @Test
  public void testCorrectStartCol() {
    examples();
    assertEquals(new Color(56, 78, 123), s.getStartColor());
  }

  //tests that the ending color is correct
  @Test
  public void testCorrectEndCol() {
    examples();
    assertEquals(new Color(58, 78.8, 120.2), s2.getEndColor());
  }

  //tests that the starting and current color are equal
  @Test
  public void testStartColandCurrCol() {
    examples();
    assertEquals(s2.getCurrColor(), s2.getStartColor());
  }

  //tests that starting color and ending color are not equal
  @Test
  public void testEndColaandCurrCol() {
    examples();
    assertFalse(s2.getCurrColor().equals(s2.getEndColor()));
  }

  //tests that the starting dimensions are correct
  @Test
  public void testCorrectStartDimension() {
    examples();
    assertEquals(new TotalSize(2, 5), s3.getStartDimension());
  }

  //tests that the ending dimensions are correct
  @Test
  public void testCorrectEndDimension() {
    examples();
    assertEquals(new TotalSize(200, 52), s3.getEndDimension());
  }

  //tests that the starting and current dimensions are the same
  @Test
  public void testStartandCurrDimension() {
    examples();
    assertEquals(s3.getCurrentDimension(), s3.getStartDimension());
  }

  //tests that the ending and current dimensions are not the same
  @Test
  public void testStartandCurrDimensionNotSame() {
    examples();
    assertFalse(s3.getCurrentDimension().equals(s3.getEndDimension()));
  }

  //tests changing the start time
  @Test
  public void testChangingStartTime() {
    examples();
    s3.setStartTime(30);
    s3.setEndTime(31);
    assertEquals(30, s3.getStartTick());
  }

  //tests changing the end time
  @Test
  public void testChangingEndTime() {
    examples();
    s3.setStartTime(30);
    s3.setEndTime(31);
    assertEquals(31, s3.getEndTick());
  }

  //tests changing the start pos
  @Test
  public void testChangingStartPos() {
    examples();
    s3.setStartPosition(new Position(78, 90.3));
    assertEquals(new Position(78, 90.3), s3.getStartPosition());
  }

  //tests changing the end pos
  @Test
  public void testChangingEndPos() {
    examples();
    s3.setEndPosition(new Position(78.9, 900.3));
    assertEquals(new Position(78.9, 900.3), s3.getEndPosition());
  }

  //tests changing the start color
  @Test
  public void testChangingStartCol() {
    examples();
    s3.setStartColor(new Color(45.2, 89, 23.4));
    assertEquals(new Color(45.2, 89, 23.4), s3.getStartColor());
  }

  //tests changing the end color
  @Test
  public void testChangingEndCol() {
    examples();
    s3.setEndColor(new Color(115.2, 189, 223.4));
    assertTrue(new Color(115.2, 189, 223.4).equals(s3.getEndColor()));
  }

  //tests changing the start dimensions
  @Test
  public void testChangingStartDem() {
    examples();
    s3.setStartSize(new TotalSize(115.2, 189));
    assertEquals(new TotalSize(115.2, 189), s3.getStartDimension());
  }

  //tests changing the end dimensions
  @Test
  public void testChangingEndDem() {
    examples();
    s3.setEndSize(new TotalSize(2.3, 100));
    assertEquals(new TotalSize(2.3, 100), s3.getEndDimension());
  }

  //tests changing the current pos
  @Test
  public void testChangingCurrPos() {
    examples();
    s3.updateCurrPos(345, 234.1);
    assertEquals(new Position(345, 234.1), s3.getCurrentPosition());
  }

  //tests that after changing current pos, not equal to start pos
  @Test
  public void testChangingCurrPosNotStartPos() {
    examples();
    s3.updateCurrPos(345, 234.1);
    assertFalse(s3.getCurrentPosition().equals(s3.getStartPosition()));
  }

  //tests changing the current color
  @Test
  public void testChangingCurrCol() {
    examples();
    s3.updateCurrColor(34, 234.1, 1);
    assertTrue(new Color(34, 1, 234.1).equals(s3.getCurrColor()));
  }

  //tests that after changing current color, not equal to start color
  @Test
  public void testChangingCurrColNotEqualStartCol() {
    examples();
    s3.updateCurrColor(34, 234.1, 1);
    assertFalse(new Color(34, 234.1, 1).equals(s3.getStartColor()));
  }

  //tests changing the current dimensions
  @Test
  public void testChangingCurrDimensions() {
    examples();
    s3.updateCurrSize(34, 234.1);
    assertEquals(new TotalSize(34, 234.1), s3.getCurrentDimension());
  }

  //tests that after changing current dimensions, not equal to start dimensions
  @Test
  public void testChangingCurrDimensionsNotEqualStartDim() {
    examples();
    s3.updateCurrSize(34, 234.1);
    assertFalse(s3.getCurrentDimension().equals(s3.getStartDimension()));
  }

  //tests that a shape cannot be made with start tick less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testShapeStartTickLess0() {
    new BasicShape("Shape 4", "rectangle", -1, 21, new Position(500, 61),
        new Position(80, 200), new Color(56, 78, 123),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with end tick less than start tick
  @Test(expected = IllegalArgumentException.class)
  public void testShapeEndTickLessStart() {
    new BasicShape("Shape 5", "oval", 2, 1, new Position(500, 61),
        new Position(80, 200), new Color(56, 78, 123),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with end tick less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testShapeEndTickLess0() {
    new BasicShape("Shape 5", "oval", 21, -200, new Position(500, 61),
        new Position(80, 200), new Color(56, 78, 123),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with start r, b, g, values less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testShapeStartColLess0() {
    new BasicShape("Shape 6", "rectangle", 21, 200,
        new Position(500, 61), new Position(80, 200), new Color(-2, .5, -20),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with end r, b, g values less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testShapeEndColLess0() {
    new BasicShape("Shape 7", "oval", 21, 200,
        new Position(500, 61), new Position(80, 200), new Color(2, .5, 20),
        new Color(58, -78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with start r, b, g, values greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testShapeStartColGreater255() {
    new BasicShape("Shape 8", "rectangle", 21, 200,
        new Position(500, 61), new Position(80, 200), new Color(2, .5, 256),
        new Color(58, 78.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with end r, b, g values greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testShapeEndColGreater255() {
    new BasicShape("Shape 9", "rectangle", 21, 200,
        new Position(500, 61), new Position(80, 200), new Color(2, .5, 26),
        new Color(58, 478.8, 120.2), new TotalSize(2, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with start height less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testShapeStartHeightLess0() {
    new BasicShape("Shape 10", "rectangle", 21, 200,
        new Position(500, 61), new Position(80, 200), new Color(2, .5, 26),
        new Color(58, 178.8, 120.2), new TotalSize(-3, 5),
        new TotalSize(200, 52));
  }

  //tests that a shape cannot be made with start width less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testShapeStartWidthLess0() {
    new BasicShape("Shape 10", "rectangle", 21, 200,
        new Position(500, 61), new Position(80, 200), new Color(2, .5, 26),
        new Color(58, 78.8, 120.2), new TotalSize(2, -456),
        new TotalSize(200, 52));
  }

  //tests that the start tick cannot be updated to be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeStartTickLess0() {
    examples();
    s3.setStartTime(-43);
  }

  //tests that the end tick cannot be updated to be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeEndTickLess0() {
    examples();
    s3.setEndTime(-43);
  }

  //tests that the start color cannot be updated to have r, b, g, values less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeStartColLess0() {
    s3.setStartColor(new Color(67, -45.6, 68));
  }

  //tests that the start color cannot be updated to have r, b, g values greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeStartColGreater255() {
    s2.setStartColor(new Color(67, 45.6, 668));
  }

  //tests that the end color cannot be updated to have r, b, g vales less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeEndColLess0() {
    s2.setEndColor(new Color(-67, 45.6, 168));
  }

  //tests that the end color cannot be updated to have r, b, g, values greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeEndColGreater255() {
    s.setEndColor(new Color(67, 745.6, 68));
  }

  //tests that a shape cannot be updated to have start height less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeStartHeightLess0() {
    s.setStartSize(new TotalSize(-5, 467));
  }

  //tests that a shape cannot be updated to have start width less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeStartWidthLess0() {
    s3.setStartSize(new TotalSize(5, -467));
  }

  //tests that a shape cannot be updated to have end height less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeEndHeightLess0() {
    s3.setEndSize(new TotalSize(-5, 467));
  }

  //tests that a shape cannot be updated to have end width less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeEndWidthLess0() {
    s2.setEndSize(new TotalSize(5, -467));
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
    s2.updateCurrColor(.5, -.4, 56);
  }

  //tests that the current color cannot be updated to have r, b, g values greater than 250
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateShapeCurrColorGreater255() {
    examples();
    s.updateCurrColor(255.2, .4, 56);
  }

  //tests to make sure a Col cannot be returned if it is null
  @Test(expected = IllegalStateException.class)
  public void testNullStartCol() {
    examples();
    noCurrValues.getStartColor();
  }

  //tests to make sure a Pos cannot be returned if it is null
  @Test(expected = IllegalStateException.class)
  public void testNullStartPos() {
    examples();
    noCurrValues.getStartPosition();
  }

  //tests to make sure a TotalSize cannot be returned if it is null
  @Test(expected = IllegalStateException.class)
  public void testNullStartDimensions() {
    examples();
    noCurrValues.getStartDimension();
  }

  //tests to make sure the correct name of a shape is returned
  @Test
  public void testCorrectName() {
    examples();
    assertEquals("Shape 1", s.getShapeName());
  }

  //tests to make sure the correct shape type of a rectangle is returned
  @Test
  public void testCorrectShapeRectangle() {
    examples();
    assertEquals("rectangle", s.getShapeType());
  }

  //tests to make sure the correct shape type of an oval is returned
  @Test
  public void testCorrectShapeOval() {
    examples();
    assertEquals("oval", s3.getShapeType());
  }

  //tests shape 1s toString
  @Test
  public void testStoString() {
    examples();
    assertEquals("Shape 1 rectangle X: 5.0 Y: 6.0 Height: 2.0 Width: 5.0 R: 56.0 G: 123.0 "
        + "B: 78.0 2 21 X: 5.0 Y: 6.0 X: 80.0 Y: 200.0 R: 56.0 G: 123.0 B: 78.0 R: 56.0 G: "
        + "123.0 B: 78.0 "
        + "Height: 2.0 Width: 5.0 Height: 2.0 Width: 5.0", s.toString());
  }

  //tests shape 2s to String
  @Test
  public void testS2toString() {
    examples();
    assertEquals("Shape 2 oval X: 500.0 Y: 61.0 Height: 2.0 Width: 5.0 R: 56.0 G: 123.0 "
        + "B: 78.0 2 21 X: 500.0 Y: 61.0 X: 80.0 Y: 200.0 R: 56.0 G: 123.0 B: 78.0 R: 58.0 G: "
        + "120.2 B: 78.8 "
        + "Height: 2.0 Width: 5.0 Height: 2.0 Width: 5.0", s2.toString());
  }

  //tests shape 3s toString
  @Test
  public void testS3toString() {
    examples();
    assertEquals("Shape 3 oval X: 500.0 Y: 61.0 Height: 2.0 Width: 5.0 R: 56.0 G: 123.0 "
        + "B: 78.0 2 21 X: 500.0 Y: 61.0 X: 80.0 Y: 200.0 R: 56.0 G: 123.0 B: 78.0 R: 58.0 G: "
        + "120.2 B: 78.8 "
        + "Height: 2.0 Width: 5.0 Height: 200.0 Width: 52.0", s3.toString());
  }

  //tests that noCurrValues cannot make a toString
  @Test(expected = IllegalStateException.class)
  public void testNoCurrValuestoString() {
    examples();
    noCurrValues.toString();
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

}