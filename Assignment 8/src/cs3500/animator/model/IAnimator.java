package cs3500.animator.model;

import java.util.List;

/**
 * Represents a model for animators the model is controller by a tick. to increase the speed of the
 * animation you call the tick faster. K is the shape that the model implements.
 */
public interface IAnimator<K> {

  /**
   * a method to start the animation. the method takes in no arguments it simply creates an empty
   * map of K.
   *
   * @throws IllegalArgumentException if the commands passed are bad
   */
  void startAnimator() throws IllegalArgumentException;

  /**
   * adds a command to a a shape. the shape does not have to exist in the animation.
   *
   * @param command the command to be added to the animation (see javaDoc on startAnimation for how
   *                to format a command)
   * @throws IllegalArgumentException if the command is bad
   */
  void addCommand(ICommands command) throws IllegalArgumentException;

  /**
   * adds a shape to the list of shapes for this model of an animation.
   *
   * @param shape the shape to be added to the animation
   * @throws IllegalArgumentException if the shape already exists in the list
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Removes a given shape from the animation. removes all future moves as well.
   *
   * @param s the shape that is being removed
   * @throws IllegalArgumentException if the name cant be found
   */
  void removeShape(Shape s) throws IllegalArgumentException;

  /**
   * Removes a command from the animation.
   *
   * @param command the command to be removed from the animation.
   * @throws IllegalArgumentException if the command does not exist in the animation
   */
  void removeCommand(ICommands command) throws IllegalArgumentException;

  /**
   * gets a map of all the shapes in the animation.
   *
   * @return the all Shapes in the animation.
   */
  List<K> getShapes();


  /**
   * getter for the bnackground of the animation.
   *
   * @return the background of the animation.
   */
  IBackground getBackground();

  /**
   * gets the last tick of any shape in the model.
   *
   * @return int of the last tick
   */
  int getLastTick();

  /**
   * sees if the animation is started.
   *
   * @return boolean if the animation is started.
   */
  boolean isStarted();

  /**
   * gets all the commands in a animator
   *
   * @return list of commands in an animator
   */
  List<ISimpleCommands> getCommands();
}
