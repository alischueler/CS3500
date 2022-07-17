import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.IController;
import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.BasicAnimator;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.TextualAnimatorView;
import cs3500.animator.view.VisualAnimationView;
import org.junit.Test;

/**
 * a class to hold tests for the interactive controller and listeners within it.
 */
public class InteractiveControllerTest {


  //tests the controller throws an error for visualviews
  @Test(expected = IllegalArgumentException.class)
  public void testNoVisual() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new VisualAnimationView(new ShapeViewModel(model), 4);
    new InteractiveController(model, view);
  }

  //tests the controller throws an error for svg views
  @Test(expected = IllegalArgumentException.class)
  public void testNoSVG() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new SVGAnimatorView(new ShapeViewModel(model), 4);
    new InteractiveController(model, view);
  }

  //tests the controller starts the animation
  @Test(expected = IllegalArgumentException.class)
  public void testNoText() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new TextualAnimatorView(new ShapeViewModel(model), 4);
    new InteractiveController(model, view);
  }

  //tests the start animator works
  @Test
  public void testStart() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("START", buttonList, 20);
    IController controller = new MockInteractiveController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    view.renderAnimate(null);
    assertEquals("animation started at tick 1", ap.toString());
  }

  //tests the pause animator works
  @Test
  public void testPause() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("PAUSE", buttonList, 20);
    IController controller = new MockInteractiveController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    view.renderAnimate(null);
    assertEquals("animation paused at tick 1", ap.toString());
  }

  //tests the resume animator works
  @Test
  public void testResume() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("RESUME", buttonList, 20);
    IController controller = new MockInteractiveController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    view.renderAnimate(null);
    assertEquals("animation resumed at tick 1", ap.toString());
  }

  //tests the restart animator works
  @Test
  public void testRestart() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("RESTART", buttonList, 20);
    IController controller = new MockInteractiveController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    view.renderAnimate(null);
    assertEquals("animation restarted at tick 1", ap.toString());
  }

  //tests the loop animator works
  @Test
  public void testLoop() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("LOOP", buttonList, 20);
    IController controller = new MockInteractiveController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    assertFalse(view.getLoop());
    view.renderAnimate(null);
    assertEquals("animation changed loop to true", ap.toString());
    assertTrue(view.getLoop());
  }

  //tests the change tempo works
  @Test
  public void testChangeTempo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("change", buttonList, 20);
    IController controller = new MockInteractiveController(model, view, buttonList, ap);
    controller.startAnimate(new StringBuilder());
    assertEquals(20, view.getTempo());
    view.renderAnimate(null);
    assertEquals("change tempo button was pressed", ap.toString());
    assertEquals(500, view.getTempo());
  }

  //tests the startinfo works
  @Test
  public void testStartInfo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("start info", buttonList, 20);
    new MockInteractiveController(model, view, buttonList, ap);
    view.renderAnimate(null);
    assertEquals("start info button pressed", ap.toString());
  }

  //tests the pauseinfo works
  @Test
  public void testPauseInfo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("pause info", buttonList, 20);
    new MockInteractiveController(model, view, buttonList, ap);
    view.renderAnimate(null);
    assertEquals("pause info button pressed", ap.toString());
  }

  //tests the resumeinfo works
  @Test
  public void testResumeInfo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("resume info", buttonList, 20);
    new MockInteractiveController(model, view, buttonList, ap);
    view.renderAnimate(null);
    assertEquals("resume info button pressed", ap.toString());
  }

  //tests the restartinfo works
  @Test
  public void testRestartInfo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("restart info", buttonList, 20);
    new MockInteractiveController(model, view, buttonList, ap);
    view.renderAnimate(null);
    assertEquals("restart info button pressed", ap.toString());
  }

  //tests the loop info works
  @Test
  public void testLoopInfo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("loop info", buttonList, 20);
    new MockInteractiveController(model, view, buttonList, ap);
    view.renderAnimate(null);
    assertEquals("loop info button pressed", ap.toString());
  }

  //tests the change info works
  @Test
  public void testChangeInfo() {
    StringBuilder ap = new StringBuilder();
    IAnimator<Shape> model = new MockModel(ap);
    model.startAnimator();
    ButtonListener buttonList = new ButtonListener();
    IInteractiveView view = new MockInteractiveView("tempo info", buttonList, 20);
    new MockInteractiveController(model, view, buttonList, ap);
    view.renderAnimate(null);
    assertEquals("tempo change info button pressed", ap.toString());
  }

  //tests that null button maps are not allowed
  @Test(expected = IllegalArgumentException.class)
  public void testNullButtonMap() {
    ButtonListener buttonList = new ButtonListener();
    buttonList.setButtonClickedActionMap(null);
  }

  //tests that null models are not allowed
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    IAnimatorView view = new VisualAnimationView(new ShapeViewModel(model), 4);
    new InteractiveController(null, view);
  }

  //tests that null views are not allowed
  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    IAnimator<Shape> model = new BasicAnimator.Builder().declareShape("bob",
        "rectangle")
        .addMotion("bob", 0, 0, 0, 10, 10, 0, 0, 0,
            10, 10, 10, 20, 20, 20, 20, 20).
            setBounds(50, 50, 300, 200).build();
    new InteractiveController(model, null);
  }
}