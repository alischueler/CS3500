import static org.junit.Assert.assertEquals;

import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.InteractiveAnimationView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.VisualJPanel;
import java.io.IOException;
import org.junit.Test;

/**
 * a class to hold tests for the interactive view.
 */
public class InteractiveAnimationViewTest {

  //tests getting loop
  @Test
  public void testGetLoop() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(
        new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    assertEquals(false, view.getLoop());
  }

  //tests setting loop
  @Test
  public void testSetLoop() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(
        new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    view.setLoop(true);
    assertEquals(true, view.getLoop());
  }

  //tests getting tempo
  @Test
  public void testGetTempo() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(
        new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    view.setLoop(true);
    assertEquals(3, view.getTempo());
  }

  //tests setting tempo
  @Test
  public void testSetTempo() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(
        new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    view.setTempo(5);
    assertEquals(5, view.getTempo());
  }

  //tests setting tempo to 0 or less
  @Test(expected = IllegalArgumentException.class)
  public void testSetTempoTooLow() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(
        new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    view.setTempo(0);
  }

  //tests setting tempo to 0 or less
  @Test(expected = IllegalArgumentException.class)
  public void testSetTempoNeg() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(
        new VisualJPanel(new ShapeViewModel(model)),
        new ShapeViewModel(model), 3);
    view.setTempo(-12);
  }

  //tests rendering will throw an error
  @Test(expected = UnsupportedOperationException.class)
  public void testRender() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(new VisualJPanel(
        new ShapeViewModel(model)), new ShapeViewModel(model), 3);
    try {
      view.render(new StringBuilder());
    } catch (IOException e) {
      //do nothing
    }
  }

  //tests getting the text of the changed tempo
  @Test
  public void testGetText() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(new VisualJPanel(
        new ShapeViewModel(model)), new ShapeViewModel(model), 3);
    assertEquals("", view.getNewTempo());
  }

  //tests making with null model
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    new InteractiveAnimationView(new VisualJPanel(
        new ShapeViewModel(model)), null, 3);
  }

  //tests making with null visualjpanel
  @Test(expected = IllegalArgumentException.class)
  public void testNullJPanel() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    new InteractiveAnimationView(null, new ShapeViewModel(model), 3);
  }

  //tests making with neg tempo
  @Test(expected = IllegalArgumentException.class)
  public void testNegTempo() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob", "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IInteractiveView view = new InteractiveAnimationView(new VisualJPanel(
        new ShapeViewModel(model)), new ShapeViewModel(model), -3);
  }

}