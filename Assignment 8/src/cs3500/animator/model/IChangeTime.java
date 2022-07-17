package cs3500.animator.model;

/**
 * a class to hold information on start and end ticks .
 */

public interface IChangeTime {

  /**
   * getter for the start tick.
   *
   * @return start tick
   */

  int getStart();

  /**
   * getter for the end tick.
   *
   * @return end tick
   */
  int getEnd();
}
