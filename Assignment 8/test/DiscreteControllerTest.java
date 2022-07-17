import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.DiscreteController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.PlusFillInteractiveView;
import cs3500.animator.view.PlusFillVisualJPanel;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.SlowMoView;
import cs3500.animator.view.VisualJPanel;
import org.junit.Test;

/**
 * a test class for discrete controllers
 */
public class DiscreteControllerTest extends InteractiveControllerTest {

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
    new DiscreteController(model, view);
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
    new DiscreteController(model, view);
  }


  //tests the controller throws an error for slowmo view
  @Test(expected = IllegalArgumentException.class)
  public void testNoSlowMo() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new SlowMoView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 4);
    new DiscreteController(model, view);
  }

  //tests that discrete animation works
  @Test
  public void testDiscrete() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockDiscreteView("discrete", buttonList, 20, model);
    IController controller = new MockDiscreteController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    view.renderAnimate(null);
    assertEquals("animation changed to discrete at tick 1", ap.toString());
  }
}
