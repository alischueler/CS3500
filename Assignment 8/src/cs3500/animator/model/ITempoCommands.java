package cs3500.animator.model;

/**
 * an interface for tempo change commands.
 */
public interface ITempoCommands extends ISimpleCommands {

  /**
   * gets that tempo of the command.
   * @return tempo of the command
   */
  int getTempo();

}
