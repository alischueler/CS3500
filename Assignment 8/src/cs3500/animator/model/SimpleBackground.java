package cs3500.animator.model;

/**
 * Creates a SimpleBackground which is an instance of TotalSize.
 */
public class SimpleBackground implements IBackground {

  private final int height;
  private final int width;
  private final int xBound;
  private final int yBound;

  /**
   * Constructs a SimpleBackground with the given height, width, shape, and color.
   *
   * @param h represents the height of this background.
   * @param w represents the width of this background.
   * @param x represents the leftmost x.
   * @param y represents the topmost y.
   */
  public SimpleBackground(int h, int w, int x, int y) {
    this.height = h;
    this.width = w;
    this.xBound = x;
    this.yBound = y;
  }

  public int getXBound() {
    return this.xBound;
  }

  public int getYBound() {
    return this.yBound;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append(Integer.toString(this.xBound) + " " + Integer.toString(this.yBound) + " "
        + Integer.toString(this.width) + " " + Integer.toString(this.height));
    String output = out.toString();
    return output;
  }
}
