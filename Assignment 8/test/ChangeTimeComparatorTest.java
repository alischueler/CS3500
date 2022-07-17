import static org.junit.Assert.assertEquals;

import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.ChangeTimeComparator;
import org.junit.Test;

/**
 * tests the ChangeTimeComparator.
 */
public class ChangeTimeComparatorTest {

  ChangeTime ct1;
  ChangeTime ct2;
  ChangeTime ct3;
  ChangeTime ct4;
  ChangeTime ct5;
  ChangeTimeComparator comparator;

  protected void examples() {
    ct1 = new ChangeTime(5, 20);
    ct2 = new ChangeTime(5, 17);
    ct3 = new ChangeTime(20, 23);
    ct4 = new ChangeTime(5, 20);
    ct5 = new ChangeTime(7, 23);
    comparator = new ChangeTimeComparator();
  }


  //tests comparing two change times with start equal and end equal
  @Test
  public void testComparatorEqual() {
    examples();
    assertEquals(0, comparator.compare(ct1, ct4));
  }

  //tests comparing two change time with start equal
  @Test
  public void testComparatorStartEqual() {
    examples();
    assertEquals(-1, comparator.compare(ct2, ct4));
  }

  //tests comparing start and end with end equal
  @Test
  public void testComparatorEndEqual() {
    examples();
    assertEquals(1, comparator.compare(ct3, ct5));
  }

  //tests comparing two change times with nothing equal
  @Test
  public void testComparatorNoEqual() {
    examples();
    assertEquals(1, comparator.compare(ct3, ct4));
  }
}