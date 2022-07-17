package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.tweener.Tweener;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.ShapeViewModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * An instance of IController which controls the interactive animation through input from the
 * client.
 */
public class InteractiveController extends VisualController {
  protected IInteractiveView view;

  /**
   * constructs an interactive controller.
   *
   * @param model the model that checks if the inputs follow the guidelines.
   * @param view  the view that will display the inputs in the form of an animation.
   */
  public InteractiveController(IAnimator<Shape> model, IAnimatorView view) {
    super(model, view);
    this.view = (IInteractiveView) view;
    makeButtonListeners();
  }

  /**
   * adds the button listeners to a ButtonListener object. Then adds Listener addActionListener to
   * the view
   */
  private void makeButtonListeners() {
    Map<String, Runnable> buttonClickedMap = makeButtonMap();
    ButtonListener buttonListener = new ButtonListener();
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  /**
   * makes the map of buttons and their actions.
   * @return map of button names and their actions
   */
  private Map<String, Runnable> makeButtonMap() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    // buttons to control animation
    buttonClickedMap.put("START", new StartAnimation());
    buttonClickedMap.put("PAUSE", new PauseAnimation());
    buttonClickedMap.put("RESUME", new ResumeAnimation());
    buttonClickedMap.put("RESTART", new RestartAnimation());
    buttonClickedMap.put("LOOP", new LoopAnimation());
    buttonClickedMap.put("change", new ChangeTempoAnimation());

    // menu buttons
    buttonClickedMap.put("start info", new StartInfo());
    buttonClickedMap.put("pause info", new PauseInfo());
    buttonClickedMap.put("resume info", new RestartInfo());
    buttonClickedMap.put("restart info", new ResumeInfo());
    buttonClickedMap.put("loop info", new LoopInfo());
    buttonClickedMap.put("tempo info", new TempoInfo());
    return buttonClickedMap;
  }

  /**
   * checks if the view if the correct instance of IAnimatorView for this controller.
   * @param view the view to be examined.
   */
  protected void checkInstance(IAnimatorView view) {
    if (!(view instanceof IInteractiveView)) {
      throw new IllegalArgumentException("this controller supports interactive view");
    }
  }

  /**
   * initializes the timer to run the visual and interactive views.
   */
  protected void initializeTimer() {
    this.timer = new Timer((int) (1.0 / view.getTempo() * 1000),
        (e) -> {
          Tweener tweener = new Tweener(new ShapeViewModel(animator));
          List<Shape> shapes = tweener.getStateAt(currTick++);
          this.view.renderAnimate(shapes);
          if (view.getLoop() && currTick > animator.getLastTick()) {
            currTick = 1;
          }
          else if (currTick > animator.getLastTick()) {
            this.view.renderAnimate(tweener.getStateAt(1));
            timer.stop();
          }
        });
  }

  /**
   * starts the un-started or ended animation from the first tick.
   */
  protected class StartAnimation implements Runnable {

    @Override
    public void run() {
      if (currTick == 1 || currTick > animator.getLastTick()) {
        timer.start();
      }
    }
  }

  /**
   * pauses the started animation at the current tick.
   */
  protected class PauseAnimation implements Runnable {

    @Override
    public void run() {
      timer.stop();
    }
  }

  /**
   * resumes the paused animation from the current tick.
   */
  protected class ResumeAnimation implements Runnable {

    @Override
    public void run() {
      if (currTick >= 1) {
        timer.start();
      }
    }
  }

  /**
   * restarts the animation from the first tick.
   */
  protected class RestartAnimation implements Runnable {

    @Override
    public void run() {
      currTick = 1;
      timer.start();
    }
  }

  /**
   * loops the animation if not looping, and does not loop the animation if looping.
   */
  protected class LoopAnimation implements Runnable {
    @Override
    public void run() {
      if (view.getLoop()) {
        view.setLoop(false);
      } else {
        view.setLoop(true);
      }
    }
  }

  /**
   * changes the tempo of the animation.
   */
  protected class ChangeTempoAnimation implements Runnable {
    @Override
    public void run() {
      String tempo = view.getNewTempo();
      try {
        Integer.parseInt(tempo);
      } catch (NumberFormatException n) {
        //to show errors
        makePopUp("input is not an integer");
        return;
      }
      try {
        view.setTempo(Integer.parseInt(tempo));
      } catch (IllegalArgumentException e) {
        //to show errors
        view.clearText();
        makePopUp("input " + Integer.parseInt(tempo) + " is less than 1");
        return;
      }
      view.clearText();
      view.updateTempo();
      timer.setDelay((int) (1.0 / view.getTempo() * 1000));
    }
  }

  /**
   * provides information about the start button.
   */
  protected class StartInfo implements Runnable {

    @Override
    public void run() {
      makePopUp("The start button will start an animation that has not yet begun, or an "
          + "animation that has ended.");
    }
  }

  /**
   * provides information about the pause button.
   */
  protected class PauseInfo implements Runnable {
    @Override
    public void run() {
      makePopUp("The pause button will stop an animation at the frame it is currently on "
          + "indefinitely.");
    }
  }

  /**
   * provides information about the restart button.
   */
  protected class RestartInfo implements Runnable {
    @Override
    public void run() {
      makePopUp("The restart button will restart an animation from the first frame.");
    }
  }

  /**
   * provides information about the resume button.
   */
  protected class ResumeInfo implements Runnable {
    @Override
    public void run() {
      makePopUp("The resume button will play a paused animation starting with the frame it is "
          + "currently on.");
    }
  }

  /**
   * provides information about the loop button.
   */
  protected class LoopInfo implements Runnable {
    @Override
    public void run() {
      makePopUp("The loop radio button will cause an animation to loop indefinitely when"
          + " selected, "
          + "or end after the current loop when deselected.");
    }
  }

  /**
   * provides information about the tempo change field.
   */
  protected class TempoInfo implements Runnable {
    @Override
    public void run() {
      makePopUp("The tempo can be changed by inputting a numerical integer greater than or "
          + "equal to 1"
          + "into the text field and selecting the change it button below.\nHigher numbers will "
          + "cause"
          + " the animation to run faster and lower numbers will cause the animation to run slower."
          + "\nThe current tempo of the animation is always displayed above.");
    }
  }

  /**
   * displays the given string in a pop up on the users screen.
   *
   * @param msg the message to be displayed.
   */
  private void makePopUp(String msg) {
    JFrame frame = new JFrame();
    frame.setSize(300, 300);
    JOptionPane.showMessageDialog(frame, msg);
  }

}