package cs3500.animator.model;

import java.util.Comparator;

/**
 * Comparator to compare tick times.
 */
public class ChangeTimeComparator implements Comparator<ChangeTime> {

  /**
   * comapres the goven tick times.
   * @param t1 start tick
   * @param t2 ens tick
   * @return the difference in tick times.
   */
  @Override
  public int compare(ChangeTime t1, ChangeTime t2) {
    if (t1.getStart() == t2.getStart()) {
      return Integer.compare(t1.getEnd(), t2.getEnd());
    } else {
      return Integer.compare(t1.getStart(), t2.getStart());
    }
  }
}
