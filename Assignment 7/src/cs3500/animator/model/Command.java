package cs3500.animator.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * holds a command for a shape object. all relevant data is stored here
 */
public class Command implements ICommands {

  ChangeTime time;
  Color startColor;
  Color endColor;
  Position startPosition;
  Position endPosition;
  TotalSize startSize;
  TotalSize endSize;
  String name;

  /**
   * a constructor for the Commands class.
   *
   * @param time          time object
   * @param startColor    starting color
   * @param endColor      ending color
   * @param startPosition starting position
   * @param endPosition   ending position
   * @param startSize     starting size
   * @param endSize       ending size
   */
  public Command(String name, ChangeTime time, Color startColor, Color endColor,
      Position startPosition,
      Position endPosition, TotalSize startSize, TotalSize endSize) {
    if (name == null) {
      throw new IllegalArgumentException("name is null");
    }
    if (time == null) {
      throw new IllegalArgumentException("time is null");
    }
    if (startColor == null) {
      throw new IllegalArgumentException("startColor is null");
    }
    if (endColor == null) {
      throw new IllegalArgumentException("endColor is null");
    }
    if (startPosition == null) {
      throw new IllegalArgumentException("startPos is null");
    }
    if (endPosition == null) {
      throw new IllegalArgumentException("endPosition is null");
    }
    if (startSize == null) {
      throw new IllegalArgumentException("startSize is null");
    }
    if (endSize == null) {
      throw new IllegalArgumentException("endSize is null");
    }
    this.time = time;
    this.startColor = startColor;
    this.endColor = endColor;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
    this.startSize = startSize;
    this.endSize = endSize;
    this.name = name;
  }

  /**
   * a constructor for the Commands class.
   *
   * @param command a command to be parsed and made into a command
   */
  public Command(String command) {
    if (command == null) {
      throw new IllegalArgumentException("command is null");
    }
    List<String> givenCom = Arrays.asList(command.split(" "));
    if (givenCom.size() != 17) {
      throw new IllegalArgumentException("command too long");
    }
    this.name = givenCom.get(0);
    this.time = new ChangeTime(Integer.parseInt(givenCom.get(1)),
        Integer.parseInt(givenCom.get(2)));
    this.startColor = new Color(Integer.parseInt(givenCom.get(11)),
        Integer.parseInt(givenCom.get(12)), Integer.parseInt(givenCom.get(13)));
    this.endColor = new Color(Integer.parseInt(givenCom.get(14)),
        Integer.parseInt(givenCom.get(15)), Integer.parseInt(givenCom.get(16)));
    this.startPosition = new Position(Integer.parseInt(givenCom.get(3)),
        Integer.parseInt(givenCom.get(4)));
    this.endPosition = new Position(Integer.parseInt(givenCom.get(5)),
        Integer.parseInt(givenCom.get(6)));
    this.startSize = new TotalSize(Integer.parseInt(givenCom.get(7)),
        Integer.parseInt(givenCom.get(8)));
    this.endSize = new TotalSize(Integer.parseInt(givenCom.get(9)),
        Integer.parseInt(givenCom.get(10)));
  }


  @Override
  public String getShape() {
    return this.name;
  }

  @Override
  public ChangeTime getTicks() {
    return this.time;
  }

  @Override
  public Color getStartColor() {
    return this.startColor;
  }

  @Override
  public Color getEndColor() {
    return this.endColor;
  }

  @Override
  public Position getStartPosition() {
    return this.startPosition;
  }

  @Override
  public Position getEndPosition() {
    return this.endPosition;
  }

  @Override
  public TotalSize getStartSize() {
    return this.startSize;
  }

  @Override
  public TotalSize getEndSize() {
    return this.endSize;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Command)) {
      return false;
    }

    Command that = (Command) a;

    return (this.getTicks().equals(that.getTicks()))
        && (this.getStartPosition().equals(that.getStartPosition()))
        && (this.getStartSize().equals(that.getStartSize()))
        && (this.getStartColor().equals(that.getStartColor()))
        && (this.getEndPosition().equals(that.getEndPosition()))
        && (this.getEndSize().equals(that.getEndSize()))
        && (this.getEndColor().equals(that.getEndColor()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getTicks(), this.getStartPosition(), this.getStartSize(),
        this.getStartColor(), this.getEndPosition(), this.getEndSize(), this.getEndColor());
  }
}
