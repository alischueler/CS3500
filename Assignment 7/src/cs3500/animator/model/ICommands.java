package cs3500.animator.model;

/**
 * a interface the represents commands in a shape.
 */
public interface ICommands {


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
  ChangeTime getTicks();

  /**
   * gets the strat color for a command.
   *
   * @return start color
   */
  Color getStartColor();

  /**
   * gets end color for a command.
   *
   * @return end color
   */
  Color getEndColor();

  /**
   * gets start position for a command.
   *
   * @return start position
   */
  Position getStartPosition();

  /**
   * gets end position for a command.
   *
   * @return end position
   */
  Position getEndPosition();

  /**
   * gets start size for a command.
   *
   * @return start size
   */
  TotalSize getStartSize();

  /**
   * gets end size for a command.
   *
   * @return end size
   */
  TotalSize getEndSize();
}
