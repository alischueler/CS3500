package model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a color with r, g, b values.
 */
public class Color {

  //INVARIANT: will not be less than 0 or greater than 255
  private double red;
  //INVARIANT: will not be less than 0 or greater than 255
  private double blue;
  //INVARIANT: will not be less than 0 or greater than 255
  private double green;

  /**
   * Constructs a color.
   *
   * @param r represents the red hue of the color.
   * @param b represents the blue hue of the color;
   * @param g represents the green hue of the color;
   */
  public Color(double r, double b, double g) {
    checkRGB(r, g, b);
    this.red = r;
    this.blue = b;
    this.green = g;
  }

  /**
   * a constructor for a color that takes in a list of Strings list must be index 0: red, index 1:
   * blue, index 2: green.
   *
   * @param colList list of strings for the color values
   */
  public Color(List<String> colList) {
    if (colList.size() != 3) {
      throw new IllegalArgumentException("Color only takes lists of length three");
    }
    double r = Integer.parseInt(colList.get(0));
    double b = Integer.parseInt(colList.get(1));
    double g = Integer.parseInt(colList.get(2));
    checkRGB(r, g, b);
    this.red = r;
    this.blue = b;
    this.green = g;
  }


  /**
   * Updates this color with the given parameters.
   *
   * @param r represents the r value to be updated
   * @param g represents the g value to be updated
   * @param b represents the b value to be updated
   */
  public void updateColor(double r, double g, double b) {
    checkRGB(r, g, b);
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  /**
   * getter for the red field.
   *
   * @return the r value of this color.
   */
  public double getRed() {
    return this.red;
  }

  /**
   * getter for the green field.
   *
   * @return the g value of this color.
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * getter for the blue field.
   *
   * @return the b value of this color.
   */
  public double getBlue() {
    return this.blue;
  }

  /**
   * Checks the r, g,b values and throws an IllegalArgument if any are invalid.
   *
   * @param r represents the r value to be examined.
   * @param g represents the g value to be examined
   * @param b represents the b value to be examined
   * @throws IllegalArgumentException if the r, g, b values are < 0 or > 255
   */
  private void checkRGB(double r, double g, double b) {
    if (r > 255 | b > 255 | g > 255) {
      throw new IllegalArgumentException("Over 255");
    }
    if (r < 0 | b < 0 | g < 0) {
      System.out.println("" + r + g + b);
      throw new IllegalArgumentException("Under 0");
    }
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Color)) {
      return false;
    }

    Color that = (Color) a;

    return ((Math.abs(this.red - that.red) < 0.01)
        && (Math.abs(this.green - that.green) < 0.01)
        && (Math.abs(this.blue - that.blue) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }

  @Override
  public String toString() {
    return "R: " + this.red + " G: " + this.green + " B: " + this.blue;
  }
}
