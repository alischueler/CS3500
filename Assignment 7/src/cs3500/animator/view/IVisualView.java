package cs3500.animator.view;

import cs3500.animator.model.Shape;
import java.util.List;

/**
 * a interface that controls the functionality of the visual views.
 * allows for the controller to communicate with the view.
 */
public interface IVisualView extends IAnimatorView {

  /**
   * method to render the visual view. should be suppressed in all other views.
   *
   * @param shapes the shapes to be drawn.
   */
  void renderAnimate(List<Shape> shapes);

  /**
   * a getter to return the tempo of the view.
   *
   * @return the current tempo of the view
   */
  int getTempo();

}
