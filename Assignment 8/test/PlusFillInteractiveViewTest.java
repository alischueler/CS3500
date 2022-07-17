import static org.junit.Assert.*;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IPlusFillInteractiveView;
import cs3500.animator.view.PlusFillInteractiveView;
import cs3500.animator.view.PlusFillVisualJPanel;
import cs3500.animator.view.ShapeViewModel;
import org.junit.Test;

/**
 * a class to hold tests for the plus fill interactive view
 */
public class PlusFillInteractiveViewTest extends InteractiveAnimationViewTest {

  //tests getting fill
  @Test
  public void testGetFill() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "plus")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IPlusFillInteractiveView view = new PlusFillInteractiveView(
        new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    assertTrue(view.getFill());
  }

  //tests setting fill
  @Test
  public void testSetFill() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "plus")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IPlusFillInteractiveView view = new PlusFillInteractiveView(
        new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    view.setFill(false);
    assertFalse(view.getFill());
  }

}