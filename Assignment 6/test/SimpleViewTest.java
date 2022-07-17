import static org.junit.Assert.assertEquals;

import model.BasicAnimator;
import model.IAnimator;
import model.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import view.SimpleAnimatorView;

/**
 * a class to test the SimpleView class.
 */
public class SimpleViewTest {

  Map<String, Shape> shapemap1;

  IAnimator<Shape> emptyModel;
  IAnimator<Shape> simpModel;
  IAnimator<Shape> multipleShapeModel;
  IAnimator<Shape> notStartedModel;
  IAnimator<Shape> assignmentModel;

  Map<String, List<String>> simpCommands;
  Map<String, List<String>> multCommands;
  Map<String, List<String>> noCommands;
  Map<String, List<String>> assignmentCommands;

  String colorIncreasing;
  String colorDecreasing;
  String colorConstant;
  String redConstant;
  String blueContstant;
  String brightGreentoGreen;
  String greenConstant;
  String blueToBrightGreen;

  String dimSquareIncreasing;
  String dimSquareStaySame;
  String constant50100;
  String decreaseWHalf;
  String constant25100;
  String constant12060;

  String bob010;
  String bob1020;
  String george015;
  String george1670;
  String r110;
  String r1050;
  String r5051;
  String r5170;
  String r70100;
  String c620;
  String c2050;
  String c5070;
  String c7080;
  String c80100;

  String positionConstant;
  String positionInc;
  String positionDec;
  String constant200200;
  String constant300300;
  String constant44070;
  String constant440370;
  String change44070to440250;
  String change440250to440370;
  String change200200to300300;
  String change300300to200200;


  SimpleAnimatorView simpView;
  SimpleAnimatorView multView;
  SimpleAnimatorView notStartedView;
  SimpleAnimatorView noCommandsView;
  SimpleAnimatorView classView;

  /**
   * a method that makes objects for testing.
   */
  protected void examples() {
    emptyModel = new BasicAnimator();
    simpModel = new BasicAnimator();
    multipleShapeModel = new BasicAnimator();
    notStartedModel = new BasicAnimator();
    assignmentModel = new BasicAnimator();

    shapemap1 = new HashMap<>();
    simpCommands = new HashMap<>();
    multCommands = new HashMap<>();
    noCommands = new HashMap<>();
    assignmentCommands = new HashMap<>();

    // front ends
    bob010 = "bob rectangle 0 10";
    bob1020 = "bob rectangle 11 20";
    george015 = "george oval 0 15";
    george1670 = "george oval 16 70";
    r110 = "R rectangle 1 10";
    r1050 = "R rectangle 10 50";
    r5051 = "R rectangle 50 51";
    r5170 = "R rectangle 51 70";
    r70100 = "R rectangle 70 100";
    c620 = "C oval 6 20";
    c2050 = "C oval 20 50";
    c5070 = "C oval 50 70";
    c7080 = "C oval 70 80";
    c80100 = "C oval 80 100";

    // positions
    positionConstant = " 0 0 0 0";
    positionInc = " 0 0 10 10";
    positionDec = " 10 10 -19 -345";
    constant200200 = " 200 200 200 200";
    constant300300 = " 300 300 300 300";
    constant44070 = " 440 70 440 70";
    constant440370 = " 440 370 440 370";
    change44070to440250 = " 440 70 440 250";
    change440250to440370 = " 440 250 440 370";
    change200200to300300 = " 200 200 300 300";
    change300300to200200 = " 300 300 200 200";

    // colors
    colorConstant = " 0 0 0 0 0 0";
    colorIncreasing = " 0 0 0 20 40 60";
    colorDecreasing = " 20 40 60 0 0 0";
    redConstant = " 255 0 0 255 0 0";
    blueContstant = " 0 0 255 0 0 255";
    brightGreentoGreen = " 0 170 85 0 255 0";
    greenConstant = " 0 255 0 0 255 0";
    blueToBrightGreen = " 0 0 255 0 170 85";

    // dimensions
    dimSquareIncreasing = " 10 10 20 20";
    dimSquareStaySame = " 10 10 10 10";
    constant50100 = " 50 100 50 100";
    decreaseWHalf = " 50 100 25 100";
    constant25100 = " 25 100 25 100";
    constant12060 = " 120 60 120 60";

    List<String> drawbob =
        new ArrayList<>(
            Arrays.asList(bob010 + positionConstant + colorConstant + dimSquareIncreasing,
                bob1020 + positionInc + colorIncreasing + dimSquareStaySame));
    List<String> drawgeorge = new ArrayList<>(Arrays.asList(george015 + positionInc +
        colorIncreasing + dimSquareStaySame, george1670 + positionDec +
        colorDecreasing + dimSquareIncreasing));
    List<String> drawR = new ArrayList<>(Arrays.asList(r110 + constant200200 +
            redConstant + constant50100, r1050 + change200200to300300 + redConstant + constant50100,
        r5051 + constant300300 + redConstant + constant50100, r5170 + constant300300 + redConstant
            + decreaseWHalf, r70100 + change300300to200200 + redConstant + constant25100));
    List<String> drawC = new ArrayList<>(Arrays.asList(c620 + constant44070 + blueContstant +
        constant12060, c2050 + change44070to440250 + blueContstant + constant12060, c5070 +
        change440250to440370 + blueToBrightGreen + constant12060, c7080 + constant440370 +
        brightGreentoGreen + constant12060, c80100 + constant440370 + greenConstant +
        constant12060));

    //simple
    simpCommands.put("bob", drawbob);

    simpModel.startAnimator(simpCommands);
    simpView = new SimpleAnimatorView(simpModel.getAllShapes());

    //multiple
    multCommands.put("bob", drawbob);
    multCommands.put("george", drawgeorge);

    multipleShapeModel.startAnimator(multCommands);
    multView = new SimpleAnimatorView(multipleShapeModel.getAllShapes());

    //notstarted
  //  notStartedView = new SimpleAnimatorView(notStartedModel.getAllShapes());

    //empty
    emptyModel.startAnimator(noCommands);
    noCommandsView = new SimpleAnimatorView(emptyModel.getAllShapes());

    //assignment example
    assignmentCommands.put("R", drawR);
    assignmentCommands.put("C", drawC);
    assignmentModel.startAnimator(assignmentCommands);
    classView = new SimpleAnimatorView(assignmentModel.getAllShapes());

  }

  //test to string method with a normal animator
  @Test
  public void toString1Shape() {
    examples();
    assertEquals("Shape bob rectangle\nmotion bob 0 0 0 10 10 0 0 0     10 0 0 20 20 "
        + "0 0 0\nmotion bob 11 0 0 10 10 0 0 0     20 10 10 10 10 20 40 60", simpView.toString());
  }

  //tests toString method with animator multiple shapes
  @Test
  public void toStringMultShapes() {
    examples();
    assertEquals("Shape bob rectangle\nmotion bob 0 0 0 10 10 0 0 0     10 0 0 20 20 "
        + "0 0 0\nmotion bob 11 0 0 10 10 0 0 0     20 10 10 10 10 20 40 60\n\n"
        + "Shape george oval\nmotion george 0 0 0 10 10 0 0 0     15 10 10 10 10 20 40 60\n"
        + "motion george 16 10 10 10 10 20 40 60     70 -19 -345 20 20 0 0 0", multView.toString());
  }

  //test to string method with no commands
  @Test
  public void toStringNoCommands() {
    examples();
    assertEquals("", noCommandsView.toString());
  }

  //tests toString method with same output as assignment example
  @Test
  public void toStringAssignmentExample() {
    examples();
    assertEquals("Shape R rectangle\nmotion R 1 200 200 50 100 255 0 0     10 200 200 50 "
        + "100 255 0 0\nmotion R 10 200 200 50 100 255 0 0     50 300 300 50 100 255 0 0\nmotion R "
        + "50 300 300 50 100 255 0 0     51 300 300 50 100 255 0 0\nmotion R 51 300 300 50 100 255 "
        + "0 0     70 300 300 25 100 255 0 0\nmotion R 70 300 300 25 100 255 0 0     100 200 200 25"
        + " 100 255 0 0\n\nShape C oval\nmotion C 6 440 70 120 60 0 0 255     20 440 70 120 60 0 0 "
        + "255\nmotion C 20 440 70 120 60 0 0 255     50 440 250 120 60 0 0 255\nmotion C 50 440 "
        + "250 120 60 0 0 255     70 440 370 120 60 0 170 85\nmotion C 70 440 370 120 60 0 170 85  "
        + "   80 440 370 120 60 0 255 0\nmotion C 80 440 370 120 60 0 255 0     100 440 370 120 60 "
        + "0 255 0", classView.toString());
  }

  //tests toString when game has not been started
  @Test(expected = IllegalStateException.class)
  public void testAnimationNotStarted() {
    examples();
    notStartedView.toString();
  }
}
