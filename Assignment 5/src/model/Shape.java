package model;

/**
 * Represents a type of shape to be mutated.
 */
public interface Shape {

  /**
   * getter for the start tick field.
   *
   * @return the start tick of this shape's change.
   */
  int getStartTick();

  /**
   * getter for the end tick field.
   *
   * @return the end tick of this shape's change.
   */
  int getEndTick();

  /**
   * getter for the name field.
   *
   * @return the name of this shape.
   */
  String getShapeName();

  /**
   * getter for the shapeType field.
   *
   * @return the type of shape this shape is.
   */
  String getShapeType();

  /**
   * getter for the start color field.
   *
   * @return the start color of this shape.
   */
  Color getStartColor();

  /**
   * getter for the end color field.
   *
   * @return the end color of this shape.
   */
  Color getEndColor();

  /**
   * getter for the start position field.
   *
   * @return the start position of this shape.
   */
  Position getStartPosition();

  /**
   * getter for the end position field.
   *
   * @return the end position of this shape.
   */
  Position getEndPosition();

  /**
   * getter for the start dimensions field.
   *
   * @return the start dimensions of this shape.
   */
  TotalSize getStartDimension();

  /**
   * getter for the end dimensions field.
   *
   * @return the end dimensions of this shape.
   */
  TotalSize getEndDimension();

  /**
   * getter for the current color field.
   *
   * @return the current color of this shape.
   */
  Color getCurrColor();

  /**
   * getter for the current position field.
   *
   * @return the current position of this shape.
   */
  Position getCurrentPosition();

  /**
   * getter for the current dimensions field.
   *
   * @return the current dimensions of this shape.
   */
  TotalSize getCurrentDimension();

  /**
   * setter for the start time field.
   *
   * @param time updates the start time of this shape to the given int.
   */
  void setStartTime(int time);

  /**
   * setter for the end time field.
   *
   * @param time updates the end time of this shape to the given int.
   */
  void setEndTime(int time);

  /**
   * setter for the newEndP field.
   *
   * @param newEndP updates the end position of this shape to the given position.
   */
  void setEndPosition(Position newEndP);

  /**
   * setter for the stat position field.
   *
   * @param newStartP updates the start position of this shape to the given position.
   */
  void setStartPosition(Position newStartP);

  /**
   * setter for the start color field.
   *
   * @param c updates the start color of this shape to be the given color.
   */
  void setStartColor(Color c);

  /**
   * setter for the end position field.
   *
   * @param c updates the end color of this shape to be the given color.
   */
  void setEndColor(Color c);

  /**
   * setter for the start size field.
   *
   * @param size updates the starting dimensions of this shape to be the given totalsize.
   */
  void setStartSize(TotalSize size);

  /**
   * setter for the end size field.
   *
   * @param size updates the ending dimensions of this shape to be the given totalSize.
   */
  void setEndSize(TotalSize size);

  /**
   * updates the current position of this shape to the given x and y values.
   *
   * @param x represents the new X position of this shape.
   * @param y represents the new Y position of this shape.
   */
  void updateCurrPos(double x, double y);

  /**
   * Updates this shapes current color to have the given r, g, b values.
   *
   * @param r represents the new r value of this shapes current color.
   * @param g represents the new g value of this shapes current color.
   * @param b represents the new b value of this shapes current color.
   */
  void updateCurrColor(double r, double g, double b);

  /**
   * Updates this shapes current dimensions to have the given h and w values.
   *
   * @param h represents the new h value of this shapes current height.
   * @param w represents the new w value of this shapes current width.
   */
  void updateCurrSize(double h, double w);

}
