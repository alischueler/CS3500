package cs3500.animator.controller;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IAnimatorView;
import cs3500.animator.view.SVGAnimatorView;
import cs3500.animator.view.TextualAnimatorView;
import java.io.IOException;

/**
 * An instance of IController which controls the text output of an animation through input from the
 * client.
 */
public class Controller implements IController {

  protected final IAnimator<Shape> animator;
  private final IAnimatorView view;

  /**
   * constructs a controller.
   *
   * @param model the model the controller will reference.
   * @param view  the view the controller will reference.
   */
  public Controller(IAnimator<Shape> model, IAnimatorView view) {
    if (model == null) {
      throw new IllegalArgumentException("model is null");
    }
    if (view == null) {
      throw new IllegalArgumentException("view is null");
    }
    checkInstance(view);
    this.animator = model;
    this.view = view;
  }

  /**
   * checks if the view if the correct instance of IAnimatorView for this controller.
   *
   * @param view the view to be examined
   */
  protected void checkInstance(IAnimatorView view) {
    if (!(view instanceof SVGAnimatorView || view instanceof TextualAnimatorView)) {
      throw new IllegalArgumentException("this controller supports textual view");
    }
  }

  @Override
  public void startAnimate(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("cant be null");
    }
    if (animator.isStarted()) {
      try {
        view.render(out);
      } catch (IOException e) {
        throw new IllegalArgumentException("could not append output");
      }
    }
  }
}
