
import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.ControllerCreator;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.DiscreteView;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.PlusFillInteractiveView;
import cs3500.animator.view.PlusFillVisualJPanel;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.SlowMoView;
import cs3500.animator.view.TextualAnimatorView;
import cs3500.animator.view.ViewCreator.ViewType;
import cs3500.animator.view.VisualAnimationView;
import cs3500.animator.view.VisualJPanel;
import org.junit.Test;

/**
 * tests the ViewCreator.
 */
public class ControllerCreatorTest {

  IAnimator<Shape> model;
  ViewType text;
  IAnimatorView textualView;
  ViewType vis;
  IAnimatorView visualView;
  ViewType svg;
  IAnimatorView svgView;
  ViewType interactive;
  IAnimatorView interactiveView;
  ViewType level1;
  ViewType level2;
  ViewType level3;
  IAnimatorView plusfill;
  IAnimatorView discrete;
  IAnimatorView slowmo;
  ControllerCreator create;

  protected void examples() {
    model = new BasicAnimator.Builder().declareShape("s", "ellipse")
        .setBounds(5, 5, 5, 5).addMotion("s", 2, 4, 5, 3,
            2, 2, 3, 4, 5, 4, 5, 6, 7, 7, 6, 7)
        .build();
    text = ViewType.TEXT;
    textualView = new TextualAnimatorView(new ShapeViewModel(model), 3);
    vis = ViewType.VISUAL;
    visualView = new VisualAnimationView(new ShapeViewModel(model), 3);
    svg = ViewType.SVG;
    svgView = new SVGAnimatorView(new ShapeViewModel(model), 3);
    interactive = ViewType.INTERACTIVE;
    interactiveView = new InteractiveAnimationView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    level1 = ViewType.LEVEL1;
    plusfill = new PlusFillInteractiveView(new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    level2 = ViewType.LEVEL2;
    discrete = new DiscreteView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    level3 = ViewType.LEVEL3;
    slowmo = new SlowMoView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    create = new ControllerCreator();
  }

  //tests making a textual view
  @Test
  public void testTextual() {
    examples();
    assertEquals(cs3500.animator.controller.Controller.class,
        create.create(text, model, textualView).getClass());
  }

  //tests making a visual view
  @Test
  public void testVisual() {
    examples();
    assertEquals(cs3500.animator.controller.VisualController.class,
        create.create(vis, model, visualView).getClass());
  }

  //tests making a svg view
  @Test
  public void testSVG() {
    examples();
    assertEquals(cs3500.animator.controller.Controller.class,
        create.create(svg, model, svgView).getClass());
  }

  //tests making a interactive controller
  @Test
  public void testInteractive() {
    examples();
    assertEquals(cs3500.animator.controller.InteractiveController.class,
        create.create(interactive, model, interactiveView).getClass());
  }

  //tests making level 1
  @Test
  public void testLevel1() {
    examples();
    assertEquals(cs3500.animator.controller.PlusFillInteractiveController.class,
        create.create(level1, model, plusfill).getClass());
  }

  //tests making level 2
  @Test
  public void testLevel2() {
    examples();
    assertEquals(cs3500.animator.controller.DiscreteController.class,
        create.create(level2, model, discrete).getClass());
  }

  //tests making level 3
  @Test
  public void testLevel3() {
    examples();
    assertEquals(cs3500.animator.controller.SlowMoController.class,
        create.create(level3, model, slowmo).getClass());
  }
}