package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

/**
 * A class to represent basic shapes to be changed by the model.
 */
public class BasicShape implements Shape {

  private final String name;
  private final ShapeType shapeType;
  private ICommands currCommand;

  private IPosition currPos;
  private IColor currColor;
  private ITotalSize currDimensions;

  private TreeMap<IChangeTime, ICommands> commands;


  /**
   * Constructs a BasicShape.
   *
   * @param name      the name of this shape
   * @param shapeType the type of this shape
   */
  public BasicShape(String name, ShapeType shapeType) {
    this.name = name;
    this.shapeType = shapeType;
    this.currCommand = null;
    this.commands = new TreeMap<>(new ChangeTimeComparator());
  }


  @Override
  public ICommands getCurrCommand() throws IllegalArgumentException {
    checkNull(currCommand, "current command is null");
    return this.currCommand;
  }

  @Override
  public String getShapeName() {
    return this.name;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }

  @Override
  public IColor getCurrColor() throws IllegalArgumentException {
    checkNull(this.currColor, "Current Color not initialized");
    return this.currColor;
  }

  @Override
  public IPosition getCurrentPosition() throws IllegalArgumentException {
    checkNull(this.currPos, "Current Position not initialized");
    return this.currPos;
  }

  @Override
  public ITotalSize getCurrentDimension() throws IllegalArgumentException {
    checkNull(this.currDimensions, "Current Dimension not initialized");
    return this.currDimensions;
  }


  @Override
  public void updateCurrPos(double x, double y) {
    if (this.currPos == null) {
      this.currPos = new Position(x, y);
    } else {
      this.currPos.updatePos(x, y);
    }
  }

  @Override
  public void updateCurrColor(double r, double g, double b) {
    if (this.currColor == null) {
      this.currColor = new Color(r, g, b);
    } else {
      this.currColor.updateColor(r, g, b);
    }
  }

  @Override
  public void updateCurrSize(double w, double h) {
    if (this.currDimensions == null) {
      this.currDimensions = new TotalSize(w, h);
    } else {
      this.currDimensions.updateDimensions(w, h);
    }
  }

  @Override
  public List<ICommands> getCommands() throws IllegalArgumentException {
    checkNull(commands, "command is null");
    List<ICommands> out = new ArrayList<>();
    for (Entry<IChangeTime, ICommands> entry : commands.entrySet()) {
      out.add(entry.getValue());
    }
    return out;
  }

  @Override
  public void addCommand(ICommands com) throws IllegalArgumentException {
    if (com == null) {
      throw new IllegalArgumentException("command is null");
    }
    if (com.getShape().equals(name)) {
      IChangeTime changeTimeCom = com.getTicks();
      checkOverlap(changeTimeCom);
      checkConsistancy(com, changeTimeCom);
      this.commands.put(changeTimeCom, com);
    } else {
      throw new IllegalArgumentException("command is not for this shape");
    }
  }

  /**
   * checks for any overlap with this command's start and end time and the other start and end times
   * of commands in this shape.
   *
   * @param cCommand the ChangeTime to be compared with other ChangeTimes
   */
  private void checkOverlap(IChangeTime cCommand) {
    int newStart = cCommand.getStart();
    int newEnd = cCommand.getEnd();
    for (IChangeTime t : this.commands.keySet()) {
      int existingStart = t.getStart();
      int existingEnd = t.getEnd();
      if (existingStart == newStart && existingEnd == newEnd ||
          (newStart > existingStart && newStart < existingEnd)
          || (newEnd > existingStart && newEnd < existingEnd)) {
        throw new IllegalArgumentException(
            "new command overlaps with a command that already exists in this shape");
      }
    }
  }

  /**
   * checks for consistancy between the given command and ChangeTime and commands with ChangeTimes.
   * that have same start as this commands end or same end as this commands start
   *
   * @param comToAdd  the string array of a command already broken up
   * @param timeToAdd the changetime of the command to be compared
   */
  private void checkConsistancy(ICommands comToAdd, IChangeTime timeToAdd) {
    for (IChangeTime t : this.commands.keySet()) {
      ICommands existingSplit = this.commands.get(t);
      if (timeToAdd.getEnd() - t.getStart() == 0 && timeToAdd.getStart() < t.getEnd()) {
        compareStartEnd(comToAdd, existingSplit);
      }
      if (t.getEnd() - timeToAdd.getStart() == 0 && t.getStart() < timeToAdd.getEnd()) {
        compareStartEnd(existingSplit, comToAdd);
      }
    }
  }

  /**
   * checks if the first commands end values are equal to the seconds commands start values.
   *
   * @param firstCom  the command whose end values will be analyzed
   * @param secondCom the command whose start values will be analyzed
   * @throws IllegalArgumentException if the end values are not equal to the seconds commands end
   *                                  values
   */
  private void compareStartEnd(ICommands firstCom, ICommands secondCom)
      throws IllegalArgumentException {
    if (!(firstCom.getEndColor().equals(secondCom.getStartColor()) &&
        firstCom.getEndSize().equals(secondCom.getStartSize()) &&
        firstCom.getEndPosition().equals(secondCom.getStartPosition()))) {
      throw new IllegalArgumentException("back to back commands do not match up");
    }
  }

  @Override
  public void removeCommand(ICommands time) throws IllegalArgumentException {
    if (time == null) {
      throw new IllegalArgumentException("command given is null");
    }
    boolean contains = false;
    if (this.commands.containsKey(time.getTicks())) {
      for (IChangeTime t : this.commands.keySet()) {
        if (this.commands.get(t).equals(time)) {
          this.commands.remove(t);
          contains |= true;
          break;
        }
      }
      if (!contains) {
        throw new IllegalArgumentException("command does not exist in this shape");
      }
    }
  }

  /**
   * Throws an IllegalState if the given field is null, else do nothing.
   *
   * @param field the field to be checked if null or not
   * @param msg   the message to put in the IllegalState
   * @throws IllegalStateException if the given field is null
   */
  private void checkNull(Object field, String msg) throws IllegalStateException {
    if (field == null) {
      throw new IllegalStateException(msg);
    }
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof BasicShape)) {
      return false;
    }

    BasicShape that = (BasicShape) a;

    return this.name.equals(that.name) &
        this.shapeType.equals(that.shapeType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.shapeType);
  }


  @Override
  public boolean compareTick(int tick) {
    boolean result = false;
    for (IChangeTime com : this.commands.keySet()) {
      if (com.getStart() <= tick && com.getEnd() >= tick) {
        return true;
      }
    }
    return result;
  }

  @Override
  public int getVeryLastTick() {
    int lastTick = 0;
    for (IChangeTime com : this.commands.keySet()) {
      if (com.getEnd() > lastTick) {
        lastTick = com.getEnd();
      }
    }
    if (lastTick == 0) {
      throw new IllegalArgumentException("there are no commands");
    } else {
      return lastTick;
    }
  }

  @Override
  public void checkGaps() {
    //creating a list of only intervals to fill
    List<IChangeTime> listOfChangeTimes = new ArrayList<>();
    listOfChangeTimes.addAll(this.commands.keySet());

    //going through each changetime and next one
    for (int i = 0; i < listOfChangeTimes.size() - 1; i++) {

      //checking to see if startTime of next changetime is different than the endTime
      // of the first changetime
      if (listOfChangeTimes.get(i + 1).getStart() - listOfChangeTimes.get(i).getEnd() > 0) {

        //creating the new ChangeTime to put into the commands tree
        IChangeTime changeTimeToAdd = new ChangeTime(listOfChangeTimes.get(i).getEnd(),
            listOfChangeTimes.get(i + 1).getStart());

        //creating the string of the command to add to the commands tree
        ICommands firstCommand = this.commands.get(listOfChangeTimes.get(i));
        ICommands secondCommand = this.commands.get(listOfChangeTimes.get(i + 1));
        ICommands commandToAdd = new Command(this.getShapeName(), new ChangeTime(
            firstCommand.getTicks().getEnd(), secondCommand.getTicks().getStart()),
            firstCommand.getEndColor(), secondCommand.getStartColor(),
            firstCommand.getEndPosition(), secondCommand.getStartPosition(),
            firstCommand.getEndSize(), secondCommand.getStartSize());
        this.commands.put(changeTimeToAdd, commandToAdd);
      }
    }
  }

  @Override
  public void updateShape(int tick) {
    for (IChangeTime time : this.commands.keySet()) {
      if (time.getStart() == tick && time.getEnd() != tick || inBetweenAndOutOfDate(tick, time)) {
        ICommands command = this.commands.get(time);
        this.currCommand = command;
        updateCurrColor(currCommand.getStartColor().getRed(),
            currCommand.getStartColor().getGreen(), currCommand.getStartColor().getBlue());
        updateCurrPos(currCommand.getStartPosition().getX(), currCommand.getStartPosition().getY());
        updateCurrSize(currCommand.getStartSize().getTotalWidth(),
            currCommand.getStartSize().getTotalHeight());
        break;
      }
    }
  }

  /**
   * sees if the the tick is inbetween the start and end times. but the shaoe current command is not
   * updated
   *
   * @param tick tick for the current display
   * @param time the time object for the command
   * @return boolean is the command is in between the start and end time but out of date
   */
  private boolean inBetweenAndOutOfDate(int tick, IChangeTime time) {

    if (time.getStart() <= tick && tick <= time.getEnd()) {
      try {
        return this.getCurrCommand().getTicks().getStart() != time.getStart();
      } catch (IllegalStateException e) {
        return true;
      }
    }
    return false;
  }

}
