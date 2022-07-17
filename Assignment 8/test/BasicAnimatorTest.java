import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.BasicShape;
import cs3500.animator.model.BasicTempoCommand;
import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.Command;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.ICommands;
import cs3500.animator.model.ISimpleCommands;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.TotalSize;
import cs3500.animator.model.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * a class to test BasicAnimator.
 */
public class BasicAnimatorTest {

  IAnimator<Shape> emptyModel;
  IAnimator<Shape> nullCommandModel;
  IAnimator<Shape> simpAnimation;
  IAnimator<Shape> doubleAnimation;
  IAnimator<Shape> noCommands;
  IAnimator<Shape> noShapes;
  IAnimator<Shape> tempoModel;
  IAnimator<Shape> tempoModelBad;

  // front ends
  String bob010;
  String bob515;
  String bobNegTime;
  String bobReverseTime;
  String bob1020;

  // positions
  String positionConstant;
  String positionInc;
  String positionInc2;
  String positionDec;

  String posNegTime;

  // colors
  String colorConstant;
  String colorIncreasing;
  String colorIncreasing2;
  String colorDecreasing;

  String colorNegStart;
  String colorNegEnd;
  String colorNegBoth;

  String colorTooHigh;

  // dimensions
  String dimSquareIncreasing;
  String dimSquareIncreasing2;
  String dimSquareStaySame;
  String dimSquareDec;

  String dimStartNeg;
  String dimEndNeg;

  //shapes
  Shape bobConstPosColorSize0;
  Shape bobConstPosColorSize1;

  //commands
  String negBobString;
  String bobDecString;
  String jeffDecString;

  List<String> negBob;


  /**
   * holds all example objects for testing.
   */
  protected void examples() {
    // front ends
    bob010 = "bob 0 10";
    bob515 = "bob 5 15";
    bobNegTime = "bob -5 15";
    bobReverseTime = "bob 15 5";
    bob1020 = "bob 10 20";

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

    bobConstPosColorSize0 = new BasicShape("bob", ShapeType.RECTANGLE);

    bobConstPosColorSize1 = new BasicShape("bob", ShapeType.RECTANGLE);

    negBobString = bob010 + positionConstant + dimEndNeg + colorConstant;

    List<String> bobIncreasing = new ArrayList<>(
        Collections.singletonList(bob010 + positionInc + dimSquareIncreasing + colorIncreasing));

    List<String> bobStaySame = new ArrayList<>(
        Collections
            .singletonList(bob010 + positionConstant + dimSquareStaySame + colorConstant));

    bobDecString = bob1020 + positionConstant + dimSquareStaySame + colorConstant;

    jeffDecString = "jeff 4 15" + positionConstant + dimSquareStaySame + colorConstant;

    negBobString = bob010 + positionConstant + dimEndNeg + colorConstant;
    negBob = new ArrayList<>(Collections.singletonList(negBobString));

    //simple animation model
    simpAnimation = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();

    //a double model
    doubleAnimation = new BasicAnimator.Builder().declareShape("jeff", "rectangle")
        .addMotion("jeff", 4, 0, 10, 10, 0, 0, 0, 15, 10, 0,
            10, 10, 0, 0, 0, 0).setBounds(45, 78, 400,
            400).declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).build();

    //an empty model
    emptyModel = new BasicAnimator.Builder().setBounds(30, 30, 800, 600).build();

    //a model with no commands
    noCommands = new BasicAnimator.Builder().setBounds(400, 450, 700, 600)
        .declareShape("jeff", "ellipse").declareShape("george",
            "rectangle").build();

    //a model with no shapes
    noShapes = new BasicAnimator.Builder().setBounds(400, 450, 700, 600)
        .addMotion("jedd", 4, 5, 6, 7, 7, 8, 8, 8, 8,
            8, 7, 5, 3, 2, 5, 4).build();

    //a model with tempo commands
    tempoModel = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).addTempo(10, 100, 20)
        .build();

    //a model with bad tempo
    tempoModelBad = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).addTempo(10, 100, 20)
        .addTempo(12, 30, 25).build();
  }

  /*
   * -------------------------------------- startAnimator --------------------------------------
   */

  //tests starting the animator with no commands
  @Test
  public void testNoCommands() {
    examples();
    noCommands.startAnimator();
    List<Shape> shapes = noCommands.getShapes();
    for (int i = 0; i < shapes.size(); i++) {
      assertEquals(new ArrayList<ICommands>(), shapes.get(i).getCommands());
    }
  }

  //tests starting the animator with no shapes
  @Test
  public void testNoShapes() {
    examples();
    noShapes.startAnimator();
    assertEquals(new ArrayList<Shape>(), noShapes.getShapes());
  }

  //tests starting the animator with empty model
  @Test
  public void testEmpty() {
    examples();
    emptyModel.startAnimator();
    assertEquals(new ArrayList<Shape>(), emptyModel.getShapes());
    List<Shape> shapes = noCommands.getShapes();
    for (int i = 0; i < shapes.size(); i++) {
      assertEquals(new ArrayList<ICommands>(), shapes.get(i).getCommands());
    }
  }

  //tests starting the animator with overlapping commands
  @Test(expected = IllegalArgumentException.class)
  public void testAddOverlappingCommands() {
    examples();
    IAnimator<Shape> doubleModel = new BasicAnimator.Builder().setBounds(50, 50, 50, 50)
        .addMotion("s3", 3, 4, 5, 6, 7, 2, 1, 3, 40,
            5, 6, 7, 8, 9, 3, 22).addMotion("s3", 30, 4,
            5, 6, 7, 2, 1, 3, 45, 5, 6, 7, 8, 9,
            3, 22).declareShape("s3", "ellipse").build();
    doubleModel.startAnimator();
  }

  //tests starting the animator with duplicate commands
  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateCommands() {
    examples();
    IAnimator<Shape> doubleModel = new BasicAnimator.Builder().setBounds(50, 50, 50, 50)
        .addMotion("s3", 3, 4, 5, 6, 7, 2, 1, 3, 3,
            5, 6, 7, 8, 9, 3, 22).addMotion("s3", 3, 4,
            5, 6, 7, 2, 1, 3, 4, 5, 6, 7, 8, 9,
            3, 22).declareShape("s3", "ellipse").build();
    doubleModel.startAnimator();
  }

  //tests starting thr animator with duplicate shape names
  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateShapes() {
    examples();
    IAnimator<Shape> doubleModel = new BasicAnimator.Builder().setBounds(50, 50, 50, 50)
        .addMotion("s3", 3, 4, 5, 6, 7, 2, 1, 3, 3,
            5, 6, 7, 8, 9, 3, 22).declareShape("s3",
            "ellipse").declareShape("s3", "ellipse").build();
    doubleModel.startAnimator();
  }

  //tests starting the animator with duplicate shape names but different type
  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateShapesNames() {
    examples();
    IAnimator<Shape> doubleModel = new BasicAnimator.Builder().setBounds(50, 50, 50,
        50).addMotion("s3", 3, 4, 5, 6, 7, 2, 1, 3,
        3, 5, 6, 7, 8, 9, 3, 22).declareShape("s3",
        "ellipse").declareShape("s3", "rectangle").build();
    doubleModel.startAnimator();
  }

  //tests starting the animator with multiple shapes
  @Test
  public void testStartMultipleShapes() {
    examples();
    IAnimator<Shape> doubleModel = new BasicAnimator.Builder().setBounds(50, 50, 50,
        50).addMotion("s3", 3, 4, 5, 6, 7, 2, 1, 3,
        3, 5, 6, 7, 8, 9, 3, 22).declareShape("s3",
        "ellipse").declareShape("s", "ellipse").addMotion("s", 5, 6, 7, 8,
        6, 3, 4, 5, 7, 3, 2, 1, 3, 5, 5, 5).build();
    doubleModel.startAnimator();
    for (int i = 0; i < doubleModel.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < doubleModel.getShapes().get(i).getCommands().size(); j++) {
        list.add(doubleModel.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, doubleModel.getShapes().get(i).getCommands());
    }
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

    simpAnimation.addCommand(new Command(bob1020 + positionConstant
        + dimSquareIncreasing + colorNegBoth));
  }

  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegColorEnd() {
    examples();
    simpAnimation.addCommand(new Command(bob1020 + positionConstant +
        dimSquareIncreasing + colorNegEnd));
  }

  /**
   * testing string that has Neg color start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegColorStart() {
    examples();
    simpAnimation.addCommand(new Command(bob1020 + positionConstant +
        dimSquareIncreasing + colorNegStart));
  }

  /**
   * testing string that has start < end time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandDecTime() {
    examples();
    simpAnimation
        .addCommand(new Command(bobReverseTime + positionConstant +
            dimSquareIncreasing + colorConstant));
  }


  /**
   * testing string that has negative times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegTime() {
    examples();
    simpAnimation.addCommand(new Command(bobNegTime + positionConstant +
        dimSquareIncreasing + colorConstant));
  }

  /**
   * testing string that has overlapping times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandOverlappingTimes() {
    examples();
    simpAnimation.startAnimator();
    simpAnimation.addCommand(new Command(bob515 + positionConstant +
        dimSquareIncreasing + colorConstant));
  }

  /**
   * testing string that has two commands with same start and end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandOverlappingTimesSameTime() {
    examples();
    simpAnimation.startAnimator();
    simpAnimation.addCommand(new Command(bob010 + positionConstant +
        dimSquareIncreasing + colorConstant));

  }

  /**
   * testing string that's too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandSmall() {
    examples();
    simpAnimation.addCommand(new Command(bob010 + dimSquareIncreasing + colorConstant));
  }

  /**
   * testing string that has comas seperating.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandCommas() {
    examples();
    simpAnimation.addCommand(new Command(bob1020 + "," +
        ", " + dimSquareIncreasing + ", " + colorConstant));
  }

  /**
   * testing string that has too high color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandColorToHigh() {
    examples();
    simpAnimation.addCommand(new Command(bob1020 + positionConstant +
        dimSquareIncreasing + colorTooHigh));

  }

  /**
   * testing when pass a word where there should be a number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandWordWrongSpot() {
    examples();
    simpAnimation.addCommand(new Command(bob1020 + positionConstant + " hi 5 6 4 " + colorTooHigh));
  }

  /**
   * testing string that has neg dimensions start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNegDimensionsEnd() {
    examples();
    simpAnimation.addCommand(new Command(negBobString));
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
    simpAnimation.addCommand(new Command(bobDecString));

    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }
  }

  /**
   * adding a new command before playing game.
   */
  @Test
  public void addCommandBeforeGameNewCommand() {
    examples();
    simpAnimation.addCommand(new Command(bobDecString));

    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }

  }

  /**
   * adding a new shape before getStateAt is called.
   */
  @Test
  public void addCommandToNonStartedGameExistingShape() {
    examples();
    simpAnimation.addCommand(new Command(jeffDecString));

    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }


  }

  /**
   * adding a new a shape after getStateAt is called.
   */
  @Test
  public void addCommandToGameExistingShape() {
    examples();
    simpAnimation.addCommand(new Command(jeffDecString));

    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }

  }

  /**
   * adding a new a shape to a animation with nothing in it.
   */
  @Test
  public void addCommandToEmptyModle() {
    examples();
    emptyModel.addCommand(new Command(jeffDecString));

    for (int i = 0; i < emptyModel.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < emptyModel.getShapes().get(i).getCommands().size(); j++) {
        list.add(emptyModel.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, emptyModel.getShapes().get(i));
    }

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
   * testing string that has neg dimensions end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegDimensionsEnd() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant + colorConstant + dimEndNeg));
  }

  /**
   * testing string that has neg dimensions start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegDimensionsStart() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    for (String s : negBob) {
      emptyModel.addCommand(new Command(s));
    }
  }

  /**
   * testing when pass a word where there should be a number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorWordWrongSpot() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant + colorTooHigh + " hi 5 6 4"));
  }

  /**
   * testing string that has too high color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorColorToHigh() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant +
        dimSquareIncreasing + colorTooHigh));
  }


  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegColorBoth() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant + colorNegBoth +
        dimSquareIncreasing));
  }

  /**
   * testing string that has Neg color end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegColorEnd() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant + colorNegEnd +
        dimSquareIncreasing));
  }

  /**
   * testing string that has Neg color start.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegColorStart() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant + colorNegStart +
        dimSquareIncreasing));
  }

  /**
   * testing string that has start < end time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatordecTime() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bobReverseTime + positionConstant + colorConstant +
        dimSquareIncreasing));
  }


  /**
   * testing string that has negative times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNegTime() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bobNegTime + positionConstant + colorConstant +
        dimSquareIncreasing));
  }

  /**
   * testing string that has overlapping times.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorOverlappingTimes() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant + colorConstant +
        dimSquareIncreasing));
    emptyModel.addCommand(new Command(bob515 + positionConstant + colorConstant +
        dimSquareIncreasing));
  }

  /**
   * testing string that has two commands with same start and end.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorOverlappingTimesSameTime() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + positionConstant +
        dimSquareIncreasing + colorConstant));
    emptyModel.addCommand(new Command(bob010 + positionConstant +
        dimSquareIncreasing + colorConstant));
  }


  /**
   * testing initializing animator with null background.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorNullBG() {
    examples();
    nullCommandModel = new BasicAnimator.Builder().addMotion("alice", 60, 600, 700,
        5, 6, 2, 4, 6, 235, 4, 6, 7, 6, 7, 56,
        24).declareShape("alice", "ellipse").build();
  }

  /**
   * testing string that's too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorSmall() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + colorConstant + dimSquareIncreasing));
  }

  /**
   * testing string that has comas seperating.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startAnimatorCommas() {
    examples();
    emptyModel.startAnimator();
    emptyModel.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
    emptyModel.addCommand(new Command(bob010 + "," + colorConstant + ", " + dimSquareIncreasing));
  }


  /*
   * -------------------------------------- addShape --------------------------------------
   */

  /**
   * shape is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addShapeNull() {
    examples();
    simpAnimation.addShape(null);
  }

  //tests adding a shape that already exists in the animation
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExists() {
    examples();
    simpAnimation.addShape(new BasicShape("bob", ShapeType.RECTANGLE));
  }

  //tests adding a shape that is a different type of shape but same name to the animation
  @Test(expected = IllegalArgumentException.class)
  public void addShapeExistsName() {
    examples();
    simpAnimation.addShape(new BasicShape("bob", ShapeType.ELLIPSE));
  }

  //tests adding a shape that has a command already
  @Test
  public void addShapeExistsCommandForIt() {
    examples();
    simpAnimation.addCommand(new Command("alice 5 13 2 2 4 6 6 5 3 6 4 1 3 4 5 6"));
    simpAnimation.addShape(new BasicShape("alice", ShapeType.ELLIPSE));
    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }
  }

  //tests adding a shape that has no commands
  @Test
  public void addShapeNoCommandForIt() {
    examples();
    simpAnimation.addShape(new BasicShape("alice", ShapeType.ELLIPSE));
    int index = simpAnimation.getShapes().indexOf(new BasicShape("alice", ShapeType.ELLIPSE));
    assertEquals(0, simpAnimation.getShapes().get(index).getCommands().size());
  }

  //adding plus shape
  @Test
  public void addShapePlus() {
    examples();
    simpAnimation.addShape(new BasicShape("alice", ShapeType.PLUS));
    int index = simpAnimation.getShapes().indexOf(new BasicShape("alice", ShapeType.ELLIPSE));
    assertEquals(0, simpAnimation.getShapes().get(index).getCommands().size());
  }

  /*
   * -------------------------------------- removeCommand --------------------------------------
   */

  //tests removing a valid command
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveCommand() {
    examples();
    simpAnimation.removeCommand(new Command("bob 0 0 0 10 10 0 0 0 10 10 10 20 20 20 20 20"));
    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }
  }

  //tests removing a valid command from a shape with multiple commands
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveCommandMultipleCommand() {
    examples();
    simpAnimation.addCommand(new Command("bob 5 6 3 2 4 5 6 3 2 1 4 4 3 4 5 6"));
    simpAnimation.addShape(new BasicShape("george", ShapeType.ELLIPSE));
    simpAnimation.addCommand(new Command("george 5 6 4 2 4 5 6 12 2 1 4 44 3 4 5 6"));
    simpAnimation.removeCommand(new Command("bob 0 0 0 10 10 0 0 0 10 10 10 20 20 20 20 20"));
    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i).getCommands());
    }
  }

  //tests removing a null command
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNull() {
    examples();
    simpAnimation.removeCommand(null);
  }

  //tests removing a command of a shape that is not in animation
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveCommandShapeNotExists() {
    examples();
    simpAnimation.removeCommand(new Command("alice 5 7 5 4 2 2 2 2 2 2 2 5 6 3 8 4"));
  }

  //tests removing a command of a shape that does not have that command
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveCommandShapeNotCommand() {
    examples();
    simpAnimation.removeCommand(new Command("bob 5 7 5 4 2 2 2 2 2 2 2 5 6 3 8 4"));
  }

  /*
   * -------------------------------------- getShape --------------------------------------
   */

  //tests getting the shapes of an empty model
  @Test
  public void testEmptyModelShapes() {
    examples();
    assertEquals(new ArrayList<>(), emptyModel.getShapes());
  }

  //tests getting the shapes of a simple animations
  @Test
  public void testSimpleModelShapes() {
    examples();
    List<Shape> list = new ArrayList<>();
    list.add(new BasicShape("bob", ShapeType.RECTANGLE));
    assertEquals(list, simpAnimation.getShapes());
  }

  //tests getting the shapes of an animation with many shapes
  @Test
  public void testMultipleModelShapes() {
    examples();
    List<Shape> list = new ArrayList<>();
    list.add(new Rectangle("jeff"));
    list.add(new Rectangle("bob"));
    assertEquals(list, doubleAnimation.getShapes());
  }

  //tests getting the shapes after adding shapes
  @Test
  public void testShapesAddShapes() {
    examples();
    simpAnimation.addShape(new Rectangle("alice"));
    List<Shape> list = new ArrayList<>();
    list.add(new Rectangle("bob"));
    list.add(new Rectangle("alice"));
    assertEquals(list, simpAnimation.getShapes());
  }

  //tests getting the shapes after removing shapes
  @Test
  public void testShapesRemoveShapes() {
    examples();
    simpAnimation.addShape(new Rectangle("alice"));
    simpAnimation.addShape(new Ellipse("rolf"));
    simpAnimation.removeShape(new Ellipse("rolf"));
    List<Shape> list = new ArrayList<>();
    list.add(new Rectangle("bob"));
    list.add(new Rectangle("alice"));
    assertEquals(list, simpAnimation.getShapes());
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
    simpAnimation.removeShape(new BasicShape("bob", ShapeType.RECTANGLE));
    simpAnimation.startAnimator();
    assertEquals(new ArrayList<>(), simpAnimation.getShapes());

  }

  /**
   * removes a good shape and leaves the other shape in the commands map alone.
   */
  @Test
  public void removeGoodShapeLeaveOtherAlone() {
    examples();
    simpAnimation.addCommand(new Command(jeffDecString));
    simpAnimation.addShape(new BasicShape("jeff", ShapeType.ELLIPSE));
    simpAnimation.removeShape(new BasicShape("bob", ShapeType.RECTANGLE));
    List<Shape> list = new ArrayList<>();
    Shape jeff = new BasicShape("jeff", ShapeType.ELLIPSE);
    jeff.addCommand(new Command(jeffDecString));
    list.add(jeff);
    simpAnimation.startAnimator();
    assertEquals(list, simpAnimation.getShapes());
  }

  /**
   * removes a good shape that has more than one command and another shape is in animation.
   */
  @Test
  public void removeGoodShapeMultipleComands() {
    examples();
    simpAnimation.addCommand(new Command(jeffDecString));
    simpAnimation.addCommand(new Command(bob1020 + positionDec +
        dimSquareIncreasing + colorDecreasing));
    simpAnimation.removeShape(new BasicShape("bob", ShapeType.RECTANGLE));
    simpAnimation.startAnimator();
    for (int i = 0; i < simpAnimation.getShapes().size(); i++) {
      List<ICommands> list = new ArrayList<>();
      for (int j = 0; j < simpAnimation.getShapes().get(i).getCommands().size(); j++) {
        list.add(simpAnimation.getShapes().get(i).getCommands().get(j));
      }
      assertEquals(list, simpAnimation.getShapes().get(i));
    }
  }


  /**
   * throw error if the shape is not present.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeShapeBadShape() {
    examples();
    simpAnimation.removeShape(new BasicShape("jerr", ShapeType.ELLIPSE));
  }

  /**
   * throw error if the map is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void removeShapeBadShapeMapEmpty() {
    examples();
    emptyModel.startAnimator();
    simpAnimation.removeShape(new BasicShape("jerr", ShapeType.ELLIPSE));
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
   * -------------------------------------- getBackground --------------------------------------
   */

  //tests getting the given background of an animator
  @Test
  public void testGetBG() {
    examples();
    assertEquals(200, simpAnimation.getBackground().getHeight());
    assertEquals(300, simpAnimation.getBackground().getWidth());
    assertEquals(50, simpAnimation.getBackground().getXBound());
    assertEquals(50, simpAnimation.getBackground().getYBound());
  }

  /*
   * -------------------------------------- isAnimationStarted ------------------------------------
   */

  //tests animation has not started
  @Test
  public void testNotStarted() {
    examples();
    assertFalse(simpAnimation.isStarted());
  }

  //tests animation has started
  @Test
  public void testStarted() {
    examples();
    simpAnimation.startAnimator();
    assertTrue(simpAnimation.isStarted());
  }

  /*
   * -------------------------------------- getCommands ------------------------------------
   */

  //tests getting commands of empty model
  @Test
  public void testgetCommands0() {
    examples();
    simpAnimation.startAnimator();
    assertEquals(new ArrayList<ISimpleCommands>(), emptyModel.getCommands());
  }

  //tests getting commands of singular shape model
  @Test
  public void testgetCommands1() {
    examples();
    simpAnimation.startAnimator();
    List<ISimpleCommands> com = new ArrayList<>();
    com.add(new Command("bob", new ChangeTime(0, 10), new Color(0, 0, 0),
        new Color(20, 20, 20), new Position(0, 0), new Position(10, 10),
        new TotalSize(10, 10), new TotalSize(20, 20)));
    assertEquals(com, simpAnimation.getCommands());
  }

  //tests getting commands for a model with tempo commands
  @Test
  public void testgetCommandsTempo() {
    examples();
    tempoModel.startAnimator();
    List<ISimpleCommands> com = new ArrayList<>();
    com.add(new Command("bob", new ChangeTime(0, 10), new Color(0, 0, 0),
        new Color(20, 20, 20), new Position(0, 0), new Position(10, 10),
        new TotalSize(10, 10), new TotalSize(20, 20)));
    com.add(new BasicTempoCommand("slow-mo", new ChangeTime(10, 100), 20));
    assertEquals(com, tempoModel.getCommands());
  }

  //tests commands where tempo ticks overlap
  @Test(expected = IllegalArgumentException.class)
  public void testgetCommandsTempoBad() {
    examples();
    tempoModelBad.startAnimator();
    List<ISimpleCommands> com = new ArrayList<>();
    com.add(new Command("bob", new ChangeTime(0, 10), new Color(0, 0, 0),
        new Color(20, 20, 20), new Position(0, 0), new Position(10, 10),
        new TotalSize(10, 10), new TotalSize(20, 20)));
    com.add(new BasicTempoCommand("slow-mo", new ChangeTime(10, 100), 20));
  }
}
