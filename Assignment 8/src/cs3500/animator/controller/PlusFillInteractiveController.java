package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.IPlusFillInteractiveView;
import java.util.Map;

/**
 * An implementation of IController that supports the plus shape and the outline option.
 */
public class PlusFillInteractiveController extends InteractiveController {

  protected IPlusFillInteractiveView view;

  /**
   * Contructs a PlusFillInteractiveController.
   *
   * @param model the model to be referenced
   * @param view  the view to be referenced.
   */
  public PlusFillInteractiveController(IAnimator<Shape> model, IAnimatorView view) {
    super(model, view);
    this.view = (IPlusFillInteractiveView) view;
  }

  /**
   * checks if the view if the correct instance of IAnimatorView for this controller.
   *
   * @param view the view to be examined.
   */
  protected void checkInstance(IAnimatorView view) {
    if (!(view instanceof IPlusFillInteractiveView)) {
      throw new IllegalArgumentException("this controller supports interactive view");
    }
  }

  /**
   * makes the map of buttons and their actions.
   *
   * @return map of button names and their actions
   */
  protected Map<String, Runnable> makeButtonMap() {
    // buttons to control animation
    Map<String, Runnable> map = super.makeButtonMap();
    map.put("fill", new FillAnimation());

    // menu buttons
    map.put("fill info", new FillInfo());
    return map;
  }

  /**
   * fills or unfills the shapes of the animation
   */
  protected class FillAnimation implements Runnable {

    @Override
    public void run() {
      view.setFill(!view.getFill());
    }
  }

  /**
   * starts the un-started or ended animation from the first tick.
   */
  protected class FillInfo implements Runnable {

    @Override
    public void run() {
      makePopUp("When the fill option is selected all of the shapes of the animation are\n"
          + "drawn with their shapes filled in. When the outline option is selected all of the\n"
          + "shapes of the animation are drawn only outlined with their given color.");
    }
  }

}
