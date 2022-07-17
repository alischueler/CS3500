package cs3500.animator.view;

import cs3500.animator.model.Shape;
import java.util.List;

/**
 * An interface for painting and repainting onto a JPanel.
 */
public interface IVisualJPanel {

  /**
   * actually repaints the given shapes passed in.
   *
   * @param currShapes the shapes being painted.
   */
  void drawAnimation(List<Shape> currShapes);

}
