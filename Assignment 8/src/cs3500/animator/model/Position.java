package cs3500.animator.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Position on the cartesian plane.
 */
public class Position implements IPosition {

  private double x;
  private double y;

  /**
   * Constructs an instanece of Position.
   *
   * @param x represents the x value of the new position.
   * @param y represents the y value of the new Position.
   */
  public Position(double x, double y) {
    this.setX(x);
    this.setY(y);
  }

  /**
   * a constructor that is passed a List. the x should be index 0, y should be index 1
   *
   * @param posList list of string positions to be made
   */
  public Position(List<String> posList) {
    if (posList.size() != 2) {
      throw new IllegalArgumentException("Position only takes lists of length two");
    }

    this.setX(Integer.parseInt(posList.get(0)));
    this.setY(Integer.parseInt(posList.get(1)));
  }

  /**
   * Updates this position to have the given x and y values.
   *
   * @param x represents the new X value of this position.
   * @param y represents the new Y value of this position.
   */
  public void updatePos(double x, double y) {
    this.setX(x);
    this.setY(y);
  }

  /**
   * getter for the x field.
   *
   * @return the x value of this position.
   */
  public double getX() {
    return this.x;
  }

  /**
   * getter for the y field.
   *
   * @return the y value of this position.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Updates the x value of this position to the given x value.
   *
   * @param newX represents the new X value of this Position.
   */
  public void setX(double newX) {
    this.x = newX;
  }

  /**
   * Updates the y value of this position to the given x value.
   *
   * @param newY represents the new Y value of this position.
   */
  public void setY(double newY) {
    this.y = newY;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position)) {
      return false;
    }

    Position that = (Position) a;

    return ((Math.abs(this.x - that.x) < 0.01)
        && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  @Override
  public String toString() {
    return (int) this.x + " " + (int) this.y;
  }
}
