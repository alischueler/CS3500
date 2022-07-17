package cs3500.animator.view;

import cs3500.animator.model.IBackground;
import cs3500.animator.model.ISimpleCommands;
import java.util.List;


/**
 * a class that takes in a animator and then keeps its read olny methods. gets rid of the mutating
 * methods.
 *
 * @param <K> the type of the shape in the animation.
 */
public interface IViewModel<K> {

  /**
   * a method to start the animation. the method takes in no arguments it simply creates an empty
   * map of K.
   *
   * @throws IllegalArgumentException if the commands passed are bad
   */
  void startAnimator() throws IllegalArgumentException;

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
   * gets all commands for a model
   *
   * @return List of commands for a model
   */
  List<ISimpleCommands> getCommands();

}
