import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.BasicShape;
import cs3500.animator.model.BasicTempoCommand;
import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.Color;
import cs3500.animator.model.Command;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.ISimpleCommands;
import cs3500.animator.model.Position;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.TotalSize;
import cs3500.animator.view.IViewModel;
import cs3500.animator.view.ShapeViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * test the view model.
 */
public class TestViewModel {

  IAnimator<Shape> emptyModel;
  IAnimator<Shape> nullCommandModel;
  IAnimator<Shape> simpAnimation;
  IAnimator<Shape> doubleAnimation;
  IAnimator<Shape> noCommands;
  IAnimator<Shape> noShapes;
  IAnimator<Shape> tempoModel;
  IAnimator<Shape> tempoModelBad;

  IViewModel<Shape> vm;

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
   * -------------------------------------- getShape --------------------------------------
   */

  //tests getting the shapes of an empty model
  @Test
  public void testEmptyModelShapes() {
    examples();
    vm = new ShapeViewModel(emptyModel);
    assertEquals(new ArrayList<>(), vm.getShapes());
  }

  //tests getting the shapes of a simple animations
  @Test
  public void testSimpleModelShapes() {
    examples();
    List<Shape> list = new ArrayList<>();
    list.add(new BasicShape("bob", ShapeType.RECTANGLE));
    vm = new ShapeViewModel(simpAnimation);
    assertEquals(list, vm.getShapes());
  }

  //tests getting the shapes of an animation with many shapes
  @Test
  public void testMultipleModelShapes() {
    examples();
    List<Shape> list = new ArrayList<>();
    list.add(new Rectangle("jeff"));
    list.add(new Rectangle("bob"));
    vm = new ShapeViewModel(doubleAnimation);
    assertEquals(list, vm.getShapes());
  }

  //tests getting the shapes after adding shapes
  @Test
  public void testShapesAddShapes() {
    examples();
    simpAnimation.addShape(new Rectangle("alice"));
    List<Shape> list = new ArrayList<>();
    list.add(new Rectangle("bob"));
    list.add(new Rectangle("alice"));
    vm = new ShapeViewModel(simpAnimation);
    assertEquals(list, vm.getShapes());
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
    vm = new ShapeViewModel(simpAnimation);
    assertEquals(list, vm.getShapes());
  }

  /*
   * -------------------------------------- getBackground --------------------------------------
   */

  //tests getting the given background of an animator
  @Test
  public void testGetBG() {
    examples();
    vm = new ShapeViewModel(simpAnimation);
    assertEquals(200, vm.getBackground().getHeight());
    assertEquals(300, vm.getBackground().getWidth());
    assertEquals(50, vm.getBackground().getXBound());
    assertEquals(50, vm.getBackground().getYBound());
  }

  /*
   * -------------------------------------- isAnimationStarted ------------------------------------
   */

  //tests animation has not started
  @Test
  public void testNotStarted() {
    examples();
    vm = new ShapeViewModel(simpAnimation);
    assertFalse(vm.isStarted());
  }

  //tests animation has started
  @Test
  public void testStarted() {
    examples();
    simpAnimation.startAnimator();
    vm = new ShapeViewModel(simpAnimation);
    assertTrue(vm.isStarted());
  }

  /*
   * -------------------------------------- getCommands ------------------------------------
   */

  //tests getting commands of empty model
  @Test
  public void testgetCommands0() {
    examples();
    simpAnimation.startAnimator();
    vm = new ShapeViewModel(emptyModel);
    assertEquals(new ArrayList<ISimpleCommands>(), vm.getCommands());
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

    vm = new ShapeViewModel(simpAnimation);
    assertEquals(com, vm.getCommands());
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
    vm = new ShapeViewModel(tempoModel);
    assertEquals(com, vm.getCommands());
  }
}

