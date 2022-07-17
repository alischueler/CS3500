package model;

/**
 * Creates a SimpleBackground which is an instance of TotalSize.
 */
public class SimpleBackground extends TotalSize {
  private final String shape;
  private Color color;

  /**
   * Constructs a SimpleBackground with the given height, width, shape, and color.
   * @param h represents the height of this background.
   * @param w represents the width of this background.
   * @param s represents the shape of this background.
   * @param c represents the color of this background.
   */
  SimpleBackground(int h, int w, String s, Color c) {
    super(h, w);
    this.shape = s;
    this.color = c;
  }

  /**
   * getter for the color field.
   * @return the color of this simple background.
   */
  protected Color getColor() {
    return this.color;
  }

  /**
   * getter for the shape name.
   * @return the shape of this simplebackground
   */
  protected String getShape() {
    return this.shape;
  }

  /**
   * Updates the color of this background to have the given r, g, b values.
   * @param r represents the r value to change the color to
   * @param g represents the g value to change the color to
   * @param b represents the b value to change the color to
   */
  protected void setColor(int r, int g, int b) {
    this.color.updateColor(r, g, b);
  }
}
