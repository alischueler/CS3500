import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.PlusFillInteractiveController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.DiscreteView;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IPlusFillInteractiveView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.SlowMoView;
import cs3500.animator.view.VisualJPanel;
import org.junit.Test;

/**
 * a class to hold tests for the plus fill controller
 */
public class PlusFillControllerTest extends InteractiveControllerTest {

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
    new PlusFillInteractiveController(model, view);
  }

  //tests the controller throws an error for discreteview
  @Test(expected = IllegalArgumentException.class)
  public void testNoDiscrete() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new DiscreteView(new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 4);
    new PlusFillInteractiveController(model, view);
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
    new PlusFillInteractiveController(model, view);
  }

  //tests that fill animation works
  @Test
  public void testFill() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IPlusFillInteractiveView view = new MockPlusFillView("fill", buttonList, 20);
    assertTrue(view.getFill());
    IController controller = new MockPlusFillController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    view.renderAnimate(null);
    assertEquals("animation filled at tick 1", ap.toString());
    assertFalse(view.getFill());
  }
}
