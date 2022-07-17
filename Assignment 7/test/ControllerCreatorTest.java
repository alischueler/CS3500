
import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.ControllerCreator;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.ShapeViewModel;
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
}