
import cs3500.animator.controller.SlowMoController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.DiscreteView;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.PlusFillInteractiveView;
import cs3500.animator.view.PlusFillVisualJPanel;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.VisualJPanel;
import org.junit.Test;

/**
 * a class to hold tests for the slowmo controller.
 */
public class SlowMoControllerTest extends InteractiveControllerTest {

  //tests the controller throws an error for regular interactive view
  @Test(expected = IllegalArgumentException.class)
  public void testNoInteractive() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new InteractiveAnimationView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 4);
    new SlowMoController(model, view);
  }

  //tests the controller throws an error for plusfill view
  @Test(expected = IllegalArgumentException.class)
  public void testNoPlusFill() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new PlusFillInteractiveView(new PlusFillVisualJPanel(
        new ShapeViewModel(model)),
        new ShapeViewModel(model), 4);
    new SlowMoController(model, view);
  }


  //tests the controller throws an error for discrete view
  @Test(expected = IllegalArgumentException.class)
  public void testNoDiscrete() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new DiscreteView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 4);
    new SlowMoController(model, view);
  }
}
