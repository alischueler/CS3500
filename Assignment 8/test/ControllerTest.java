import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.TextualAnimatorView;
import cs3500.animator.view.VisualAnimationView;
import cs3500.animator.view.VisualJPanel;
import java.io.IOException;
import org.junit.Test;

/**
 * tests the controller.
 */
public class ControllerTest {

  AnimationBuilder<IAnimator<Shape>> emptyBuilder;

  TestLambda makeBob;
  TestLambda bob010Inc;
  TestLambda bob1020Inc;

  TestLambda makeJeff;
  TestLambda jeff010Inc;
  TestLambda jeff1020Inc;

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
   * examples for testing.
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


  }

  /**
   * takes in a builder and a tick makes a SVG animator. then calls render on the view and returns.
   * the svg code of the view indirectly tests set loop
   *
   * @param builder builder for a model
   * @param tempo   tempo for model
   * @return string of svg code
   * @throws IOException if the append fails
   */
  private String makeControllerOut(AnimationBuilder<IAnimator<Shape>> builder, int tempo, int type)
      throws IOException {
    IAnimator<Shape> model = builder.build();
    IAnimatorView view;

    if (type == 0) {
      view = new SVGAnimatorView(new ShapeViewModel(model), tempo);
    } else {
      view = new TextualAnimatorView(new ShapeViewModel(model), tempo);
    }
    model.startAnimator();
    IController cont = getControler(model, view);
    StringBuilder sb = new StringBuilder();
    cont.startAnimate(sb);
    return sb.toString();
  }

  public IController getControler(IAnimator<Shape> model, IAnimatorView view) {
    return new Controller(model, view);
  }


  // view is interactive
  @Test(expected = IllegalArgumentException.class)
  public void testInteractive() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));
    IAnimator<Shape> model = builder.build();
    IAnimatorView view = new InteractiveAnimationView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);

    IController cont = new Controller(model, view);

  }

  // view is visual
  @Test(expected = IllegalArgumentException.class)
  public void testVisual() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));
    IAnimator<Shape> model = builder.build();
    IAnimatorView view = new VisualAnimationView(new ShapeViewModel(model), 3);

    IController cont = new Controller(model, view);

  }

  // null model and view
  @Test(expected = IllegalArgumentException.class)
  public void testNullModelView() {
    examples();
    new Controller(null, null);
  }

  // null model
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));
    IAnimator<Shape> model = builder.build();
    IAnimatorView view = new TextualAnimatorView(new ShapeViewModel(model), 1);
    IController cont = new Controller(null, view);
  }

  // null view
  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));
    IAnimator<Shape> model = builder.build();

    IController cont = new Controller(model, null);
  }

  // appendable is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));
    IAnimator<Shape> model = builder.build();
    IAnimatorView view = new TextualAnimatorView(new ShapeViewModel(model), 1);

    IController cont = new Controller(model, view);
    StringBuilder sb = new StringBuilder();
    cont.startAnimate(null);
  }

  /**
   * two shapes in the view one rectangle and one ellipse both are increasing over everything more.
   * than one command svg
   */
  @Test
  public void twoShapeIncMultComSVG() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));

    assertEquals(makeControllerOut(builder, 1, 0),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/"
            + "svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"20000.0ms\" attributeN"
            + "ame=\"visibility\" from=\"hide\" to=\"hide\"/></rect><rect id=\"bob\" x=\"0\" y=\""
            + "0\" width=\"10\" height=\"10\" fill=\"transparent\" visibility=\"visible\" ><anima"
            + "te attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeNa"
            + "me=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begi"
            + "n=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"y\" from=\"0\" to=\"10\" "
            + "fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\""
            + "10000.00ms\" attributeName=\"height\" from=\"10\" to=\"20\" fill=\"freeze\" /><anim"
            + "ate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attribut"
            + "eName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"x"
            + "ml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"r"
            + "gb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\"xml\" "
            + "begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"x\" from=\"10\" "
            + "to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+1000"
            + "0.00ms\" dur=\"10000.00ms\" attributeName=\"y\" from=\"10\" to=\"30\" fill=\"free"
            + "ze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000."
            + "00ms\" attributeName=\"height\" from=\"20\" to=\"30\" fill=\"freeze\" /><animate at"
            + "tributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeNa"
            + "me=\"width\" from=\"20\" to=\"40\" fill=\"freeze\" /><animate attributeType=\"xml\""
            + " begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\""
            + "rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attributeType=\""
            + "xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fil"
            + "l=\"freeze\" /></rect><ellipse id=\"jeff\" cx=\"0\" cy=\"0\" rx=\"10\" ry=\"10\" "
            + "fill=\"transparent\" visibility=\"visible\" ><animate attributeType=\"xml\" begi"
            + "n=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"0\" to=\"1"
            + "0\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\""
            + " dur=\"10000.00ms\" attributeName=\"cy\" from=\"0\" to=\"10\" fill=\"freeze\" />"
            + "<animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attr"
            + "ibuteName=\"ry\" from=\"5\" to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml"
            + "\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"rx\" from=\"5\" "
            + "to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.0"
            + "0ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20"
            + ",20)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000"
            + ".00ms\" dur=\"10000.00ms\" attributeName=\"cx\" from=\"10\" to=\"20\" fill=\"fr"
            + "eeze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"100"
            + "00.00ms\" attributeName=\"cy\" from=\"10\" to=\"30\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attribute"
            + "Name=\"ry\" from=\"10\" to=\"15\" fill=\"freeze\" /><animate attributeType=\"xml\""
            + " begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"rx\" from=\"1"
            + "0\" to=\"20\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begi"
            + "n+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(20,20,20)\" "
            + "to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\""
            + "base.end\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fill=\"freeze\""
            + " /></ellipse></svg>");
  }

  /**
   * two shapes in the view one rectangle and one ellipse both are increasing over everything more.
   * than one command textual
   */
  @Test
  public void twoShapeIncMultComText() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));

    assertEquals(makeControllerOut(builder, 1, 1),
        "canvas 0 0 500 300\n"
            + "shape bob rectangle\n"
            + "motion bob 0.00 0 0 10 10 0 0 0     10.00 10 10 20 20 20 20 20\n"
            + "motion bob 10.00 10 10 20 20 20 20 20     20.00 20 30 40 30 120 100 80\n"
            + "\n"
            + "shape jeff ellipse\n"
            + "motion jeff 0.00 0 0 10 10 0 0 0     10.00 10 10 20 20 20 20 20\n"
            + "motion jeff 10.00 10 10 20 20 20 20 20     20.00 20 30 40 30 120 100 80\n");
  }

  //tests that the controller is unable to transmit output
  @Test(expected = IllegalArgumentException.class)
  public void testNoOutput() {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder = jeff1020Inc.apply(jeff010Inc
        .apply(makeJeff.apply(bob1020Inc.apply(bob010Inc.apply(makeBob.apply(emptyBuilder))))));
    IAnimator<Shape> model = builder.build();
    IAnimatorView view = new TextualAnimatorView(new ShapeViewModel(model), 1);
    model.startAnimator();
    IController cont = new Controller(model, view);
    Appendable app = new MockAppendable();
    cont.startAnimate(app);
  }

}
