import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Color;
import org.junit.Test;

/**
 * a class to test the color object.
 */
public class ColorTest {

  //tests correct r value is returned
  @Test
  public void testCorrectRValue() {
    Color c = new Color(134.8, 24, 226.3);
    assertEquals(134.8, c.getRed(), .01);
  }

  //tests correct b value is returned
  @Test
  public void testCorrectGValue() {
    Color c = new Color(134.8, 24, 0);
    assertEquals(0, c.getGreen(), .01);
  }

  //tests correct g value is returned
  @Test
  public void testCorrectBValue() {
    Color c = new Color(134.8, 255, 226.3);
    assertEquals(255, c.getBlue(), .01);
  }

  //tests correct r value is returned when r value is updated
  @Test
  public void testCorrectRValueUpdated() {
    Color c = new Color(134.8, 24, 226.3);
    c.updateColor(235, 226.3, 24);
    assertEquals(235, c.getRed(), .01);
  }

  //tests correct g value is returned when g value is returned
  @Test
  public void testCorrectGValueUpdated() {
    Color c = new Color(134.8, 24, 226.3);
    c.updateColor(134.8, 24.2, 24);
    assertEquals(24.2, c.getGreen(), .01);
  }

  //tests correct b value is returned when b value is returned
  @Test
  public void testCorrectBValueUpdated() {
    Color c = new Color(134.8, 24, 226.3);
    c.updateColor(134.8, 226.3, 245.2);
    assertEquals(245.2, c.getBlue(), .01);
  }

  //tests correct r value is returned when pos is updated
  @Test
  public void testCorrectRValueUpdatedPos() {
    Color c = new Color(134.8, 24, 226.3);
    c.updateColor(234.8, 25, 99.9);
    assertEquals(234.8, c.getRed(), .01);
  }

  //tests correct b value is returned when pos is updated
  @Test
  public void testCorrectBValueUpdatedPos() {
    Color c = new Color(134.8, 24, 226.3);
    c.updateColor(234.8, 25, 99.9);
    assertEquals(99.9, c.getBlue(), .01);
  }

  //tests correct g value is returned when pos is updated
  @Test
  public void testCorrectGValueUpdatedPos() {
    Color c = new Color(134.8, 24, 226.3);
    c.updateColor(234.8, 25, 99.9);
    assertEquals(25, c.getGreen(), .01);
  }

  //tests that the r value cannot be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testRLess0() {
    new Color(-356, 24, 226.3);
  }

  //tests that the g value cannot be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testGLess0() {
    new Color(156, 24, -.1);
  }

  //tests that the b value cannot be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testBLess0() {
    new Color(156, -2004, .1);
  }

  //tests that the r value cannot be greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testRGreater255() {
    new Color(255.3, 104, .1);
  }

  //tests that the g value cannot be greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testGGreater255() {
    new Color(254.1, 104, 266);
  }

  //tests that the b value cannot be greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testBGreater255() {
    new Color(254.1, 1004, 26);
  }

  //tests that the r value cannot be set to a value less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testRUpdateLess0() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(-2, 26, 104);
  }

  //tests that the g value cannot be set to a value less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testGUpdateLess0() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(254.1, -255, 104);
  }

  //tests that the b value cannot be set to a value less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testBUpdateLess0() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(254.1, 26, -104);
  }

  //tests that the r value cannot be set to a greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testRUpdateGreater255() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(255.1, 26, 104);
  }

  //tests that the g value cannot be set to a greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testGUpdateGreater255() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(254.1, 266, 104);
  }

  //tests that the b value cannot be set to a greater than 255
  @Test(expected = IllegalArgumentException.class)
  public void testBUpdateGreater255() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(254.1, 26, 1004);
  }

  //tests that two colors are equal to each other
  @Test
  public void testTwoColorEqual() {
    Color c = new Color(254.1, 104, 26);
    Color c2 = new Color(254.1, 104, 26);
    assertTrue(c.equals(c2));
  }

  //tests that two colors are not equal to each other
  @Test
  public void testTwoColorNotEqual() {
    Color c = new Color(254.1, 104, 26);
    Color c2 = new Color(254.5, 104, 26);
    assertFalse(c.equals(c2));
  }

  //tests toString returns correct String of colors
  @Test
  public void testToStringColor() {
    Color c = new Color(254.1, 104, 26);
    assertEquals("R: 254.1 G: 26.0 B: 104.0", c.toString());
  }

  //tests toString returns correct String of Colors after being updated
  @Test
  public void testToStringColorUpdated() {
    Color c = new Color(254.1, 104, 26);
    c.updateColor(200.0, 0, 46.8);
    assertEquals("R: 200.0 G: 0.0 B: 46.8", c.toString());
  }

}