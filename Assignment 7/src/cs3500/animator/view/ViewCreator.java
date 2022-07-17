package cs3500.animator.view;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;

/**
 * factory class to make the views. currently supports TEX(), SVG(), VISUAL(), INTERACTIVE();
 */
public class ViewCreator {

  /**
   * the type of the views that can be made.
   */
  public enum ViewType {
    TEXT(), SVG(), VISUAL(), INTERACTIVE();
  }

  /**
   * factory method to make a view.
   *
   * @param type  they of the view.
   * @param tempo tempo to give the view
   * @param model model to give the view
   * @return made view with the given params
   */
  public static IAnimatorView create(ViewType type, int tempo, IAnimator<Shape> model) {
    IAnimatorView view;
    switch (type) {
      case TEXT:
        view = new TextualAnimatorView(new ShapeViewModel(model), tempo);
        break;
      case SVG:
        view = new SVGAnimatorView(new ShapeViewModel(model), tempo);
        break;
      case VISUAL:
        view = new VisualAnimationView(new ShapeViewModel(model), tempo);
        break;
      case INTERACTIVE:
        view = new InteractiveAnimationView(new VisualJPanel(new ShapeViewModel(model)),
            new ShapeViewModel(model), tempo);
        break;
      default:
        view = null;
        break;
    }
    return view;
  }
}