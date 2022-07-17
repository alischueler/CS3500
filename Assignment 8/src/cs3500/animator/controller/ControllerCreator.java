package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.ViewCreator.ViewType;

/**
 * factory class to make the controller based on views.
 */
public class ControllerCreator {

  /**
   * factory method to make a controller.
   *
   * @param type  the type of view that will be referenced.
   * @param model the model used to initiate the controller
   * @param view  the view used to initiate the controller
   * @return an instance of icontroller.
   */
  public static IController create(ViewType type, IAnimator<Shape> model, IAnimatorView view) {
    IController controller;
    switch (type) {
      case TEXT:
      case SVG:
      case SVGPLUS:
        controller = new Controller(model, view);
        break;
      case VISUAL:
        controller = new VisualController(model, view);
        break;
      case INTERACTIVE:
        controller = new InteractiveController(model, view);
        break;
      case LEVEL1:
        controller = new PlusFillInteractiveController(model, view);
        break;
      case LEVEL2:
        controller = new DiscreteController(model, view);
        break;
      case LEVEL3:
        controller = new SlowMoController(model, view);
        break;
      default:
        controller = null;
        break;
    }
    return controller;
  }

}
