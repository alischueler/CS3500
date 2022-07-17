package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.tweener.Tweener;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.IVisualView;
import cs3500.animator.view.ShapeViewModel;
import cs3500.animator.view.VisualAnimationView;
import java.util.List;
import javax.swing.Timer;

/**
 * An instance of IController which controls the animation through input from the client.
 */
public class VisualController extends Controller {

  protected int currTick = 1;
  protected Timer timer;
  private final IVisualView view;

  /**
   * constructs a visual controller.
   *
   * @param model the model the controller will reference.
   * @param view  the view the controller will reference.
   */
  public VisualController(IAnimator<Shape> model, IAnimatorView view) {
    super(model, view);
    this.view = (IVisualView) view;
  }

  /**
   * checks if the view if the correct instance of IAnimatorView for this controller.
   *
   * @param view the view to be examined
   */
  protected void checkInstance(IAnimatorView view) {
    if (!(view instanceof VisualAnimationView) || (view instanceof IInteractiveView)) {
      throw new IllegalArgumentException("this controller supports visual view");
    }
  }

  @Override
  public void startAnimate(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("appendable is null");
    }
    if (animator.isStarted()) {
      initializeTimer();
      timer.start();
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
          if (currTick > animator.getLastTick()) {
            timer.stop();
          }
        });
  }
}
