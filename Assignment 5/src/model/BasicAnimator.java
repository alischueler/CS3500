package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents the most basic form of a animator.
 * The animator has a map of commands which it uses to make the animation.
 * The K for this animator is a Shape object.
 */
public class BasicAnimator implements IAnimator<Shape> {

  private SimpleBackground b;
  // INVARIANT: each key has an associated list of strings. Each string has exactly 17
  // spaces. One space in between each data point.
  private Map<String, List<String>> mapCommands;
  private Map<String, Shape> currentShapes;

  /**
   * The constructor for BasicAnimator.
   *
   * @param height height of the background
   * @param width  width of the background
   * @param shape  String of the shape
   * @param red    red value
   * @param blue   blue value
   * @param green  green value
   */
  public BasicAnimator(int height, int width, String shape, double red, double blue, double green) {
    this.b = new SimpleBackground(height, width, shape, new Color(red, blue, green));
    this.mapCommands = null;
    this.currentShapes = null;
  }

  /**
   * The constructor for BasicAnimator default.
   */
  public BasicAnimator() {
    this.b = new SimpleBackground(500, 500, "rectangle", new Color(0, 0, 0));
    this.mapCommands = null;
    this.currentShapes = null;
  }


  @Override
  public void startAnimator(Map<String, List<String>> commands) throws IllegalArgumentException {
    commandsGood(commands);
    this.mapCommands = commands;
    this.currentShapes = new HashMap<>();
  }

  /**
   * checks to make sure that the commands given to the model are good and error free.
   *
   * @param commands list of commands to be animated
   * @throws IllegalArgumentException if there are overlapping commands commands is null not enough
   *                                  commands command has invalid format
   */
  private void commandsGood(Map<String, List<String>> commands) throws IllegalArgumentException {
    if (commands == null) {
      throwIllArg("commands are null");
    } else {
      correctCommandType(commands);
      anyOverlap(commands);
    }
  }

  /**
   * sees if any of the the commands are overlapping for the same shape. or if the command is too
   * short.
   *
   * @param commands the commands that going to be used by the model
   * @throws IllegalArgumentException if the commands overlap the size of the command is too short
   *                                  any paramaters are invalid
   */
  private void correctCommandType(Map<String, List<String>> commands)
      throws IllegalArgumentException {
    // see if any of the commands are of the wrong type/not enough commands
    for (String key : commands.keySet()) {
      for (String com : commands.get(key)) {
        correctCommandTypeHelper(com);
      }
    }
  }


  /**
   * checks the command to see if its good or not. bad if: null, length is too small, it can't be
   * made into a shape.
   *
   * @param com the command to be tested
   * @throws IllegalArgumentException if the command is bad
   */
  private void correctCommandTypeHelper(String com) throws IllegalArgumentException {
    // making sure the command isnt null
    if (com == null) {
      throw new IllegalArgumentException("null in commands");
    }
    String[] splitCommands = com.split(" ");
    // seeing if the size of the list os too small
    if (splitCommands.length < 16) {
      throwIllArg("Not enough commands");
    }

    // checking to see if the commands are good
    mutateEntireShape(com, new BasicShape("test", "test"));

    for (int i = 0; i < splitCommands.length; i++) {
      // the first two commands should be strings
      if ((i < 2) & (isInt(splitCommands[i]))) {
        throwIllArg("The passed commands should not be a int index: " + i);
      } else if (i > 2 & !isInt(splitCommands[i])) {
        throwIllArg("The passed commands should be a string at index: " + i);
      }
    }
  }


  /**
   * sees if there is any overlap in the command start time.
   *
   * @param commands the map of the commands
   * @throws IllegalArgumentException is there is overlap times
   */
  private void anyOverlap(Map<String, List<String>> commands) throws IllegalArgumentException {
    // seeing if there are overlaping commands
    for (String key : commands.keySet()) {
      ArrayList<Position> commandTimeList = new ArrayList<>();
      // looping over the commands
      for (String com : commands.get(key)) {
        String[] splitCommands = com.split(" ");
        Position startEndTime = new Position(Integer.parseInt(splitCommands[2]),
            Integer.parseInt(splitCommands[3]));
        commandTimeList.add(startEndTime);
      }
      anyOverlapHelper(commandTimeList);
    }
  }

  /**
   * sees if a string can be turned to a number.
   *
   * @param s string to check
   * @return boolean if the string can become a number
   */
  private boolean isInt(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * sees if any of the given positions overlap.
   *
   * @param commandTimeList a list of positions
   * @throws IllegalArgumentException if the positions overlap
   */
  private void anyOverlapHelper(ArrayList<Position> commandTimeList)
      throws IllegalArgumentException {
    // x == start
    // y == end
    for (int i = 0; i < commandTimeList.size(); i++) {
      for (int n = 0; n < commandTimeList.size(); n++) {
        if (i == n) {
          continue;
        }
        Position p1 = commandTimeList.get(i);
        Position p2 = commandTimeList.get(n);

        if (p2.getX() == p1.getX() & p2.getY() == p1.getY()) {
          throwIllArg("commands overlap");
        }
        // looking for overlap
        // if the p1start is greater than the p2start and p2start is before p1ends they overlap
        if (p2.getX() > p1.getX() & p2.getX() < p1.getY()) {
          throwIllArg("commands overlap");
        }
        // flipped scenario above
        if (p1.getX() > p2.getX() & p1.getX() < p2.getY()) {
          throwIllArg("commands overlap");
        }
      }
    }
  }


  /**
   * throws a IllegalArgumentException with the given message.
   *
   * @param msg message to be thrown
   * @throws IllegalArgumentException when started
   */
  private void throwIllArg(String msg) throws IllegalArgumentException {
    throw new IllegalArgumentException(msg);
  }

  /**
   * throws a IllegalStateException with the given message.
   *
   * @param msg message to be thrown
   * @throws IllegalStateException when called
   */
  private void throwIllState(String msg) throws IllegalStateException {
    throw new IllegalStateException(msg);
  }


  /**
   * sees if the game has been started.
   *
   * @throws IllegalStateException if the animation has not been started
   */
  private void isAnimationStarted() throws IllegalStateException {
    if (this.mapCommands == null) {
      throwIllState("Animation not started");
    }
  }

  /**
   * sees if the tick is valid.
   *
   * @param tick the tick that is being checked
   * @throws IllegalArgumentException if tick is negative
   */
  private void checkTick(int tick) throws IllegalArgumentException {
    if (tick < 0) {
      throwIllArg("tick is negative");
    }
  }

  @Override
  public Map<String, Shape> getStateAt(int tick) throws IllegalArgumentException,
      IllegalStateException {
    isAnimationStarted();
    checkTick(tick);
    changeState(tick);
    return this.currentShapes;
  }

  /**
   * changes the state of the game to the given tick.
   *
   * @param tick the tick that the game is being made for
   */
  private void changeState(int tick) {
    for (String shapeName : this.mapCommands.keySet()) {
      // seeing if the shapeAnimation is currently displayed
      Shape shapeObject = this.currentShapes.getOrDefault(shapeName, null);
      if (shapeObject == null) {
        tryMakingNewAnimation(tick, shapeName);
      } else if (shapeObject.getEndTick() >= tick) {
        mutateOutOfDateShape(tick, shapeName);
        tweenShape(tick, shapeName);
      }
      // if the animation is still good then move it
      else {
        tweenShape(tick, shapeName);
      }
    }
  }

  /**
   * Attempts to add a shape to the currentShapes. if the shape is currently in the animation the
   * method could do nothing if the shape is not in the animation assumes the shape has 2 arguments
   * for the dimensions
   *
   * @param tick      the current tick
   * @param shapeName the name of the shape to try to add
   */
  private void tryMakingNewAnimation(int tick, String shapeName) {
    List<String> commands = mapCommands.get(shapeName);
    for (String command : commands) {
      // splitting commands
      ArrayList<String> givenCom = new ArrayList<>(Arrays.asList(command.split(" ")));
      int startTime = Integer.parseInt(givenCom.get(2));
      int endTime = Integer.parseInt(givenCom.get(3));

      if (startTime <= tick && endTime >= tick) {
        String name = givenCom.get(0);
        String shapeType = givenCom.get(1);
        Shape addedShape = new BasicShape(name, shapeType);
        mutateEntireShape(command, addedShape);
        this.currentShapes.put(shapeName, addedShape);
        tweenShape(tick, shapeName);
      }
    }
  }


  /**
   * takes a shape and sees of it should be deleted from animation. or be mutated with all new
   * values.
   *
   * @param tick      the current tick
   * @param shapeName the name of the shape being examned
   * @throws IllegalArgumentException if the commands are invalid
   */
  private void mutateOutOfDateShape(int tick, String shapeName) throws IllegalArgumentException {
    Shape examiningShape = this.currentShapes.get(shapeName);
    List<String> comands = this.mapCommands.get(shapeName);

    // looping over commands looking for a command that works at this tick
    for (String command : comands) {
      ArrayList<String> givenCom = new ArrayList<>(Arrays.asList(command.split(" ")));
      int startTime = Integer.parseInt(givenCom.get(2));
      int endTime = Integer.parseInt(givenCom.get(3));

      if (startTime <= tick && endTime > tick) {
        mutateEntireShape(command, examiningShape);
      }
    }
  }


  /**
   * mutates the given shape to the given commands. sets all current fields to the start fields.
   *
   * @param command          the command for the current instance of the shape
   * @param shapeToBeMutated the shape that's going to be mutated
   * @throws IllegalArgumentException if the shape has invalid commands
   */
  private void mutateEntireShape(String command, Shape shapeToBeMutated)
      throws IllegalArgumentException {
    List<String> givenCom = new ArrayList<>(Arrays.asList(command.split(" ")));

    int startTime = Integer.parseInt(givenCom.get(2));
    int endTime = Integer.parseInt(givenCom.get(3));
    shapeToBeMutated.setStartTime(startTime);
    shapeToBeMutated.setEndTime(endTime);

    // check sub list but i think its right, inclusive first exclusive second
    Position startPos = new Position(givenCom.subList(4, 6));
    Position endPos = new Position(givenCom.subList(6, 8));
    shapeToBeMutated.setStartPosition(startPos);
    shapeToBeMutated.setEndPosition(endPos);

    // setting the color
    Color startColor = new Color(givenCom.subList(8, 11));
    Color endColor = new Color(givenCom.subList(11, 14));
    shapeToBeMutated.setStartColor(startColor);
    shapeToBeMutated.setEndColor(endColor);

    // currently only dimensions of length two work
    TotalSize startDimensions = new TotalSize(givenCom.subList(14, 16));
    TotalSize endDimensions = new TotalSize(givenCom.subList(16, 18));
    shapeToBeMutated.setStartSize(startDimensions);
    shapeToBeMutated.setEndSize(endDimensions);

    // setting all the start variables
    shapeToBeMutated.updateCurrPos(startPos.getX(), startPos.getY());
    shapeToBeMutated.updateCurrColor(startColor.getRed(), startColor.getGreen(),
        startColor.getBlue());
    shapeToBeMutated.updateCurrSize(startDimensions.getTotalHeight(),
        startDimensions.getTotalWidth());
  }

  /**
   * moves a shape to the given tick. mutates everything color, position, size.
   *
   * @param shapeName the name of the shape to be tweened
   * @param tick      the tick to be tweened to
   */
  private void tweenShape(int tick, String shapeName) {
    // tweening the different aspects of the shape
    changeSize(tick, shapeName);
    changeColor(tick, shapeName);
    changePosition(tick, shapeName);
  }


  /**
   * calcuates the position of the data based on the inputs passed. is used in the change methods
   *
   * @param posToCalculate the current tick of the animator its: currtick - shape.startTime this
   *                       calcuation would be redundent if done in this method
   * @param amountOfTime   the amount of time the enimation exists for
   * @param startPoint     the starting point of the shape
   * @param endPoint       the ending point of the shape
   * @return the poition of the data point for the shape
   */
  private static double calculatePoint(int posToCalculate, int amountOfTime, double startPoint,
      double endPoint) {
    double dist = endPoint - startPoint;
    double slopeWidth = dist / amountOfTime;
    return slopeWidth * posToCalculate + startPoint;
  }


  /**
   * changes the size of the shape to be the correct size at the given tick.
   *
   * @param tick the tick to move the data to
   * @param name the name of the shape that's being move=d
   */
  private void changeSize(int tick, String name) {
    Shape shape = currentShapes.get(name);
    int posToCalculate = tick - shape.getStartTick();
    int amountOfTime = shape.getEndTick() - shape.getStartTick();
    TotalSize startPos = shape.getStartDimension();
    TotalSize endPos = shape.getEndDimension();

    double newWidth = calculatePoint(posToCalculate, amountOfTime, startPos.getTotalWidth(),
        endPos.getTotalWidth());

    double newHeight = calculatePoint(posToCalculate, amountOfTime, startPos.getTotalHeight(),
        endPos.getTotalHeight());

    shape.updateCurrSize(newWidth, newHeight);
  }


  /**
   * changes the color of the shape to be the correct color at the given tick.
   *
   * @param tick the tick to move the data to
   * @param name the name of the shape that's being move=d
   */
  private void changeColor(int tick, String name) {
    Shape shape = currentShapes.get(name);
    int posToCalculate = tick - shape.getStartTick();
    int amountOfTime = shape.getEndTick() - shape.getStartTick();
    Color startPos = shape.getStartColor();
    Color endPos = shape.getEndColor();

    double newRed = calculatePoint(posToCalculate, amountOfTime, startPos.getRed(),
        endPos.getRed());

    double newBlue = calculatePoint(posToCalculate, amountOfTime, startPos.getBlue(),
        endPos.getBlue());

    double newGreen = calculatePoint(posToCalculate, amountOfTime, startPos.getGreen(),
        endPos.getGreen());

    shape.updateCurrColor(newRed, newBlue, newGreen);
  }


  /**
   * changes the position of the shape to be the correct position at the given tick.
   *
   * @param tick the tick to move the data to
   * @param name the name of the shape that's being move=d
   */
  private void changePosition(int tick, String name) {
    Shape shape = currentShapes.get(name);
    int posToCalculate = tick - shape.getStartTick();
    int amountOfTime = shape.getEndTick() - shape.getStartTick();
    Position startPos = shape.getStartPosition();
    Position endPos = shape.getEndPosition();

    double newXPos = calculatePoint(posToCalculate, amountOfTime, startPos.getX(),
        endPos.getX());

    double newYPos = calculatePoint(posToCalculate, amountOfTime, startPos.getY(),
        endPos.getY());

    shape.updateCurrPos(newXPos, newYPos);
  }


  @Override
  public void addCommand(String command)
      throws IllegalArgumentException, IllegalStateException {
    isAnimationStarted();
    correctCommandTypeHelper(command);

    List<String> givenCom = new ArrayList<>(Arrays.asList(command.split(" ")));
    String name = givenCom.get(0);
    List<String> exists = mapCommands.getOrDefault(name, null);

    // seeing if the shape command is good
    mutateEntireShape(command, new BasicShape("", ""));

    if (exists == null) {
      mapCommands.put(name, new ArrayList<>(Collections.singletonList(command)));
    } else {
      addToExisingComands(name, command);
    }
  }

  /**
   * adds a command to a existing shape.
   *
   * @param name    the name of that shape that the comand coresopnds too
   * @param command the command to add
   */
  private void addToExisingComands(String name, String command) {
    List<String> possibleCommands = new ArrayList<>(mapCommands.get(name));
    possibleCommands.add(command);
    HashMap<String, List<String>> holderMap = new HashMap<String, List<String>>();
    holderMap.put(name, possibleCommands);
    anyOverlap(new HashMap<>(holderMap));
    mapCommands.put(name, possibleCommands);
  }

  @Override
  public void removeShape(String name) throws IllegalStateException, IllegalArgumentException {
    isAnimationStarted();

    if (name == null) {
      throwIllArg("Shape can be null");
    }
    if (mapCommands.getOrDefault(name, null) == null) {
      throwIllArg("Shape does not exist in this animation");
    }
    currentShapes.remove(name);
    mapCommands.remove(name);
  }

  @Override
  public boolean isAnimationOver(int tick) throws IllegalArgumentException, IllegalStateException {
    isAnimationStarted();
    checkTick(tick);
    return loopOverEndTimes(tick);
  }

  /**
   * loops and gets all end times of the animations. sees of any of the endtimes are > the passed
   * tick. does not have any side effects.
   *
   * @param tick the tick we are comparing the end times too
   * @return boolean if there is a end time tick > passed tick
   */
  public boolean loopOverEndTimes(int tick) {
    boolean over = false;
    for (String key : mapCommands.keySet()) {
      for (String com : mapCommands.get(key)) {
        int endTick = Integer.parseInt(com.split(" ")[3]);
        over = over | endTick < tick;
      }
    }
    return over;
  }

  @Override
  public Map<String, List<String>> getCommands() {
    isAnimationStarted();
    return this.mapCommands;
  }
}
