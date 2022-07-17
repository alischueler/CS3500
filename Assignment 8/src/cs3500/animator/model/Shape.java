package cs3500.animator.model;

import java.util.List;

/**
 * Represents a type of shape to be mutated.
 */
public interface Shape {

  /**
   * getter for the cureent command.
   *
   * @return the current command of this shape.
   * @throws IllegalArgumentException if there is no current command of this shape
   */
  ICommands getCurrCommand() throws IllegalArgumentException;

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
  ShapeType getShapeType();

  /**
   * getter for the current color field.
   *
   * @return the current color of this shape.
   * @throws IllegalArgumentException if current color has not been initialized
   */
  IColor getCurrColor() throws IllegalArgumentException;

  /**
   * getter for the current position field.
   *
   * @return the current position of this shape.
   * @throws IllegalArgumentException if current position has not been initialized
   */
  IPosition getCurrentPosition() throws IllegalArgumentException;

  /**
   * getter for the current dimensions field.
   *
   * @return the current dimensions of this shape.
   * @throws IllegalArgumentException if current size has not been initialized
   */
  ITotalSize getCurrentDimension() throws IllegalArgumentException;


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


  /**
   * gets all commands.
   *
   * @return a list of all the shapes commands, in order.
   * @throws IllegalArgumentException if commands is null
   */
  List<ICommands> getCommands() throws IllegalArgumentException;

  /**
   * removes the given command from the shapes list of commands.
   *
   * @param com the command to be removed
   * @throws IllegalArgumentException if commands is null or command does not exist
   */
  void removeCommand(ICommands com) throws IllegalArgumentException;

  /**
   * adds the given command to the shapes list of commands.
   *
   * @param com the command to be added to this shape
   * @throws IllegalArgumentException if commands is null or command is not for this shape
   */
  void addCommand(ICommands com) throws IllegalArgumentException;

  /**
   * determines if the given tick is part of any command in this shape.
   *
   * @param tick the tick to be compared.
   * @return true if the tick is part of a command, false if otherwise
   */
  boolean compareTick(int tick);

  /**
   * gets the very last tick of this shape.
   *
   * @return the very last tick of this shape.
   */
  int getVeryLastTick();

  /**
   * checks for any gaps in this shapes list of commands.
   */
  void checkGaps();


  /**
   * updates this shape have the values of the command containing the given tick.
   *
   * @param tick the tick to use to update this shape
   */
  void updateShape(int tick);


}
