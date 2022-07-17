import cs3500.animator.controller.VisualController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.TextualAnimatorView;
import cs3500.animator.view.VisualJPanel;
import org.junit.Test;

/**
 * holds tests for the visual controller.
 */
public class VisualControllerTest {

  //tests that an interactive view will not work
  @Test(expected = IllegalArgumentException.class)
  public void testNoInteractive() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new InteractiveAnimationView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 4);
    new VisualController(model, view);
  }

  //tests that an svg biew will not work
  @Test(expected = IllegalArgumentException.class)
  public void testNoSVG() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new SVGAnimatorView(new ShapeViewModel(model), 4);
    new VisualController(model, view);
  }

  //tests that a text view will not work
  @Test(expected = IllegalArgumentException.class)
  public void testNoTextual() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new TextualAnimatorView(new ShapeViewModel(model), 4);
    new VisualController(model, view);
  }

  //tests that a null model will not work
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new TextualAnimatorView(new ShapeViewModel(model), 4);
    new VisualController(null, view);
  }

  //tests that a null view will not work
  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    new VisualController(model, null);
  }

}