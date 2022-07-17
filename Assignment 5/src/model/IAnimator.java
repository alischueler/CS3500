package model;

import java.util.List;
import java.util.Map;

/**
 * Represents a model for animators
 * the model is controller by a tick.
 * to increase the speed of the animation you call the tick faster.
 * K is the shape that the model implements.
 */
public interface IAnimator<K> {

  /**
   * a method to start the animation. the method takes in no arguments it simply creates an empty
   * map of K
   *
   * @param commands a map of commands that have to be executed by the model the key is the shape
   *                 name
   *
   *                 command List Of String format: "Name Shape StartTick EndTick StartX EndX
   *                 StartY EndY StartRed StartBlue StartGreen EndRed EndBlue EndGreen
   *                 ArgsStartDimensions ArgsEndDimensions"
   *
   *                 - ArgsStartDimensions is the dimensions of the shape at the start separated by
   *                 a space - ArgsEndDimensions the dimensions of the shape at the end separated
   *                 by a space
   *
   * @throws IllegalArgumentException if the commands passed are bad
   */
  void startAnimator(Map<String, List<String>> commands) throws IllegalArgumentException;


  /**
   * gets the state of the game at a given tick with the current shapes in the model.
   *
   * @param tick the tick to het the state of the game at
   * @throws IllegalArgumentException if the tick is negative
   * @throws IllegalStateException    if the game hasn't started
   */
  Map<String, K> getStateAt(int tick) throws IllegalArgumentException,
      IllegalStateException;


  /**
   * adds a command to a a shape. the shape does not have to exist in the animation.
   *
   * @param command the command to be added to the animation (see javaDoc on startAnimation for how
   *                to format a command)
   * @throws IllegalArgumentException if the command is bad
   * @throws IllegalStateException    if the game has not started
   */
  void addCommand(String command) throws IllegalArgumentException,
      IllegalStateException;


  /**
   * Removes a given shape from the animation. removes all future moves as well
   *
   * @param name the name of the shape thats being moved
   * @throws IllegalArgumentException if the name cant be found
   */
  void removeShape(String name);


  /**
   * method to tell you if the animation os over. its over if the given tick is > then all animation
   * commands
   *
   * @param tick the tick to check if the animation is over
   * @return boolean if animation is over
   * @throws IllegalStateException is tick is bad
   */
  boolean isAnimationOver(int tick) throws IllegalArgumentException;

  /**
   * gets a map of all the commands in the animation.
   *
   * @return the commands of all Shapes in the animation.
   * @throws IllegalStateException if the game is not started.
   */
  Map<String, List<String>> getCommands() throws IllegalStateException;

}