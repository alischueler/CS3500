
import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.TextualAnimatorView;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;

/**
 * a class to test the SimpleView class.
 */
public class SimpleViewTest {

  IAnimator<Shape> emptyModel;
  IAnimator<Shape> simpModel;
  IAnimator<Shape> multipleShapeModel;
  IAnimator<Shape> assignmentModel;

  TextualAnimatorView simpView;
  TextualAnimatorView multView;
  TextualAnimatorView noCommandsView;
  TextualAnimatorView classView;


  /**
   * a method that makes objects for testing.
   */
  protected void examples() {
    //a simple model with only one shape
    simpModel = new BasicAnimator.Builder()
        .declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 0, 0, 20, 20, 0, 0, 0)
        .addMotion("bob", 11, 0, 0, 20, 20, 0, 0, 0
            , 20, 10, 10, 10, 10, 20, 40, 60)
        .setBounds(500, 500, 500, 500).build();
    simpModel.startAnimator();
    simpView = new TextualAnimatorView(new ShapeViewModel(simpModel), 1);

    //a multiple shape model
    multipleShapeModel = new BasicAnimator.Builder()
        .declareShape("jeff", "rectangle")
        .addMotion("jeff", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 0, 0, 20, 20, 0, 0, 0)
        .addMotion("jeff", 11, 0, 0, 20, 20, 0, 0, 0
            , 20, 10, 10, 10, 10, 20, 40, 60)
        .setBounds(500, 500, 500, 500).addMotion("george",
            0, 0, 0, 10, 10, 0, 0, 0, 15, 10, 10, 10,
            10, 20, 40, 60).declareShape("george", "ellipse")
        .addMotion("george", 16, 10, 10, 10, 10, 20, 40, 60,
            70, -19, -345, 20, 20, 0, 0, 0).build();
    multipleShapeModel.startAnimator();
    multView = new TextualAnimatorView(new ShapeViewModel(multipleShapeModel), 2);

    //assignment text box model
    assignmentModel = new BasicAnimator.Builder()
        .declareShape("R", "rectangle")
        .addMotion("R", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("R", 10, 200, 200, 50, 100, 255, 0, 0
            , 50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("R", 70, 300, 300, 25, 100, 255, 0, 0,
            100, 200, 200, 25, 100, 255, 0, 0).addMotion("R",
            50, 300, 300, 50, 100, 255, 0, 0, 51, 300,
            300, 50, 100, 255, 0, 0).addMotion("R", 51, 300,
            300, 50, 100, 255, 0, 0, 70, 300, 300, 25,
            100, 255, 0, 0)
        .setBounds(200, 70, 360, 360).addMotion("C",
            6, 440, 70, 120, 60, 0, 0, 255, 20, 440, 70, 120,
            60, 0, 0, 255).declareShape("C", "ellipse")
        .addMotion("C", 80, 440, 370, 120, 60, 0, 255, 0,
            100, 440, 370, 120, 60, 0, 255, 0)
        .addMotion("C", 20, 440, 70, 120, 60, 0, 0, 255,
            50, 440, 250, 120, 60, 0, 0, 255).addMotion("C",
            50, 440, 250, 120, 60, 0, 0, 255, 70, 440,
            370, 120, 60, 0, 170, 85).addMotion("C", 70, 440,
            370, 120, 60, 0, 170, 85, 80, 440, 370, 120,
            60, 0, 255, 0).build();
    assignmentModel.startAnimator();
    classView = new TextualAnimatorView(new ShapeViewModel(assignmentModel), 5);

    //no commands in the model
    emptyModel = new BasicAnimator.Builder().setBounds(40, 60, 100, 300)
        .addMotion("one", 14, 3, 4, 2, 8, 4, 2, 9, 20,
            3, 6, 8, 2, 4, 5, 3)
        .declareShape("two", "rectangle").build();
    emptyModel.startAnimator();
    noCommandsView = new TextualAnimatorView(new ShapeViewModel(emptyModel), 3);
  }


  //test to string method with a normal animator
  @Test
  public void toString1Shape() {
    examples();
    assertEquals("canvas 500 500 500 500\nshape bob rectangle\nmotion bob 0.00 0 0 10 "
        + "10 0 0 0     10.00 0 0 20 20 0 0 0\nmotion bob 10.00 0 0 20 20"
        + " 0 0 0     11.00 0 0 20 20 0 0 0\n"
        + "motion bob 11.00 0 0 20 20 "
        + "0 0 0     20.00 10 10 10 10 20 40 60\n", simpView.toString());
  }

  //tests toString method with animator multiple shapes
  @Test
  public void toStringMultShapes() {
    examples();
    assertEquals("canvas 500 500 500 500\nshape jeff rectangle\nmotion jeff 0.00 0 0 "
        + "10 10 0 0 0     5.00 0 0 20 20 0 0 0\nmotion jeff 5.00 0 0 "
        + "20 20 0 0 0     5.50 0 0 20 20 0 0 0"
        + "\nmotion jeff 5.50 0 0 "
        + "20 20 0 0 0     10.00 10 10 10 10 20 40 60\n\nshape george ellipse\n"
        + "motion george 0.00 0 0 10 10 0 0 0     7.50 10 10 10 10 20 40 60"
        + "\nmotion george 7.50 10 10 10 10 20 40 60     8.00 10 10 10 "
        + "10 20 40 60\nmotion george 8.00 10 10 10 10 20 40 60     35.00 "
        + "-19 -345 20 20 0"
        + " 0 0\n", multView.toString());
  }

  //test to string method with no commands
  @Test
  public void toStringNoCommands() {
    examples();
    assertEquals("canvas 40 60 100 300\nshape two rectangle\n", noCommandsView.toString());
  }

  //tests toString method with same output as assignment example
  @Test
  public void toStringAssignmentExample() {
    examples();
    assertEquals("canvas 200 70 360 360\nshape R rectangle\nmotion R 0.20 200 200 50 "
        + "100 255 0 0     2.00 200 200 50 "
        + "100 255 0 0\nmotion R 2.00 200 200 50 100 255 0 0     10.00 300 "
        + "300 50 100 255 0 0\nmotion R "
        + "10.00 300 300 50 100 255 0 0     10.20 300 300 50 100 255 0 0"
        + "\nmotion R 10.20 300 300 50 100 255 "
        + "0 0     14.00 300 300 25 100 255 0 0\nmotion R 14.00 300 300 25 100 "
        + "255 0 0     20.00 200 200 25"
        + " 100 255 0 0\n\nshape C ellipse\nmotion C 1.20 440 70 120 60 0 0 255"
        + "     4.00 440 70 120 60 0 0 "
        + "255\nmotion C 4.00 440 70 120 60 0 0 255     10.00 440 250 120 60 "
        + "0 0 255\nmotion C 10.00 440 "
        + "250 120 60 0 0 255     14.00 440 370 120 60 0 170 85\nmotion C "
        + "14.00 440 370 120 60 0 170 85  "
        + "   16.00 440 370 120 60 0 255 0\nmotion C 16.00 440 370 120 60 0 "
        + "255 0     20.00 440 370 120 60 "
        + "0 255 0\n", classView.toString());
  }

  //testting the rendering of the textual view
  @Test
  public void testRender() {
    examples();
    Appendable ap = new StringBuilder();
    try {
      classView.render(ap);
      assertEquals("canvas 200 70 360 360\nshape R rectangle\nmotion R 0.20 200 200 50 "
          + "100 255 0 0     2.00 200 200 50 "
          + "100 255 0 0\nmotion R 2.00 200 200 50 100 255 0 0     10.00 300 "
          + "300 50 100 255 0 0\nmotion R "
          + "10.00 300 300 50 100 255 0 0     10.20 300 300 50 100 255 0 0"
          + "\nmotion R 10.20 300 300 50 100 255 "
          + "0 0     14.00 300 300 25 100 255 0 0\nmotion R 14.00 300 300 25 100 "
          + "255 0 0     20.00 200 200 25"
          + " 100 255 0 0\n\nshape C ellipse\nmotion C 1.20 440 70 120 60 0 0 255"
          + "     4.00 440 70 120 60 0 0 "
          + "255\nmotion C 4.00 440 70 120 60 0 0 255     10.00 440 250 120 60 "
          + "0 0 255\nmotion C 10.00 440 "
          + "250 120 60 0 0 255     14.00 440 370 120 60 0 170 85\nmotion C "
          + "14.00 440 370 120 60 0 170 85  "
          + "   16.00 440 370 120 60 0 255 0\nmotion C 16.00 440 370 120 60 0 "
          + "255 0     20.00 440 370 120 60 "
          + "0 255 0\n", ap.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("cannot append output");
    }
  }

  //testting the rendering of the textual view
  @Test
  public void tesWithStringWriter() {
    examples();
    Appendable ap = new StringWriter();
    try {
      classView.render(ap);
      assertEquals("canvas 200 70 360 360\nshape R rectangle\nmotion R 0.20 200 200 50 "
          + "100 255 0 0     2.00 200 200 50 "
          + "100 255 0 0\nmotion R 2.00 200 200 50 100 255 0 0     10.00 300 "
          + "300 50 100 255 0 0\nmotion R "
          + "10.00 300 300 50 100 255 0 0     10.20 300 300 50 100 255 0 0"
          + "\nmotion R 10.20 300 300 50 100 255 "
          + "0 0     14.00 300 300 25 100 255 0 0\nmotion R 14.00 300 300 25 100 "
          + "255 0 0     20.00 200 200 25"
          + " 100 255 0 0\n\nshape C ellipse\nmotion C 1.20 440 70 120 60 0 0 255"
          + "     4.00 440 70 120 60 0 0 "
          + "255\nmotion C 4.00 440 70 120 60 0 0 255     10.00 440 250 120 60 "
          + "0 0 255\nmotion C 10.00 440 "
          + "250 120 60 0 0 255     14.00 440 370 120 60 0 170 85\nmotion C "
          + "14.00 440 370 120 60 0 170 85  "
          + "   16.00 440 370 120 60 0 255 0\nmotion C 16.00 440 370 120 60 0 "
          + "255 0     20.00 440 370 120 60 "
          + "0 255 0\n", ap.toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("cannot append output");
    }
  }

  //tests setting loop throws an exception
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLoop() {
    examples();
    simpView.setLoop(true);
  }

}
