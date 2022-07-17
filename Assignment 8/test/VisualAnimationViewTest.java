import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IVisualView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.VisualAnimationView;
import java.io.IOException;
import org.junit.Test;

/**
 * a class to hold tests for the visual animation view.
 */
public class VisualAnimationViewTest {

  //tests getting tempo
  @Test
  public void testGetTempo() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IVisualView view = new VisualAnimationView(new ShapeViewModel(model), 3);
    assertEquals(3, view.getTempo());
  }

  //tests setting the loop will throw an error
  @Test(expected = UnsupportedOperationException.class)
  public void testSetLoop() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IVisualView view = new VisualAnimationView(new ShapeViewModel(model), 3);
    view.setLoop(true);
  }

  //tests rendering will throw an error
  @Test(expected = UnsupportedOperationException.class)
  public void testRender() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IVisualView view = new VisualAnimationView(new ShapeViewModel(model), 3);
    try {
      view.render(new StringBuilder());
    } catch (IOException e) {
      //do nothing
    }
  }
}