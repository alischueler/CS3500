import static org.junit.Assert.assertEquals;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGWithPlusView;
import cs3500.animator.view.ShapeViewModel;
import java.io.IOException;
import org.junit.Test;

/**
 * class to test making the svg with a plus.
 */
public class TestPlusSvg extends SVGAnimatorViewTest {

  TestLambda makePaul;
  TestLambda paul010Inc;
  TestLambda paul1020Inc;

  /**
   * holds examples for testing.
   */
  protected void examples() {
    super.examples();
    makePaul = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.declareShape("paul", "plus");
      return builder;
    };
    paul010Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("paul", 0, 0, 0, 10, 10, 0, 0, 0,
          10, 10, 10, 20, 20, 20, 20, 20);
      return builder;
    };
    paul1020Inc = (AnimationBuilder<IAnimator<Shape>> builder) -> {
      builder.addMotion("paul", 10, 10, 10, 20, 20, 20, 20,
          20, 20, 20, 30, 40, 30, 120, 100, 80);
      return builder;
    };
  }

  /**
   * only plus is included with two commands.
   */
  @Test
  public void twoPlus() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        paul1020Inc.apply(paul010Inc.apply(makePaul.apply(emptyBuilder)));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.o"
            + "rg/2000/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"20000"
            + ".0ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><polygon "
            + "id=\"paul\" points=\"2.5,0.0 7.5,0.0 7.5,2.5 10.0,2.5 10.0,7.5 7.5,7.5 7.5,10.0 2"
            + ".5,10.0 2.5,7.5 0.0,7.5 0.0,2.5 2.5,2.5 \" fill=\"transparent\" visibility=\"visi"
            + "ble\" ><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00m"
            + "s\" attributeName=\"points\" from=\"2.5,0.0 7.5,0.0 7.5,2.5 10.0,2.5 10.0,7.5 7.5,"
            + "7.5 7.5,10.0 2.5,10.0 2.5,7.5 0.0,7.5 0.0,2.5 2.5,2.5 \" to=\"15.0,10.0 25.0,10.0 "
            + "25.0,15.0 30.0,15.0 30.0,25.0 25.0,25.0 25.0,30.0 15.0,30.0 15.0,25.0 10.0,25.0 10"
            + ".0,15.0 15.0,15.0 \" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base"
            + ".begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\""
            + "rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begi"
            + "n+10000.00ms\" dur=\"10000.00ms\" attributeName=\"points\" from=\"15.0,10.0 25.0,1"
            + "0.0 25.0,15.0 30.0,15.0 30.0,25.0 25.0,25.0 25.0,30.0 15.0,30.0 15.0,25.0 10.0,25."
            + "0 10.0,15.0 15.0,15.0 \" to=\"30.0,30.0 50.0,30.0 50.0,37.5 60.0,37.5 60.0,52.5 50"
            + ".0,52.5 50.0,60.0 30.0,60.0 30.0,52.5 20.0,52.5 20.0,37.5 30.0,37.5 \" fill=\"free"
            + "ze\" /><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000."
            + "00ms\" attributeName=\"fill\" from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\""
            + "freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribute"
            + "Name=\"fill\" to=\"transparent\" fill=\"freeze\" /></polygon></svg>");
  }

  /**
   * only plus is included with onw commands.
   */
  @Test
  public void onePlus() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        paul010Inc.apply(makePaul.apply(emptyBuilder));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/200"
            + "0/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"10000.0ms\" attr"
            + "ibuteName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><polygon id=\"paul\" "
            + "points=\"2.5,0.0 7.5,0.0 7.5,2.5 10.0,2.5 10.0,7.5 7.5,7.5 7.5,10.0 2.5,10.0 2.5"
            + ",7.5 0.0,7.5 0.0,2.5 2.5,2.5 \" fill=\"transparent\" visibility=\"visible\" ><ani"
            + "mate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attribu"
            + "teName=\"points\" from=\"2.5,0.0 7.5,0.0 7.5,2.5 10.0,2.5 10.0,7.5 7.5,7.5 7.5,10."
            + "0 2.5,10.0 2.5,7.5 0.0,7.5 0.0,2.5 2.5,2.5 \" to=\"15.0,10.0 25.0,10.0 25.0,15.0 3"
            + "0.0,15.0 30.0,25.0 25.0,25.0 25.0,30.0 15.0,30.0 15.0,25.0 10.0,25.0 10.0,15.0 15.0"
            + ",15.0 \" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00m"
            + "s\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,2"
            + "0)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms"
            + "\" attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></polygon></svg>");
  }

  /**
   * plus and rectangle and ellipse is included.
   */
  @Test
  public void everything() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        jeff010Inc.apply(bob010Inc
            .apply(makeJeff.apply(makeBob.apply(paul010Inc.apply(makePaul.apply(emptyBuilder))))));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/"
            + "svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"10000.0ms\" attribute"
            + "Name=\"visibility\" from=\"hide\" to=\"hide\"/></rect><polygon id=\"paul\" points=\""
            + "2.5,0.0 7.5,0.0 7.5,2.5 10.0,2.5 10.0,7.5 7.5,7.5 7.5,10.0 2.5,10.0 2.5,7.5 0.0,7."
            + "5 0.0,2.5 2.5,2.5 \" fill=\"transparent\" visibility=\"visible\" ><animate attribu"
            + "teType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"poin"
            + "ts\" from=\"2.5,0.0 7.5,0.0 7.5,2.5 10.0,2.5 10.0,7.5 7.5,7.5 7.5,10.0 2.5,10.0 2."
            + "5,7.5 0.0,7.5 0.0,2.5 2.5,2.5 \" to=\"15.0,10.0 25.0,10.0 25.0,15.0 30.0,15.0 30.0"
            + ",25.0 25.0,25.0 25.0,30.0 15.0,30.0 15.0,25.0 10.0,25.0 10.0,15.0 15.0,15.0 \" fil"
            + "l=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"100"
            + "00.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"fr"
            + "eeze\" /><animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeNa"
            + "me=\"fill\" to=\"transparent\" fill=\"freeze\" /></polygon><rect id=\"bob\" x=\"0\""
            + " y=\"0\" width=\"10\" height=\"10\" fill=\"transparent\" visibility=\"visible\" ><"
            + "animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attr"
            + "ibuteName=\"x\" from=\"0\" to=\"10\" fill=\"freeze\" /><animate attributeType=\"x"
            + "ml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"y\" from=\"0\""
            + " to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0."
            + "00ms\" dur=\"10000.00ms\" attributeName=\"height\" from=\"10\" to=\"20\" fill=\"f"
            + "reeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000."
            + "00ms\" attributeName=\"width\" from=\"10\" to=\"20\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeNa"
            + "me=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(20,20,20)\" fill=\"freeze\" /><animate "
            + "attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" to=\""
            + "transparent\" fill=\"freeze\" /></rect><ellipse id=\"jeff\" cx=\"0\" cy=\"0\" rx="
            + "\"10\" ry=\"10\" fill=\"transparent\" visibility=\"visible\" ><animate attributeT"
            + "ype=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"cx\" fr"
            + "om=\"0\" to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.b"
            + ""
            + "egin+0.00ms\" dur=\"10000.00ms\" attributeName=\"cy\" from=\"0\" to=\"10\" fill=\""
            + "freeze\" /><animate attributeType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000."
            + "00ms\" attributeName=\"ry\" from=\"5\" to=\"10\" fill=\"freeze\" /><animate attrib"
            + "uteType=\"xml\" begin=\"base.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"rx\""
            + " from=\"5\" to=\"10\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"bas"
            + "e.begin+0.00ms\" dur=\"10000.00ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to="
            + "\"rgb(20,20,20)\" fill=\"freeze\" /><animate attributeType=\"xml\" begin=\"base.en"
            + "d\" dur=\"1ms\" attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" /></elli"
            + "pse></svg>");
  }

  /**
   * plus is offset from zero.
   */
  @Test
  public void offsetPlus() throws IOException {
    examples();
    AnimationBuilder<IAnimator<Shape>> builder =
        paul1020Inc.apply(makePaul.apply(emptyBuilder));
    assertEquals(makeSVGCode(builder, 1, true),
        "<svg width=\"500\" height=\"300\" version=\"1.1\" xmlns=\"http://www.w3.org/2"
            + "000/svg\"><rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"20000.0ms\" attr"
            + "ibuteName=\"visibility\" from=\"hide\" to=\"hide\"/></rect><polygon id=\"paul\" po"
            + "ints=\"15.0,10.0 25.0,10.0 25.0,15.0 30.0,15.0 30.0,25.0 25.0,25.0 25.0,30.0 15.0,3"
            + "0.0 15.0,25.0 10.0,25.0 10.0,15.0 15.0,15.0 \" fill=\"transparent\" visibility=\"vis"
            + "ible\" ><animate attributeType=\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000"
            + ".00ms\" attributeName=\"points\" from=\"15.0,10.0 25.0,10.0 25.0,15.0 30.0,15.0 30."
            + "0,25.0 25.0,25.0 25.0,30.0 15.0,30.0 15.0,25.0 10.0,25.0 10.0,15.0 15.0,15.0 \" to="
            + "\""
            + "30.0,30.0 50.0,30.0 50.0,37.5 60.0,37.5 60.0,52.5 50.0,52.5 50.0,60.0 30.0,60.0 30."
            + "0,52.5 20.0,52.5 20.0,37.5 30.0,37.5 \" fill=\"freeze\" /><animate attributeType="
            + "\"xml\" begin=\"base.begin+10000.00ms\" dur=\"10000.00ms\" attributeName=\"fill\""
            + " from=\"rgb(20,20,20)\" to=\"rgb(120,100,80)\" fill=\"freeze\" /><animate attrib"
            + "uteType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"fill\" to=\"trans"
            + "parent\" fill=\"freeze\" /></polygon></svg>");
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
  protected String makeSVGCode(AnimationBuilder<IAnimator<Shape>> builder, int tempo, boolean loop)
      throws IOException {
    IAnimator<Shape> model = builder.build();
    model.startAnimator();
    IAnimatorView view = new SVGWithPlusView(new ShapeViewModel(model), tempo);
    view.setLoop(loop);
    StringBuilder sb = new StringBuilder();
    view.render(sb);
    return sb.toString();
  }


}
