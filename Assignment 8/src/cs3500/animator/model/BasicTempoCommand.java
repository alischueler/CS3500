package cs3500.animator.model;

import java.util.Objects;

/**
 * An implementation of a tempocommand.
 */
public class BasicTempoCommand implements ITempoCommands {

  private final String name;
  private final IChangeTime t;
  private final int tempo;

  /**
   * constructs a tempo command.
   *
   * @param name  the name of the "shape".
   * @param t     the intervals of the change.
   * @param tempo the tempo the animation will run at.
   */
  public BasicTempoCommand(String name, IChangeTime t, int tempo) {
    if (name == null || !name.equals("slow-mo")) {
      throw new IllegalArgumentException("name is null or not slow-mo");
    }
    if (t == null) {
      throw new IllegalArgumentException("change time is null");
    }
    if (tempo < 1) {
      throw new IllegalArgumentException("tempo is less than 1");
    }
    this.name = name;
    this.t = t;
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public String getShape() {
    return name;
  }

  @Override
  public IChangeTime getTicks() {
    return new ChangeTime(t.getStart(), t.getEnd());
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof BasicTempoCommand)) {
      return false;
    }

    BasicTempoCommand that = (BasicTempoCommand) a;

    return (this.getTicks().equals(that.getTicks()))
        && (this.getTempo() == (that.getTempo()))
        && (this.getShape().equals(that.getShape()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getTicks(), this.getTempo(), this.getShape());
  }
}
