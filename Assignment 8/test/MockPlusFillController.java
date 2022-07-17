import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.PlusFillInteractiveController;
import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A Mock MockPlusFillController to be able to test it and the listener methods within it.
 */
public class MockPlusFillController extends PlusFillInteractiveController {

  private Appendable log;

  /**
   * Constructs a MockPlusFillController
   *
   * @param model      the model the controller will reference.
   * @param view       the view the controller will reference.
   * @param buttonList the buttonListener that has buttonmap to add runnables to.
   * @param ap         the appendable that each runnable method will add to.
   */
  public MockPlusFillController(IAnimator<Shape> model, IAnimatorView view,
      ButtonListener buttonList, Appendable ap) {
    super(model, view);
    this.log = ap;
    Map<String, Runnable> buttonMap = new HashMap<>();
    buttonMap.put("START", new MockStartAnimation());
    buttonMap.put("PAUSE", new MockPauseAnimation());
    buttonMap.put("RESUME", new MockResumeAnimation());
    buttonMap.put("RESTART", new MockRestartAnimation());
    buttonMap.put("LOOP", new MockLoopAnimation());
    buttonMap.put("change", new MockChangeTempoAnimation());
    buttonMap.put("fill", new MockFillAnimation());

    // menu buttons
    buttonMap.put("start info", new MockStartInfo());
    buttonMap.put("pause info", new MockPauseInfo());
    buttonMap.put("resume info", new MockResumeInfo());
    buttonMap.put("restart info", new MockRestartInfo());
    buttonMap.put("loop info", new MockLoopInfo());
    buttonMap.put("tempo info", new MockTempoInfo());
    buttonMap.put("fill info", new MockFillInfo());
    buttonList.setButtonClickedActionMap(buttonMap);
  }

  /**
   * fills or unfills the animation.
   */
  public class MockFillAnimation extends FillAnimation {

    @Override
    public void run() {
      super.run();
      appendString("animation filled at tick " + currTick);
    }
  }

  /**
   * provides information about filling or unfilling an animation.
   */
  public class MockFillInfo extends FillInfo {

    @Override
    public void run() {
      super.run();
      appendString("fill info button pressed");
    }
  }

  /**
   * appends the given string to the appendable.
   *
   * @param msg the string to be appended
   */
  private void appendString(String msg) {
    try {
      log.append(msg);
    } catch (IOException e) {
      throw new IllegalArgumentException("cannot append output");
    }
  }


  /**
   * starts the un-started or ended animation from the first tick.
   */
  public class MockStartAnimation extends StartAnimation {

    @Override
    public void run() {
      super.run();
      appendString("animation started at tick " + currTick);
    }
  }

  /**
   * pauses the started animation at the current tick.
   */
  protected class MockPauseAnimation extends PauseAnimation {

    @Override
    public void run() {
      super.run();
      appendString("animation paused at tick " + currTick);
    }
  }

  /**
   * resumes the paused animation from the current tick.
   */
  protected class MockResumeAnimation extends ResumeAnimation {

    @Override
    public void run() {
      //super.run();
      appendString("animation resumed at tick " + currTick);
    }
  }

  /**
   * restarts the animation from the first tick.
   */
  protected class MockRestartAnimation extends RestartAnimation {

    @Override
    public void run() {
      super.run();
      appendString("animation restarted at tick " + currTick);
    }
  }

  /**
   * loops the animation if not looping, and does not loop the animation if looping.
   */
  protected class MockLoopAnimation extends LoopAnimation {

    @Override
    public void run() {
      super.run();
      appendString("animation changed loop to " + view.getLoop());
    }
  }

  /**
   * changes the tempo of the animation.
   */
  protected class MockChangeTempoAnimation extends ChangeTempoAnimation {

    @Override
    public void run() {
      super.run();
      appendString("change tempo button was pressed");
    }
  }

  /**
   * provides information about the start button.
   */
  protected class MockStartInfo extends StartInfo {

    @Override
    public void run() {
      super.run();
      appendString("start info button pressed");
    }
  }

  /**
   * provides information about the pause button.
   */
  protected class MockPauseInfo extends PauseInfo {

    @Override
    public void run() {
      super.run();
      appendString("pause info button pressed");
    }
  }

  /**
   * provides information about the restart button.
   */
  protected class MockRestartInfo extends RestartInfo {

    @Override
    public void run() {
      super.run();
      appendString("restart info button pressed");
    }
  }

  /**
   * provides information about the resume button.
   */
  protected class MockResumeInfo extends ResumeInfo {

    @Override
    public void run() {
      super.run();
      appendString("resume info button pressed");
    }
  }

  /**
   * provides information about the loop button.
   */
  protected class MockLoopInfo extends LoopInfo {

    @Override
    public void run() {
      super.run();
      appendString("loop info button pressed");
    }
  }

  /**
   * provides information about the tempo change field.
   */
  protected class MockTempoInfo extends TempoInfo {

    @Override
    public void run() {
      super.run();
      appendString("tempo change info button pressed");
    }
  }
}

