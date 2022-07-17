package cs3500.animator.model;

/**
 * a enum to represent the two shape types shape.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), ELLIPSE("ellipse"), PLUS("plus");

  private final String type;

  /**
   * constructor for the ShapeType.
   * @param type the type of sgape
   */
  ShapeType(String type) {
    this.type = type;
  }

  /**
   * getter for the type of the shape.
   * @return type of shape
   */
  public String getType() {
    return this.type;
  }

}
