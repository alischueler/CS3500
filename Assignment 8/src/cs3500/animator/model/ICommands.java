package cs3500.animator.model;

/**
 * a interface the represents commands in a shape.
 */
public interface ICommands extends ISimpleCommands {


  /**
   * gets the strat color for a command.
   *
   * @return start color
   */
  IColor getStartColor();

  /**
   * gets end color for a command.
   *
   * @return end color
   */
  IColor getEndColor();

  /**
   * gets start position for a command.
   *
   * @return start position
   */
  IPosition getStartPosition();

  /**
   * gets end position for a command.
   *
   * @return end position
   */
  IPosition getEndPosition();

  /**
   * gets start size for a command.
   *
   * @return start size
   */
  ITotalSize getStartSize();

  /**
   * gets end size for a command.
   *
   * @return end size
   */
  ITotalSize getEndSize();
}
