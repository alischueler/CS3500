import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.TotalSize;
import org.junit.Test;

/**
 * a class to test the TotalSize class.
 */
public class TotalSizeTest {

  //tests the correct height is returned
  @Test
  public void testCorrectH() {
    TotalSize ts = new TotalSize(0, 678);
    assertEquals(0, ts.getTotalHeight(), 0.01);
  }

  //tests the correct width is returned
  @Test
  public void testCorrectW() {
    TotalSize ts = new TotalSize(45, 0);
    assertEquals(0, ts.getTotalWidth(), 0.01);
  }

  //tests the correct height is returned when height is updated
  @Test
  public void testCorrectHUpdateH() {
    TotalSize ts = new TotalSize(45, 678);
    ts.setTotalHeight(74.2);
    assertEquals(74.2, ts.getTotalHeight(), 0.01);
  }

  //tests the correct width is returned when width is updated
  @Test
  public void testCorrectWUpdateW() {
    TotalSize ts = new TotalSize(45, 678);
    ts.setTotalWidth(133.5);
    assertEquals(133.5, ts.getTotalWidth(), 0.01);
  }

  //tests the correct height is returned when width is updated
  @Test
  public void testCorrectHUpdateW() {
    TotalSize ts = new TotalSize(45, 678);
    ts.setTotalWidth(133.5);
    assertEquals(45, ts.getTotalHeight(), 0.01);
  }

  //tests the correct width is returned when height is updated
  @Test
  public void testCorrectWUpdateH() {
    TotalSize ts = new TotalSize(45, 678);
    ts.setTotalHeight(46464);
    assertEquals(678, ts.getTotalWidth(), 0.01);
  }

  //tests correct height is returned when total size is updates
  @Test
  public void testCorrectHUpdateTS() {
    TotalSize ts = new TotalSize(45, 678);
    ts.updateDimensions(46464, 7363.9);
    assertEquals(46464, ts.getTotalHeight(), 0.01);
  }

  //tests correct width is returned when total size is updated
  @Test
  public void testCorrectWUpdateTS() {
    TotalSize ts = new TotalSize(45, 678);
    ts.updateDimensions(46464, 7363.9);
    assertEquals(7363.9, ts.getTotalWidth(), 0.01);
  }

  //tests the the height cannot be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testHLess0() {
    new TotalSize(-45, 678);
  }

  //tests that the width cannot be less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testWLess0() {
    new TotalSize(45, -678);
  }

  //tests that the height cannot be set to less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testHSetLess0() {
    TotalSize ts = new TotalSize(45, 678);
    ts.setTotalHeight(-.2);
  }

  //tests that the width cannot be set to less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testWSetLess0() {
    TotalSize ts = new TotalSize(45, -36363.4);
    ts.setTotalWidth(200.5);
  }

  //tests that the dimensions cannot be updated to height less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateHLess0() {
    TotalSize ts = new TotalSize(45, 678);
    ts.updateDimensions(-353, 200.5);
  }

  //tests that the dimensions cannot be updated to width less than 0
  @Test(expected = IllegalArgumentException.class)
  public void testUpdateWLess0() {
    TotalSize ts = new TotalSize(45, 678);
    ts.updateDimensions(353, -200.5);
  }

  //tests that two sizes are equal
  @Test
  public void testTwoTSEqual() {
    TotalSize ts = new TotalSize(45, 678);
    TotalSize ts2 = new TotalSize(45, 678);
    assertTrue(ts.equals(ts2));
  }

  //tests that two sizes are not equal
  @Test
  public void testTwoTSNotEqual() {
    TotalSize ts = new TotalSize(45, 678);
    TotalSize ts2 = new TotalSize(46, 366);
    assertFalse(ts.equals(ts2));
  }
}