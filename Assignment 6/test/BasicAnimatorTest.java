import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.BasicAnimator;
import model.BasicShape;
import model.Color;
import model.IAnimator;
import model.Position;
import model.Shape;
import model.TotalSize;
import org.junit.Test;

/**
 * a class to test BasicAnimator.
 */
public class BasicAnimatorTest {

  Map<String, Shape> emptyMapStringShape;

  HashMap<String, List<String>> commandsEmpty;
  HashMap<String, List<String>> commandMap;
  HashMap<String, List<String>> doubleCommandMap;

  IAnimator<Shape> emptyModel;
  IAnimator<Shape> simpAnimation;
  IAnimator<Shape> doubleAnimation;

  String bobNegTime;
  String colorIncreasing;
  String colorDecreasing;
  String colorNegStart;
  String colorNegEnd;
  String colorNegBoth;
  String dimSquareIncreasing;
  String dimSquareStaySame;
  String dimStartNeg;
  String dimEndNeg;
  String colorConstant;
  String bob010;
  String bob515;
  String posNegTime;
  String positionConstant;
  String positionInc;
  String bobReverseTime;
  String colorTooHigh;
  Shape bobConstPosColorSize0;
  String bob1020;
  String positionInc2;
  String dimSquareIncreasing2;
  String colorIncreasing2;
  String positionDec;
  String dimSquareDec;
  String jeffDecString;
  String bobDecString;
  Shape bobConstPosColorSize1;
  String negBobString;

  ArrayList<String> bobIncreasing;
  ArrayList<String> bobStaySame;
  ArrayList<String> jeffDec;
  ArrayList<String> negBob;


  /**
   * holds all example objects for testing.
   */
  protected void examples() {
    emptyModel = new BasicAnimator();
    commandMap = new HashMap<>();
    // front ends
    bob010 = "bob rectangle 0 10";
    bob515 = "bob rectangle 5 15";
    bobNegTime = "bob rectangle -5 15";
    bobReverseTime = "bob rectangle 15 5";
    bob1020 = "bob rectangle 10 20";

    // positions
    positionConstant = " 0 0 0 0";
    positionInc = " 0 0 10 10";
    positionInc2 = " 10 10 30 20";
    positionDec = " 60 80 0 0";

    posNegTime = " -1 10 0 10";

    // colors
    colorConstant = " 0 0 0 0 0 0";
    colorIncreasing = " 0 0 0 20 20 20";
    colorIncreasing2 = " 20 20 20 40 30 60";
    colorDecreasing = " 20 40 60 0 0 0";

    colorNegStart = " -1 0 0 0 0 0";
    colorNegEnd = " 0 0 0 0 -1 0";
    colorNegBoth = " 0 -1 0 0 -1 0";

    colorTooHigh = " 300 0 0 0 0 0";

    // dimensions
    dimSquareIncreasing = " 10 10 20 20";
    dimSquareIncreasing2 = " 20 20 40 30";
    dimSquareStaySame = " 10 10 10 10";
    dimSquareDec = " 10 10 0 0";

    dimStartNeg = " -10 10 20 20";
    dimEndNeg = " 10 10 -20 20";

    commandsEmpty = new HashMap<String, List<String>>();
    emptyMapStringShape = new HashMap<>();

    bobConstPosColorSize0 = new BasicShape("bob", "rectangle", 0, 10,
        new Position(0, 0), new Position(0, 0), new Color(0, 0, 0),
        new Color(0, 0, 0), new TotalSize(10, 10), new TotalSize(10, 10));

    bobConstPosColorSize1 = new BasicShape("bob", "rectangle", 0, 10,
        new Position(0, 0), new Position(0, 0), new Color(0, 0, 0),
        new Color(0, 0, 0), new TotalSize(10, 10), new TotalSize(10, 10));

    bobIncreasing = new ArrayList<>(
        Collections.singletonList(bob010 + positionInc + colorIncreasing + dimSquareIncreasing));

    bobStaySame = new ArrayList<>(
        Collections
            .singletonList(bob010 + positionConstant + colorConstant + dimSquareStaySame));

    bobStaySame = new ArrayList<>(
        Collections
            .singletonList(bob1020 + positionConstant + colorConstant + dimSquareStaySame));

    bobDecString = bob1020 + positionConstant + colorConstant + dimSquareStaySame;
    jeffDecString = "jeff Rectangle 4 15" + positionConstant + colorConstant + dimSquareStaySame;
    simpAnimation = new BasicAnimator();
    commandMap.put("bob", bobIncreasing);
    simpAnimation.startAnimator(commandMap);

    jeffDec = new ArrayList<>(Collections.singletonList(jeffDecString));

    negBobString = bob010 + positionConstant + colorConstant + dimEndNeg;
    negBob = new ArrayList<>(Collections.singletonList(negBobString));

    doubleAnimation = new BasicAnimator();
    doubleCommandMap = new HashMap<>(commandMap);
    doubleCommandMap.put("jeff", jeffDec);
    doubleAnimation.startAnimator(doubleCommandMap);
  }




  /*
   * -------------------------------------- addCommand --------------------------------------
   */

  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegColorBoth() {
    examples();
    simpAnimation.addCommand(bob1020 + positionConstant + colorNegBoth + dimSquareIncreasing);
  }

  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegColorEnd() {
    examples();
    simpAnimation.addCommand(bob1020 + positionConstant + colorNegEnd + dimSquareIncreasing);
  }

  /**
   * testing string that has Neg color start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegColorStart() {
    examples();
    simpAnimation.addCommand(bob1020 + positionConstant + colorNegStart + dimSquareIncreasing);
  }

  /**
   * testing string that has start < end time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandDecTime() {
    examples();
    simpAnimation
        .addCommand(bobReverseTime + positionConstant + colorConstant + dimSquareIncreasing);
  }


  /**
   * testing string that has negative times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegTime() {
    examples();
    simpAnimation.addCommand(bobNegTime + positionConstant + colorConstant + dimSquareIncreasing);
  }

  /**
   * testing string that has overlapping times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandOverlappingTimes() {
    examples();
    simpAnimation.addCommand(bob515 + positionConstant + colorConstant + dimSquareIncreasing);
  }

  /**
   * testing string that has two commands with same start and end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandOverlappingTimesSameTime() {
    examples();
    simpAnimation.addCommand(bob010 + positionConstant + colorConstant + dimSquareIncreasing);

  }

  /**
   * testing string that's too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandSmall() {
    examples();
    simpAnimation.addCommand(bob010 + colorConstant + dimSquareIncreasing);
  }

  /**
   * testing string that has comas seperating.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandCommas() {
    examples();
    simpAnimation.addCommand(bob1020 + "," + colorConstant +
        ", " + dimSquareIncreasing);
  }

  /**
   * testing string that has too high color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandColorToHigh() {
    examples();
    simpAnimation.addCommand(bob1020 + positionConstant + colorTooHigh + dimSquareIncreasing);

  }

  /**
   * testing when pass a word where there should be a number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandWordWrongSpot() {
    examples();
    simpAnimation.addCommand(bob1020 + positionConstant + colorTooHigh + " hi 5 6 4");
  }

  /**
   * testing string that has neg dimensions start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegDimensionsEnd() {
    examples();
    simpAnimation.addCommand(negBobString);
  }

  /**
   * game not started.
   */
  @Test(expected = IllegalStateException.class)
  public void addCommandGameNotStarted() {
    examples();
    emptyModel.addCommand(jeffDecString);
  }

  /**
   * command is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNull() {
    examples();
    simpAnimation.addCommand(null);
  }

  /**
   * adding a new command to a playing game.
   */
  @Test
  public void addCommandToPlayingGameNewCommand() {
    examples();
    simpAnimation.getStateAt(4);
    simpAnimation.addCommand(bobDecString);
    commandMap.get("bob").add(bobDecString);
    assertEquals(commandMap, simpAnimation.getCommands());
  }

  /**
   * adding a new command before playing game.
   */
  @Test
  public void addCommandBeforeGameNewCommand() {
    examples();
    simpAnimation.addCommand(bobDecString);
    commandMap.get("bob").add(bobDecString);
    assertEquals(commandMap, simpAnimation.getCommands());

  }

  /**
   * adding a new shape before getStateAt is called.
   */
  @Test
  public void addCommandToNonStartedGameExistingShape() {
    examples();
    simpAnimation.addCommand(jeffDecString);
    commandMap.put("jeff", jeffDec);
    assertEquals(commandMap, simpAnimation.getCommands());


  }

  /**
   * adding a new a shape after getStateAt is called.
   */
  @Test
  public void addCommandToGameExistingShape() {
    examples();
    simpAnimation.getStateAt(4);
    simpAnimation.addCommand(jeffDecString);
    commandMap.put("jeff", jeffDec);
    assertEquals(commandMap, simpAnimation.getCommands());

  }

  /**
   * adding a new a shape to a animation with nothing in it.
   */
  @Test
  public void addCommandToEmptyModle() {
    examples();
    emptyModel.startAnimator(commandsEmpty);
    emptyModel.addCommand(jeffDecString);
    commandMap.put("jeff", jeffDec);
    assertEquals(commandMap, simpAnimation.getCommands());

  }

  /*
   * start animation test:
   * - game started correctly with the right commands
   * - can start animation over of there is one running
   * - no commands is
   *
   *
   * errors:
   * - one of the things that should be a number is a string
   * - the string is too small
   * - the string has commas seperating
   * - null passed
   * - null passes in the map
   * - overlapping times
   * - bad neg time
   * - start less then end
   * - neg color
   * - color too high
   * - neg dimensions
   */


  /**
   * game started correctly with the right commands.
   */
  @Test
  public void startAnimatorOverAnotherAnimation() {
    examples();
    commandsEmpty.put("bob", bobStaySame);

    emptyModel.startAnimator(commandsEmpty);
    emptyModel.getStateAt(6);

    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionInc + colorConstant + dimSquareStaySame)));
    emptyModel.startAnimator(commandsEmpty);
    Map<String, List<String>> out = new HashMap<>(commandsEmpty);
    assertEquals(out, emptyModel.getCommands());
  }

  /**
   * game started correctly with not commands entered.
   */
  @Test
  public void startAnimatorNoCommandsGiven() {
    examples();
    emptyModel.startAnimator(commandsEmpty);
    Map<String, List<String>> out = new HashMap<>(commandsEmpty);
    assertEquals(out, emptyModel.getCommands());
  }

  /**
   * game started correctly with multiple shapes.
   */
  @Test
  public void startAnimatorMultipleShapes() {
    examples();
    emptyModel.startAnimator(commandsEmpty);
    commandsEmpty.put("bob", bobStaySame);

    commandsEmpty
        .put("jeff", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorConstant + dimSquareStaySame)));

    Map<String, List<String>> out = new HashMap<>(commandsEmpty);

    assertEquals(out, emptyModel.getCommands());
  }


  /**
   * game started correctly with the right commands.
   */
  @Test
  public void startAnimatorCorrect() {
    examples();
    commandsEmpty.put("bob", bobStaySame);

    emptyModel.startAnimator(commandsEmpty);

    assertEquals(commandsEmpty, emptyModel.getCommands());
  }


  /**
   * testing string that has neg dimensions end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegDimensionsEnd() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorConstant + dimEndNeg)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has neg dimensions start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegDimensionsStart() {
    examples();
    commandsEmpty.put("bob", negBob);

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing when pass a word where there should be a number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorWordWrongSpot() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorTooHigh + " hi 5 6 4")));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has too high color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorColorToHigh() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorTooHigh + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }


  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegColorBoth() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorNegBoth + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegColorEnd() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorNegEnd + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has Neg color start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegColorStart() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(bob010 + positionConstant + colorNegStart + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has start < end time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatordecTime() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections.singletonList(
                bobReverseTime + positionConstant + colorConstant + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }


  /**
   * testing string that has negative times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegTime() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections
                .singletonList(
                    bobNegTime + positionConstant + colorConstant + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has overlapping times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorOverlappingTimes() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Arrays.asList(bob010 + positionConstant + colorConstant + dimSquareIncreasing,
                bob515 + positionConstant + colorConstant + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has two commands with same start and end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorOverlappingTimesSameTime() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Arrays.asList(bob010 + positionConstant + colorConstant + dimSquareIncreasing,
                bob010 + positionConstant + colorConstant + dimSquareIncreasing)));

    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has a null map.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNull() {
    examples();
    emptyModel.startAnimator(null);
  }

  /**
   * testing string that's null in map.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNullCommand() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections.singletonList(null)));
    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that's too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorSmall() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections.singletonList(bob010 + colorConstant + dimSquareIncreasing)));
    emptyModel.startAnimator(commandsEmpty);
  }

  /**
   * testing string that has comas seperating.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorCommas() {
    examples();
    commandsEmpty.put("bob", new ArrayList<>(
        Collections.singletonList(bob010 + "," + colorConstant +
            ", " + dimSquareIncreasing)));
    emptyModel.startAnimator(commandsEmpty);
  }

  /*
   * get state at:
   * - returns the correct state at the first tick
   *  - returns the correct state at a certian tick with jump == 1
   * - returns the correct state at a certian tick with jump > 1
   * - mutates the colors correctly
   * - mutates the position correctly
   * - mutates the size correctly
   * - shaoe that stays at one spot
   * - nothing is on the board bc the tick is so high
   * - a shape gets the start/endPos mutated
   * - a shape gets taken off and not relpaced (done with the large tick)
   * - a shape on its last tick with no replacement
   *
   *
   * errors:
   * - the tick is negative
   * - game hasnt started
   */
  /*
   * -------------------------------------- getStateAt --------------------------------------
   */

  /**
   * reversing the movement of the tick.
   */
  @Test
  public void testGetStateAtReverse() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);
    emptyModel.getStateAt(10);

    bobConstPosColorSize0.updateCurrPos(5, 5);
    bobConstPosColorSize0.updateCurrColor(10, 10, 10);
    bobConstPosColorSize0.updateCurrSize(15, 15);
    emptyMapStringShape.put("bob", bobConstPosColorSize0);

    assertEquals(emptyMapStringShape, emptyModel.getStateAt(5));
  }

  /**
   * a shape that's going down in tweaning.
   */
  @Test
  public void testGettingSmaller() {
    examples();
    ArrayList<String> comm = new ArrayList<>(
        Collections.singletonList(bob010 + positionDec + colorDecreasing + dimSquareDec));
    commandsEmpty.put("bob", comm);

    emptyModel.startAnimator(commandsEmpty);
    bobConstPosColorSize1.updateCurrPos(30, 40);
    bobConstPosColorSize1.updateCurrColor(10, 20, 30);
    bobConstPosColorSize1.updateCurrSize(5, 5);

    emptyMapStringShape.put("bob", bobConstPosColorSize1);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(5));
  }

  /**
   * a shape on its last tick with no replacement does jumps there.
   */
  @Test
  public void testLastTick() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);
    emptyModel.startAnimator(commandsEmpty);
    bobConstPosColorSize1.updateCurrPos(10, 10);
    bobConstPosColorSize1.updateCurrColor(20, 20, 20);
    bobConstPosColorSize1.updateCurrSize(20, 20);

    emptyMapStringShape.put("bob", bobConstPosColorSize1);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(10));

  }

  /**
   * a shape on its last tick with no replacement does not jump there.
   */
  @Test
  public void testLastTickJumpoFromZero() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);
    emptyModel.startAnimator(commandsEmpty);
    bobConstPosColorSize1.updateCurrPos(10, 10);
    bobConstPosColorSize1.updateCurrColor(20, 20, 20);
    bobConstPosColorSize1.updateCurrSize(20, 20);

    emptyMapStringShape.put("bob", bobConstPosColorSize1);
    emptyModel.getStateAt(1);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(10));

  }

  /**
   * a shape gets the start/endPos mutated.
   */
  @Test
  public void testMutatingShapeEndPoints() {
    examples();
    ArrayList<String> comm = new ArrayList<>(
        Arrays.asList(bob010 + positionInc + colorIncreasing + dimSquareIncreasing,
            bob1020 + positionInc2 + colorIncreasing2 + dimSquareIncreasing2));
    commandsEmpty.put("bob", comm);

    emptyModel.startAnimator(commandsEmpty);
    bobConstPosColorSize1.updateCurrPos(20, 15);
    bobConstPosColorSize1.updateCurrColor(30, 25, 40);
    bobConstPosColorSize1.updateCurrSize(25, 30);

    emptyMapStringShape.put("bob", bobConstPosColorSize1);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(15));
  }


  /**
   * nothing is on the board bc the tick is so high.
   */
  @Test
  public void testTickHigh() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(40));
  }

  /**
   * returns the correct state at a certian tick with jump > 1.
   */
  @Test
  public void testWithJumpFive() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);
    emptyModel.getStateAt(0);

    bobConstPosColorSize0.updateCurrPos(5, 5);
    bobConstPosColorSize0.updateCurrColor(10, 10, 10);
    bobConstPosColorSize0.updateCurrSize(15, 15);
    emptyMapStringShape.put("bob", bobConstPosColorSize0);

    assertEquals(emptyMapStringShape, emptyModel.getStateAt(5));
  }

  /**
   * testing a shaoe that stays in the same spot.
   */
  @Test
  public void testWithShapeStaySame() {
    examples();
    ArrayList<String> comm = new ArrayList<>(
        Collections.singletonList(bob010 + positionConstant + colorConstant + dimSquareStaySame));
    commandsEmpty.put("bob", comm);

    emptyModel.startAnimator(commandsEmpty);
    emptyModel.getStateAt(0);
    emptyMapStringShape.put("bob", bobConstPosColorSize0);

    assertEquals(emptyMapStringShape, emptyModel.getStateAt(5));
  }


  /**
   * making sure tweening is correct when adding on one tick at a time.
   */
  @Test
  public void testIncByOneTick() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);

    bobConstPosColorSize1.updateCurrColor(2, 2, 2);
    bobConstPosColorSize1.updateCurrPos(1, 1);
    bobConstPosColorSize1.updateCurrSize(11, 11);
    emptyMapStringShape.put("bob", bobConstPosColorSize1);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(1));

    bobConstPosColorSize1.updateCurrColor(4, 4, 4);
    bobConstPosColorSize1.updateCurrPos(2, 2);
    bobConstPosColorSize1.updateCurrSize(12, 12);

    emptyMapStringShape.put("bob", bobConstPosColorSize1);
    assertEquals(emptyMapStringShape, emptyModel.getStateAt(2));
  }

  /**
   * making sure tweening is correct when adding new shapes to the animation.
   */
  @Test
  public void testFirstTick() {
    examples();
    commandsEmpty.put("bob", bobIncreasing);

    emptyModel.startAnimator(commandsEmpty);
    emptyMapStringShape.put("bob", bobConstPosColorSize1);

    assertEquals(emptyMapStringShape, emptyModel.getStateAt(0));
  }

  /**
   * throw error when the tick is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void tickNegative() {
    examples();
    commandsEmpty
        .put("bob", new ArrayList<>(
            Collections.singletonList(bob010 + colorConstant + dimSquareIncreasing)));
    emptyModel.startAnimator(commandsEmpty);
    emptyModel.getStateAt(-1);
  }

  /**
   * throw error when the animation hasnt started.
   */
  @Test(expected = IllegalStateException.class)
  public void gameHasntStarted() {
    examples();
    emptyModel.getStateAt(10);
  }


  /*
   * -------------------------------------- removeShape --------------------------------------
   */


  /**
   * removes a good shape checking the commands.
   */
  @Test
  public void removeGoodShape() {
    examples();
    simpAnimation.removeShape("bob");
    assertEquals(commandsEmpty, simpAnimation.getCommands());
  }

  /**
   * removes a good shape and leaves the other shape in the commands map alone.
   */
  @Test
  public void removeGoodShapeLeaveOtherAlone() {
    examples();
    simpAnimation.addCommand(jeffDecString);
    simpAnimation.removeShape("bob");
    HashMap<String, List<String>> m = new HashMap<>();
    m.put("jeff", jeffDec);
    assertEquals(m, simpAnimation.getCommands());
  }

  /**
   * removes a good shape that has more than one command and another shape is in animation.
   */
  @Test
  public void removeGoodShapeMultipleComands() {
    examples();
    simpAnimation.addCommand(jeffDecString);
    simpAnimation.addCommand(bob1020 + positionDec + colorDecreasing + dimSquareIncreasing);
    simpAnimation.removeShape("bob");
    HashMap<String, List<String>> m = new HashMap<>();
    m.put("jeff", jeffDec);
    assertEquals(m, simpAnimation.getCommands());
  }


  /**
   * throw error if the shape is not present.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeShapeBadShape() {
    examples();
    simpAnimation.removeShape("jerr");
  }

  /**
   * throw error if the map is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeShapeBadShapeMapEmpty() {
    examples();
    emptyModel.startAnimator(new HashMap<>());
    emptyModel.removeShape("jerr");
  }

  /**
   * throw error if the animation isnt started.
   */
  @Test(expected = IllegalStateException.class)
  public void removeShapeNotStarted() {
    examples();
    emptyModel.removeShape("bob");
  }

  /**
   * throw error if given null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeShapeNull() {
    examples();
    simpAnimation.removeShape(null);
  }


  /*
   * -------------------------------------- isAnimationOver --------------------------------------
   */

  /**
   * animation is not over both shape.
   */
  @Test
  public void isAnimationOverMultShapeFalseBoth() {
    examples();
    assertFalse(doubleAnimation.isAnimationOver(10));
  }

  /**
   * animation is over for both shapes.
   */
  @Test
  public void isAnimationOverMultShapeTrueBoth() {
    examples();
    assertTrue(doubleAnimation.isAnimationOver(16));
  }

  /**
   * the animation is over for one shape but not other.
   */
  @Test
  public void isAnimationOverMultShapeFalseSplit() {
    examples();
    assertTrue(doubleAnimation.isAnimationOver(12));
  }

  /**
   * the animation is over one shape.
   */
  @Test
  public void isAnimationOverOneShapeTrue() {
    examples();
    assertTrue(simpAnimation.isAnimationOver(11));
  }

  /**
   * the animation is not over one shape.
   */
  @Test
  public void isAnimationOverOneShapeFalse() {
    examples();
    assertFalse(simpAnimation.isAnimationOver(10));
  }

  /**
   * throw error the tick is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void isAnimationOverTickNeg() {
    examples();
    simpAnimation.isAnimationOver(-1);
  }

  /**
   * throw error the animation isn't started.
   */
  @Test(expected = IllegalStateException.class)
  public void isAnimationOverNotStarted() {
    examples();
    emptyModel.isAnimationOver(10);
  }


  /*
   * -------------------------------------- getCommands --------------------------------------
   */


  /**
   * return empty map.
   */
  @Test
  public void getCommandsEmptyMap() {
    examples();
    emptyModel.startAnimator(commandsEmpty);
    assertEquals(new HashMap<String, List<String>>(), emptyModel.getCommands());
  }


  /**
   * return map with one shape.
   */
  @Test
  public void getCommandsOneShape() {
    examples();
    assertEquals(commandMap, simpAnimation.getCommands());
  }

  /**
   * return map with multi shapes.
   */
  @Test
  public void getCommandsMultShape() {
    examples();
    assertEquals(doubleCommandMap, doubleAnimation.getCommands());
  }

  /**
   * the game isnt started.
   */
  @Test(expected = IllegalStateException.class)
  public void getCommandsNotStarted() {
    examples();
    emptyModel.getCommands();
  }


}
