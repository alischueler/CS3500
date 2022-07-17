package view;

import model.IAnimator;
import java.util.List;
import java.util.Map;
import model.Shape;

/**
 * draws a simple string representation of a animation.
 * this class acts as the view for the textual based animator.
 */
public class SimpleAnimatorView {
  List<Shape> shapes;
  StringBuilder log;

  /**
   * constructor for the SimpleAnimatorView.
   * @param shapes the shapes we want to represent in the textual view
   */
  public SimpleAnimatorView(List<Shape> shapes) {
    this.shapes = shapes;
    this.log = new StringBuilder();
  }

  public void render() {
    this.log.append(toString());
  }

  @Override
  public String toString() {
    String out = "";
    out += "canvas\n";
    for (Shape s : shapes) {
      out += "shape " + s.getShapeName() + " " + s.getShapeType() + "\n";
      out += s.getShapeInfo() + "\n";
      if (shapes.size() != shapes.indexOf(s) + 1) {
        out += "\n";
      }
    }
    return out;
  }
}
