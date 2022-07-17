import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.ISlowMoView;
import cs3500.animator.view.PlusFillVisualJPanel;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.SlowMoView;
import org.junit.Test;

/**
 * a class to hold tests for slow mo view.
 */
public class SlowMoViewTest extends InteractiveAnimationViewTest {

  //tests getting the correct tempo with no slow mo
  @Test
  public void testGettingCorrectTempo() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "plus")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    ISlowMoView view = new SlowMoView(
        new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    assertEquals(3, view.getCorrectTempo(3));
  }

  //tests getting the correct tempo with slow mo
  @Test
  public void testGettingCorrectTempoSlow() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "plus")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).addTempo(12, 15, 30).
            build();
    ISlowMoView view = new SlowMoView(
        new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    assertEquals(30, view.getCorrectTempo(13));
  }

  //tests getting the correct tempo with slow mo
  @Test
  public void testGettingCorrectTempoBoundLower() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "plus")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).addTempo(12, 15, 30).
            build();
    ISlowMoView view = new SlowMoView(
        new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    assertEquals(30, view.getCorrectTempo(12));
  }

  //tests getting the correct tempo with slow mo
  @Test
  public void testGettingCorrectTempoBoundUpper() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "plus")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).addTempo(12, 15, 30).
            build();
    ISlowMoView view = new SlowMoView(
        new PlusFillVisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    assertEquals(30, view.getCorrectTempo(15));
  }
}
