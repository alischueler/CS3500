package cs3500.animator.model;

/**
 * An interface for all types of commands.
 */
public interface ISimpleCommands {

  /**
   * gets the shape this command is referencing.
   *
   * @return the String of the name of the shape
   */
  String getShape();

  /**
   * gets the start and end ticks of a command.
   *
   * @return ChangeTime object with the ticks
   */
  IChangeTime getTicks();

}
