package cs3500.animator.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents the total dimensions from top to bottom and left to right of a shape object.
 */
public class TotalSize {

  //INVARIANT: greater than 0
  private double totalHeight;
  //INVARIANT: greater than 0
  private double totalWidth;

  /**
   * Constructs a TotalSize with the given dimensions.
   *
   * @param h represents the total height from top to bottom
   * @param w represents the total width from left to right
   */
  public TotalSize(double w, double h) {
    checkHW(w, h);
    this.totalHeight = h;
    this.totalWidth = w;
  }

  /**
   * Constructs a TotalSize with the 1st and second indexes of a list. the x should be index 0, y
   * should be index 1
   *
   * @param posList list of string positions to be made
   */
  public TotalSize(List<String> posList) {
    if (posList.size() != 2) {
      throw new IllegalArgumentException("Position only takes lists of length two");
    }
    checkHW(Integer.parseInt(posList.get(0)), Integer.parseInt(posList.get(1)));
    this.setTotalHeight(Integer.parseInt(posList.get(1)));
    this.setTotalWidth(Integer.parseInt(posList.get(0)));
  }

  /**
   * Updates this dimension to be the given h and w values.
   *
   * @param h represents the h value that will be this h value
   * @param w represents the w value that will be this w value
   */
  public void updateDimensions(double w, double h) {
    checkHW(w, h);
    this.setTotalHeight(h);
    this.setTotalWidth(w);
  }

  /**
   * getter for the totalheight field.
   *
   * @return the totalheight of this totalsize.
   */
  public double getTotalHeight() {
    return this.totalHeight;
  }

  /**
   * getter for the totalWidth field.
   *
   * @return the totalWidth of this totalSize.
   */
  public double getTotalWidth() {
    return this.totalWidth;
  }

  /**
   * Updates this totalsize to have the given height.
   *
   * @param h represents the height this totalsize will be changed to.
   */
  public void setTotalHeight(double h) {
    if (h < 0) {
      throw new IllegalArgumentException("height cannot be negative");
    }
    this.totalHeight = h;
  }

  /**
   * Updates this totalsize to have the given width.
   *
   * @param w represents the width this totalsize will be changed to.
   */
  public void setTotalWidth(double w) {
    if (w < 0) {
      throw new IllegalArgumentException("width cannot be negative");
    }
    this.totalWidth = w;
  }

  /**
   * Checks if h or w is invalid.
   *
   * @param h represents the height value examined
   * @param w represents the width value examined
   * @throws IllegalArgumentException if h or w < 0
   */
  protected void checkHW(double w, double h) throws IllegalArgumentException {
    if (h < 0 || w < 0) {
      throw new IllegalArgumentException("Cant have negative height or width");
    }
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof TotalSize)) {
      return false;
    }

    TotalSize that = (TotalSize) a;

    return ((Math.abs(this.totalHeight - that.totalHeight) < 0.01)
        && (Math.abs(this.totalWidth - that.totalWidth) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.totalHeight, this.totalWidth);
  }


  @Override
  public String toString() {
    return (int) this.totalWidth + " " + (int) this.totalHeight;
  }
}