package cs3500.animator.model;

/**
 * An interface to represent positions on a 2 dimensional plane.
 */
public interface IPosition {

  /**
   * Updates this position to have the given x and y values.
   *
   * @param x represents the new X value of this position.
   * @param y represents the new Y value of this position.
   */
  void updatePos(double x, double y);

  /**
   * getter for the x field.
   *
   * @return the x value of this position.
   */
  double getX();

  /**
   * getter for the y field.
   *
   * @return the y value of this position.
   */
  double getY();

  /**
   * Updates the x value of this position to the given x value.
   *
   * @param newX represents the new X value of this Position.
   */
  void setX(double newX);

  /**
   * Updates the y value of this position to the given x value.
   *
   * @param newY represents the new Y value of this position.
   */
  void setY(double newY);
}
