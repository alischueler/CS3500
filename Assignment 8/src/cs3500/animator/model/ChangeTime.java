package cs3500.animator.model;

import java.util.Objects;

/**
 * a class to hold information on start and end ticks .
 */
public class ChangeTime implements IChangeTime {

  private final int start;
  private final int end;

  /**
   * a constructor for the ChangeTime object.
   *
   * @param start start tick
   * @param end   end tick
   */
  public ChangeTime(int start, int end) {
    checkCorrect(start, end);
    this.start = start;
    this.end = end;
  }

  /**
   * ensures the ticks are correct.
   *
   * @param start start tick
   * @param end   end tick
   */
  private void checkCorrect(int start, int end) {
    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("start or end is less than 0");
    }
    if (start > end) {
      throw new IllegalArgumentException("start is greater than end");
    }
  }

  /**
   * getter for the start tick.
   *
   * @return start tick
   */

  public int getStart() {
    return this.start;
  }

  /**
   * getter for the end tick.
   *
   * @return end tick
   */
  public int getEnd() {
    return this.end;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof ChangeTime)) {
      return false;
    }

    ChangeTime that = (ChangeTime) a;

    return (Math.abs(this.start - that.start) < 0.01) &&
        (Math.abs(this.end - that.end) < 0.01);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append(getStart());
    out.append(" ");
    out.append(getEnd());
    return out.toString();
  }

}
