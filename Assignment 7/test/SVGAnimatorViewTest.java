import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.ShapeViewModel;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;

/**
 * a class for testing the SVGAnimatorView.
 */
public class SVGAnimatorViewTest {

  AnimationBuilder<IAnimator<Shape>> emptyBuilder;

  TestLambda makeBob;
  TestLambda bob010Inc;
  TestLambda bob1020Inc;

  TestLambda makeJeff;
  TestLambda jeff010Inc;
  TestLambda jeff1020Inc;

  TestLambda makeStaySameRect;
  TestLambda staySameRect010;
  TestLambda staySameRect1020;

  TestLambda makeNoTimeGap;
  TestLambda noTimeGap11;

  TestLambda bobDelayed;


  /**
   * a FunctionalInterface that allows for the stacking of commands. for builders allows for easy.
   * creation of IAnimators. allows for switching and swapping the order/commands used.
   */
  @FunctionalInterface
  private interface TestLambda {

    /**
     * method to run the TestLambda.
     *
     * @param b the builder to add the command to
     * @return the passed builder which is mutated
     */
    AnimationBuilder<IAnimator<Shape>> apply(AnimationBuilder<IAnimator<Shape>> b);
  }

  /**
   * a method that makes objects for testing.
   */
  protected void examples() {

    emptyBuilder = new BasicAnimator.Builder().setBounds(0, 0, 500, 300);

    makeBob = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("bob", "rectangle");
      return builder;
    };
    bob010Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
          10, 10, 10, 20, 20, 20, 20, 20);
      return builder;
    };
    bob1020Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("bob", 10, 10, 10, 20, 20, 20, 20,
          20, 20, 20, 30, 40, 30, 120, 100, 80);
      return builder;
    };

    makeJeff = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("jeff", "ellipse");
      return builder;
    };
    jeff010Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("jeff", 0, 0, 0, 10, 10, 0, 0,
          0, 10, 10, 10, 20, 20, 20, 20, 20);
      return builder;
    };
    jeff1020Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("jeff", 10, 10, 10, 20, 20, 20, 20,
          20, 20, 20, 30, 40, 30, 120, 100, 80);
      return builder;
    };

    // shapes that stay in the same place
    makeStaySameRect = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("SSR", "ellipse");
      return builder;
    };

    staySameRect010 = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("SSR", 0, 10, 10, 10, 10, 10, 10,
          10, 10, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };
    staySameRect1020 = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("SSR", 10, 10, 10, 10, 10, 10, 10,
          10, 20, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };

    makeNoTimeGap = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("NoTimeGap", "ellipse");
      return builder;
    };

    noTimeGap11 = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("NoTimeGap", 1, 10, 10, 10, 10, 10, 10,
          10, 1, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };

    bobDelayed = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("SSR", 5, 10, 10, 10, 10, 10, 10,
          10, 10, 10, 10, 10, 10, 10, 10, 10);
      return builder;
    };
  }


  /**
   * tempo at 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addCommandNull() throws IOException {
    examples();
    makeSVGCode(emptyBuilder, 0, true);
  }


  /**
   * negative tempo.
   */
  @Test(expected = IllegalArgumentException.class)
  public void negativeTempo() throws IOException {
    examples();
    makeSVGCode(emptyBuilder, -1, true);
  }

  /**
   * null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void nullModel() throws IOException {
    new SVGAnimatorView(null, 1);
  }

  /**
   * shape multiple commands nothing changes.
   */
  @Test
  public void noMovement() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        staySameRect1020.apply(staySameRect010.apply(makeStaySameRect.apply(emptyBuilder)));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\""
            + " xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" "
            + "begin=\"0;base.end\" dur=\"20000.0ms\" attributeName=\"visibility\" "
            + "from=\"hide\" to=\"hide\"/></rect><ellipse id=\"SSR\" cx=\"10\" cy=\"10\" "
            + "rx=\"10\" ry=\"10\" fill=\"transparent\" visibility=\"visible\" ><animate"
            + " attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"cx\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate"
            + " attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"cy\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"ry\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\""
            + " attributeName=\"rx\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(10,10,10)\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base."
            + "begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"10\" "
            + "to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+"
            + "10000.00ms\" dur=\"10000.00ms\" attributeName=\"cy\" from=\"10\" to=\"10\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" "
            + "dur=\"10000.00ms\" attributeName=\"ry\" from=\"10\" to=\"10\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"rx\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(10,10,10)\" fill=\""
            + "freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * shae with same start and end time stacked.
   */
  @Test
  public void stackedMovements() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = noTimeGap11
        .apply(makeNoTimeGap.apply(emptyBuilder));
    assertEquals(makeSVGCode(builder, 1, true), "<svg width=\"500\" "
        + "height=\"300\" version=\"1"
        + ".1\" xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base"
        + ".end\" dur=\"1000.0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\""
        + "/></rect><ellipse id=\"NoTimeGap\" cx=\"10\" cy=\"10\" rx=\"10\" ry=\"10\" fill"
        + "=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" begin=\""
        + "base.begin+1000.00ms\" dur=\"0.00ms\" attributeName=\"cx\" from=\"10\" to=\"10\" f"
        + "ill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+1000.00ms\" "
        + "dur=\"0.00ms\" attributeName=\"cy\" from=\"10\" to=\"10\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.begin+1000.00ms\" dur=\"0.00ms\" "
        + "attributeName=\"ry\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+1000.00ms\" dur=\"0.00ms\" "
        + "attributeName=\"rx\" from=\"10\" to=\"10\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+1000.00ms\" dur=\"0.00ms\" "
        + "attributeName=\"fill\" from=\"rgb(10,10,10)\" to=\"rgb(10,10,10)\" "
        + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" "
        + "dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" "
        + "/></ellipse></svg>");
  }

  /**
   * empty model.
   */
  @Test
  public void emptyModel() throws IOException {
    examples();
    assertEquals(makeSVGCode(emptyBuilder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" "
            + "begin=\"0;base.end\" dur=\"0.0ms\" attributeName=\"visibility\" "
            + "from=\"hide\" to=\"hide\"/></rect></svg>");
  }

  /**
   * one shape delayed out of two shapes.
   */
  @Test
  public void delayedStartOneMultShape() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        jeff1020Inc.apply(makeJeff.apply(bob1020Inc.apply(bobDelayed.apply(emptyBuilder))));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" begin=\"0"
            + ";base.end\" dur=\"20000.0ms\" attributeName=\"visibility\" from=\"hide\" "
            + "to=\"hide\"/></rect><ellipse id=\"jeff\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"20\""
            + " fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" "
            + "begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\""
            + "10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.be"
            + "gin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cy\" from=\"10\" to=\"30\" fil"
            + "l=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" du"
            + "r=\"10000.00ms\" attributeName=\"ry\" from=\"20\" to=\"30\" fill=\"freeze\" /><an"
            + "imate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attr"
            + "ibuteName=\"rx\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate attributeType=\""
            + "xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" f"
            + "rom=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attribute"
            + "Type=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" "
            + "to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * one shape delayed out of one shape.
   */
  @Test
  public void delayedStartOneShape() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        bob1020Inc.apply(bobDelayed.apply(emptyBuilder));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns="
            + "\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\""
            + " dur=\"0.0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></r"
            + "ect></svg>");
  }

  /**
   * two shapes delayed out of two shapes.
   */
  @Test
  public void twoDelayed() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        jeff1020Inc.apply(makeJeff.apply(bob1020Inc.apply(bobDelayed.apply(emptyBuilder))));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns="
            + "\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\""
            + " begin=\"0;base.end\" dur=\"20000.0ms\" attributeName=\"visibility\" from=\"hide\""
            + " to=\"hide\"/></rect><ellipse id=\"jeff\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"20\""
            + " fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" "
            + "begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\""
            + "10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cy\" from=\"10\""
            + " to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"ry\" from=\"20\""
            + " to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"rx\" from=\"20\""
            + " to=\"40\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\""
            + "rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attributeType="
            + "\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\""
            + " fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * overlay works correctly with two shapes that are over each other.
   */

  @Test
  public void overlayWorksCorrectly() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        jeff1020Inc.apply(makeJeff.apply(bob1020Inc.apply(bobDelayed.apply(emptyBuilder))));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2000"
            + "/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"20000.0ms\" "
            + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><ellipse id="
            + "\"jeff\" cx=\"10\" cy=\"10\" rx=\"20\" ry=\"20\" fill=\"transparent\" vi"
            + "sibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+"
            + "10000.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"10\" to=\"20\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms"
            + "\" dur=\"10000.00ms\" attributeName=\"cy\" from=\"10\" to=\"30\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\""
            + " attributeName=\"ry\" from=\"20\" to=\"30\" fill=\"freeze\" /><animate attributeT"
            + "ype=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName="
            + "\"rx\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate attributeType=\"xml\""
            + " begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\""
            + " from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate"
            + " attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\""
            + " to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * one rect one command tempo one one rect two command tempo one.
   */
  @Test
  public void oneRectOneCommand() throws IOException {
    examples();

    AnimationBuilder<IAnimator<Shape>> builder = bob010Inc.apply(makeBob.apply(emptyBuilder));
    assertEquals(makeSVGCode(builder, 1, true), "<svg width=\"500\" height=\"300\""
        + " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" "
        + "begin=\"0;base.end\" dur=\"10000.0ms\" attributeName=\"visibility\" from=\"hide\" "
        + "to=\"hide\"/></rect><rect id=\"bob\" x=\"0\" y=\"0\" width=\"10\" height=\"10\" "
        + "fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" begin=\""
        + "base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"x\" from=\"0\" to=\"10\" "
        + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\""
        + "10000.00ms\" attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\""
        + "height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" "
        + "begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"width\" from=\"10\""
        + " to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
        + " dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" "
        + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
        + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></rect></svg>");
  }

  /**
   * one rect one command tempo one one rect two command tempo one.
   */
  @Test
  public void oneRectOneCommand2() throws IOException {
    examples();

    AnimationBuilder<IAnimator<Shape>> builder = bob010Inc.apply(makeBob.apply(emptyBuilder));
    assertEquals(makeSVGCode(bob1020Inc.apply(builder), 1, true), "<svg width=\"500\" "
        + "height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate "
        + "id=\"base\" begin=\"0;base.end\" dur=\"20000.0ms\" attributeName=\"visibility\" "
        + "from=\"hide\" to=\"hide\"/></rect><rect id=\"bob\" x=\"0\" y=\"0\" width=\"10\" "
        + "height=\"10\" fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\""
        + "xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"x\" from=\"0\" "
        + "to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
        + " dur=\"10000.00ms\" attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attribute"
        + "Type=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"width\" "
        + "from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.beg"
        + "in+0.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20"
        + ",20)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms"
        + "\" dur=\"10000.00ms\" attributeName=\"x\" from=\"10\" to=\"20\" fill=\"freeze\" /><an"
        + "imate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attribut"
        + "eName=\"y\" from=\"10\" to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" beg"
        + "in=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"height\" from=\"20\" "
        + "to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000."
        + "00ms\" dur=\"10000.00ms\" attributeName=\"width\" from=\"20\" to=\"40\" fill=\"freeze\""
        + " /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"fill\" from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" "
        + "to=\"transparent\" fill=\"freeze\" /></rect></svg>");
  }

  /**
   * one rect two command tempo no looping.
   */
  @Test
  public void oneRectTwoCommandLoop() throws IOException {
    examples();

    AnimationBuilder<IAnimator<Shape>> builder =
        bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder)));

    assertEquals(makeSVGCode(builder, 1, false),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\""
            + "http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" "
            + "begin=\"0;base.end\" dur=\"20000.0ms\" attributeName=\"visibility\""
            + " from=\"hide\" to=\"hide\"/></rect><rect id=\"bob\" x=\"0\" y=\"0\" "
            + "width=\"10\" height=\"10\" fill=\"transparent\" visibility=\"visible\" ><animate "
            + "attributeType=\"xml\" begin=\"0.00ms\" dur=\"10000.00ms\" attributeName=\"x\""
            + " from=\"0\" to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "0.00ms\" dur=\"10000.00ms\" attributeName=\"y\" from=\"0\" to=\"10\" fill=\"free"
            + "ze\" /><animate attributeType=\"xml\" begin=\"0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"0.00ms\" dur=\"10000.00ms\" attributeName=\"width\" "
            + "from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "0.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\""
            + "rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "10000.00ms\" dur=\"10000.00ms\" attributeName=\"x\" from=\"10\" to=\"20\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"10000.00ms\" "
            + "dur=\"10000.00ms\" attributeName=\"y\" from=\"10\" to=\"30\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"height\" from=\"20\" to=\"30\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"10000.00ms\" dur=\"10000.00ms\" attributeName=\""
            + "width\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate attributeType=\"xml\" "
            + "begin=\"10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(20,20,20)"
            + "\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin="
            + "\"base.end\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" "
            + "/></rect></svg>");
  }

  /**
   * one ell one command tempo one one ell two command tempo one.
   */
  @Test
  public void onrElltOneCommand1() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc.apply(makeJeff.apply(emptyBuilder));
    assertEquals(makeSVGCode(builder, 1, true), "<svg width=\"500\" height=\"300\" version="
        + "\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base."
        + "end\" dur=\"10000.0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><"
        + "ellipse id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" fill=\"transparent\" visibil"
        + "ity=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" "
        + "dur=\"10000.00ms\" attributeName=\"cx\" from=\"0\" to=\"10\" fill=\"freeze\" /"
        + "><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" /><animate attributeType="
        + "\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"ry\" from=\""
        + "10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+"
        + "0.00ms\" dur=\"10000.00ms\" attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\""
        + "fill\" to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * one ell one command tempo one one ell two command tempo one.
   */
  @Test
  public void onrElltOneCommand2() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc.apply(makeJeff.apply(emptyBuilder));
    assertEquals(makeSVGCode(jeff1020Inc.apply(builder), 1, true), "<svg width=\"500\" "
        + "height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate "
        + "id=\"base\" begin=\"0;base.end\" dur=\"20000.0ms\" attributeName=\"visibility\" "
        + "from=\"hide\" to=\"hide\"/></rect><ellipse id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" r"
        + "y=\"10\" fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" "
        + "begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"0\" to=\""
        + "10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" "
        + "dur=\"10000.00ms\" attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType="
        + "\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"rx\" from=\"10\" "
        + "to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" "
        + "dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill="
        + "\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\""
        + "10000.00ms\" attributeName=\"cx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"cy\" from=\"10\" to=\"30\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"ry\" from=\"20\" to=\"30\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"rx\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"fill\" from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" "
        + "to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * two shapes in the view one rectangle and one ellipse both are increasing over everything more.
   * than one command
   */
  @Test
  public void twoShapeIncMultCom() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));

    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg"
            + "\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"20000.0ms\" "
            + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><rect "
            + "id=\"bob\" x=\"0\" y=\"0\" width=\"10\" height=\"10\" fill=\"transparent\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" "
            + "dur=\"10000.00ms\" attributeName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" />"
            + "<animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\""
            + "freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" "
            + "dur=\"10000.00ms\" attributeName=\"x\" from=\"10\" to=\"20\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"y\" from=\"10\" to=\"30\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"height\" from=\"20\" to=\"30\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"width\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\""
            + "freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></rect><ellips"
            + "e id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" fill=\"transparent\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0"
            + ".00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"0\" to=\"10\" fill=\""
            + "freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\""
            + "10000.00ms\" attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" /><"
            + "animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attribute"
            + "Type=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\""
            + "rx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" "
            + "begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\""
            + "rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\""
            + "xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" "
            + "from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"cy\" from=\"10\" to=\""
            + "30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms"
            + "\" dur=\"10000.00ms\" attributeName=\"ry\" from=\"20\" to=\"30\" fill=\"freeze\" />"
            + "<animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\""
            + " attributeName=\"rx\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate attributeType"
            + "=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" "
            + "from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attribute"
            + "Type=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" "
            + "to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * two shapes in the view one rectangle and one ellipse both are increasing over everything more.
   * than one command HIGH TEMPO
   */
  @Test
  public void testRender() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));

    assertEquals(makeSVGCode(builder, 20, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\""
            + "http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" begin="
            + "\"0;base.end\" dur=\"1000.0ms\" attributeName=\"visibility\" from="
            + "\"hide\" to=\"hide\"/></rect><rect id=\"bob\" x=\"0\" y=\"0\" width=\"10\" "
            + "height=\"10\" fill=\"transparent\" visibility=\"visible\" ><animate attributeType="
            + "\"xml\" begin=\"base.begin+0.00ms\" dur=\"500.00ms\" attributeName=\"x\" from=\"0\" "
            + "to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms"
            + "\" dur=\"500.00ms\" attributeName=\"y\" from=\"0\" to=\"10\" fill=\"freeze\" />"
            + "<animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"500.00ms\" "
            + "attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"500.00ms\" attributeName="
            + "\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" "
            + "begin=\"base.begin+0.00ms\" dur=\"500.00ms\" attributeName=\"fill\" from=\""
            + "rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\""
            + "xml\" begin=\"base.begin+500.00ms\" dur=\"500.00ms\" attributeName=\"x\" "
            + "from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" "
            + "begin=\"base.begin+500.00ms\" dur=\"500.00ms\" attributeName=\"y\" "
            + "from=\"10\" to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" "
            + "begin=\"base.begin+500.00ms\" dur=\"500.00ms\" attributeName=\"height\" f"
            + "rom=\"20\" to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+500.00ms\" dur=\"500.00ms\" attributeName=\"width\" from=\"20\" "
            + "to=\"40\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+500"
            + ".00ms\" dur=\"500.00ms\" attributeName=\"fill\" from=\"rgb(20,20,20)\" to=\""
            + "rgb(120,100,80)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base."
            + "end\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" />"
            + "</rect><ellipse id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" fill=\"trans"
            + "parent\" visibility=\"visible\" ><animate attributeType=\"xml\" begin=\""
            + "base.begin+0.00ms\" dur=\"500.00ms\" attributeName=\"cx\" from=\"0\" to=\"10\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" "
            + "dur=\"500.00ms\" attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" />"
            + "<animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"500.00ms\" "
            + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"500.00ms\" "
            + "attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"500.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" />"
            + "<animate attributeType=\"xml\" begin=\"base.begin+500.00ms\" dur=\"500.00ms\" "
            + "attributeName=\"cx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType"
            + "=\"xml\" begin=\"base.begin+500.00ms\" dur=\"500.00ms\" attributeName=\"cy\" from=\""
            + "10\" to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.begin+500.00ms\" dur=\"500.00ms\" attributeName=\"ry\" from=\"20\" "
            + "to=\"30\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base."
            + "begin+500.00ms\" dur=\"500.00ms\" attributeName=\"rx\" from=\"20\" to=\"40\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+500.00ms\" "
            + "dur=\"500.00ms\" attributeName=\"fill\" from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)"
            + "\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * negative positions one ellipse one rect.
   */
  @Test
  public void negPositionsFromBounds() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc
        .apply(makeJeff.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))));
    builder.setBounds(400, 200, 500, 500);

    assertEquals(makeSVGCode(builder, 1, true), "<svg width=\"500\" height=\"500\" "
        + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" "
        + "begin=\"0;base.end\" dur=\"10000.0ms\" attributeName=\"visibility\" from=\"hide\" "
        + "to=\"hide\"/></rect><rect id=\"bob\" x=\"0\" y=\"0\" width=\"10\" height=\"10\" "
        + "fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" "
        + "begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"x\" from=\"-400\" "
        + "to=\"-390\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0"
        + ".00ms\" dur=\"10000.00ms\" attributeName=\"y\" from=\"-200\" to=\"-190\" "
        + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" "
        + "dur=\"10000.00ms\" attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" "
        + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\""
        + "fill\" to=\"transparent\" fill=\"freeze\" /></rect><ellipse id=\"jeff\" cx=\"0\" "
        + "cy=\"0\" rx=\"10\" ry=\"10\" fill=\"transparent\" visibility=\"visible\" "
        + "><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"cx\" from=\"-400\" to=\"-390\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"cy\" from=\"-200\" to=\"-190\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
        + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
        + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\""
        + "freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
        + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");

  }

  /**
   * negative bounds.
   */
  @Test
  public void negBounds() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc
        .apply(makeJeff.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))));
    builder.setBounds(-400, -200, 500, 500);

    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3"
            + ".org/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"10000.0ms\""
            + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><rect id=\"bob\" "
            + "x=\"0\" y=\"0\" width=\"10\" height=\"10\" fill=\"transparent\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
            + " dur=\"10000.00ms\" attributeName=\"x\" from=\"400\" to=\"410\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"210\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></rect><ellipse "
            + "id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" fill=\"transparent\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
            + " dur=\"10000.00ms\" attributeName=\"cx\" from=\"400\" to=\"410\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"cy\" from=\"200\" to=\"210\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /**
   * testing with StringBuilder.
   */
  @Test
  public void diffWriteAppendable() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc
        .apply(makeJeff.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))));
    builder.setBounds(-400, -200, 500, 500);

    IAnimator<Shape> model = builder.build();
    model.startAnimator();
    IAnimatorView view = new SVGAnimatorView(new ShapeViewModel(model), 1);
    Appendable sb = new StringWriter();
    view.render(sb);

    assertEquals(sb.toString(),
        "<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www.w3"
            + ".org/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"10000.0ms\""
            + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><rect id=\"bob\" "
            + "x=\"0\" y=\"0\" width=\"10\" height=\"10\" fill=\"transparent\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
            + " dur=\"10000.00ms\" attributeName=\"x\" from=\"400\" to=\"410\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"210\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></rect><ellipse "
            + "id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" fill=\"transparent\" "
            + "visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
            + " dur=\"10000.00ms\" attributeName=\"cx\" from=\"400\" to=\"410\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"cy\" from=\"200\" to=\"210\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"ry\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" "
            + "/><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }

  /*
   * render :
   * view with one shape increasing everything no loop
   * view with one shape increasing everything loop
   * view with more than one shape increasing everything no loop
   * view with more than one shape increasing everything loop
   * view increasing all but one thing loop
   * view increasing all but one thing no loop
   * view nothing changing loop
   * view nothing changing no loop
   * shape stays hidden until its first tick
   * shape goes off the canvas
   * overlaying the first shape below second shape
   *
   * view with the tempo > 10
   * view with the tempo == 1
   * view with empty model no loop
   * view with empty model loop
   *
   *
   * exceptions:
   * view with negative tempo (currently dont support reverse)
   * view with tempo 0
   * view with null model
   *
   */

  /**
   * takes in a builder and a tick makes a SVG animator. then calls render on the view and returns.
   * the svg code of the view indirectly tests set loop
   *
   * @param builder builder for a model
   * @param tempo   tempo for model
   * @return string of svg code
   * @throws IOException if the append fails
   */
  private String makeSVGCode(AnimationBuilder<IAnimator<Shape>> builder, int tempo, boolean loop)
      throws IOException {
    IAnimator<Shape> model = builder.build();
    model.startAnimator();
    IAnimatorView view = new SVGAnimatorView(new ShapeViewModel(model), tempo);
    view.setLoop(loop);
    StringBuilder sb = new StringBuilder();
    view.render(sb);
    return sb.toString();
  }

  //tests setting loop does not throw an exception
  @Test
  public void testSetLoop() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff010Inc
        .apply(makeJeff.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))));
    builder.setBounds(-400, -200, 500, 500);

    IAnimator<Shape> model = builder.build();
    model.startAnimator();
    IAnimatorView view = new SVGAnimatorView(new ShapeViewModel(model), 1);
    view.setLoop(true);
    StringBuilder sb = new StringBuilder();
    view.render(sb);
    assertEquals(sb.toString(), "<svg width=\"500\" height=\"500\" version=\"1.1\" "
        + "xmlns=\"http://www.w3.org/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base.e"
        + "nd\" dur=\"10000.0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></re"
        + "ct><rect id=\"bob\" x=\"0\" y=\"0\" width=\"10\" height=\"10\" fill=\"transparent\" v"
        + "isibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur"
        + "=\"10000.00ms\" attributeName=\"x\" from=\"400\" to=\"410\" fill=\"freeze\" /><anima"
        + "te attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName"
        + "=\"y\" from=\"200\" to=\"210\" fill=\"freeze\" /><animate attributeType=\"xml\" begin="
        + "\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"height\" from=\"10\" to=\"20\""
        + " fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"100"
        + "00.00ms\" attributeName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate at"
        + "tributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"fi"
        + "ll\""
        + " from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\""
        + "xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fill=\"fr"
        + "eeze\" /></rect><ellipse id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" fill=\"tr"
        + "ansparent\" visibility=\"visible\" ><animate attributeType=\"xml\" begin=\"base.begin+"
        + "0.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"400\" to=\"410\" fill=\"free"
        + "ze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" at"
        + "tributeName=\"cy\" from=\"200\" to=\"210\" fill=\"freeze\" /><animate attributeType=\""
        + "xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"ry\" from=\"10\""
        + " to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.0"
        + "0ms\" dur=\"10000.00ms\" attributeName=\"rx\" from=\"10\" to=\"20\" fill=\"freeze\" /"
        + "><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" at"
        + "tributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" /><"
        + "animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" t"
        + "o=\"transparent\" fill=\"freeze\" /></ellipse></svg>");
  }


}