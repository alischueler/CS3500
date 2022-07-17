package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.ISimpleCommands;
import cs3500.animator.model.Shape;
import cs3500.animator.tweener.Tweener;
import cs3500.animator.view.DiscreteView;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.ShapeViewModel;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;

/**
 * an implementation of IController that allows the user to communicate with the view and switch
 * between normal and discrete playing.
 */
public class DiscreteController extends InteractiveController {

  private boolean des;
  protected final DiscreteView view;

  /**
   * constructs an interactive controller.
   *
   * @param model the model that checks if the inputs follow the guidelines.
   * @param view  the view that will display the inputs in the form of an animation.
   */
  public DiscreteController(IAnimator<Shape> model,
      IAnimatorView view) {
    super(model, view);
    this.view = (DiscreteView) view;
    this.des = false;
  }

  /**
   * checks if the view if the correct instance of IAnimatorView for this controller.
   *
   * @param view the view to be examined
   */
  protected void checkInstance(IAnimatorView view) {
    if (!(view instanceof DiscreteView)) {
      throw new IllegalArgumentException("this controller supports discrete view");
    }
  }

  /**
   * initializes the timer to run the visual and interactive views.
   */
  protected void initializeTimer() {
    this.timer = new Timer((int) (1.0 / view.getTempo() * 1000),
        (e) -> {
          Tweener tweener = new Tweener(new ShapeViewModel(animator));
          currTick++;
          // see if there is a start or end this tick
          if (des & show()) {
            List<Shape> shapes = tweener.getStateAt(currTick);
            this.view.renderAnimate(shapes);
          }
          if (!des) {
            List<Shape> shapes = tweener.getStateAt(currTick);
            this.view.renderAnimate(shapes);
          }
          if (view.getLoop() && currTick > animator.getLastTick()) {
            currTick = 1;
          } else if (currTick > animator.getLastTick()) {
            this.view.renderAnimate(tweener.getStateAt(1));
            timer.stop();
          }
        });
  }

  /**
   * @return
   */
  private boolean show() {
    for (ISimpleCommands c : super.animator.getCommands()) {
      if (this.currTick == c.getTicks().getEnd() || this.currTick == c.getTicks().getEnd()) {
        return true;
      }
    }
    return false;
  }

  /**
   * makes the map of buttons and their actions.
   *
   * @return map of button names and their actions
   */
  protected Map<String, Runnable> makeButtonMap() {
    Map<String, Runnable> map = super.makeButtonMap();
    map.put("discrete", new ToggleDiscrete());
    map.put("discrete info", new DiscreteInfo());
    return map;
  }

  /**
   * switches the view between normal viewing and discrete viewing.
   */
  protected class ToggleDiscrete implements Runnable {

    @Override
    public void run() {
      des = !des;
    }
  }

  /**
   * provides information to the user about discrete viewing.
   */
  protected class DiscreteInfo implements Runnable {

    @Override
    public void run() {
      makePopUp("When selected, the animation will play only frames that are a start\n"
          + "or end tick of a shapes motions.\nOtherwise plays the animation normally.");
    }
  }
}
