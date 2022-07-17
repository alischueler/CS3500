package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.tweener.Tweener;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.ISlowMoView;
import cs3500.animator.view.ShapeViewModel;
import java.util.List;
import javax.swing.Timer;

/**
 * an implementation of IController that allows for an animation to have slow mo periods. Shares
 * common methods with InteractiveController.
 */
public class SlowMoController extends InteractiveController {

  protected ISlowMoView view;

  /**
   * constructs an interactive controller.
   *
   * @param model the model that checks if the inputs follow the guidelines.
   * @param view  the view that will display the inputs in the form of an animation.
   */
  public SlowMoController(IAnimator<Shape> model, IAnimatorView view) {
    super(model, view);
    this.view = (ISlowMoView) view;
  }

  /**
   * checks if the view if the correct instance of SlowMoView for this controller.
   *
   * @param view the view to be examined
   */
  protected void checkInstance(IAnimatorView view) {
    if (!(view instanceof ISlowMoView)) {
      throw new IllegalArgumentException("this controller supports slomo view");
    }
  }

  /**
   * initializes the controller so the animation can run at the correct tempo.
   */
  protected void initializeTimer() {
    this.timer = new Timer((int) (1.0 / view.getTempo() * 1000),
        (e) -> {
          Tweener tweener = new Tweener(new ShapeViewModel(animator));
          List<Shape> shapes = tweener.getStateAt(currTick++);
          this.view.renderAnimate(shapes);
          if (view.getLoop() && currTick > animator.getLastTick()) {
            currTick = 1;
          } else if (currTick > animator.getLastTick()) {
            this.view.renderAnimate(tweener.getStateAt(1));
            timer.stop();
          }

          int newTempo = view.getCorrectTempo(currTick++);
          timer.setDelay((int) (1.0 / newTempo * 1000));
        });
  }


}
