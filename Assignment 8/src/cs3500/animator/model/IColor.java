package cs3500.animator.model;

/**
 * An interface to represent Colors represented by r, g, b values.
 */
public interface IColor {

  /**
   * Updates this color with the given parameters.
   *
   * @param r represents the r value to be updated
   * @param g represents the g value to be updated
   * @param b represents the b value to be updated
   */
  void updateColor(double r, double g, double b);

  /**
   * getter for the red field.
   *
   * @return the r value of this color.
   */
  double getRed();

  /**
   * getter for the green field.
   *
   * @return the g value of this color.
   */
  double getGreen();

  /**
   * getter for the blue field.
   *
   * @return the b value of this color.
   */
  double getBlue();
}
