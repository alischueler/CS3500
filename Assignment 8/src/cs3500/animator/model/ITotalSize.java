package cs3500.animator.model;

/**
 * Represents the total dimensions from top to bottom and left to right of a shape object.
 */
public interface ITotalSize {

  /**
   * Updates this dimension to be the given h and w values.
   *
   * @param h represents the h value that will be this h value
   * @param w represents the w value that will be this w value
   */
  void updateDimensions(double w, double h);

  /**
   * getter for the totalheight field.
   *
   * @return the totalheight of this totalsize.
   */
  double getTotalHeight();

  /**
   * getter for the totalWidth field.
   *
   * @return the totalWidth of this totalSize.
   */
  double getTotalWidth();

  /**
   * Updates this totalsize to have the given height.
   *
   * @param h represents the height this totalsize will be changed to.
   */
  void setTotalHeight(double h);

  /**
   * Updates this totalsize to have the given width.
   *
   * @param w represents the width this totalsize will be changed to.
   */
  void setTotalWidth(double w);

}
