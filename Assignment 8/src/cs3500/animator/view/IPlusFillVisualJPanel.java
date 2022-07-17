package cs3500.animator.view;

/**
 * a public interface to allow for the visualjpanel to draw a new shape and outline the shapes.
 */
public interface IPlusFillVisualJPanel extends IVisualJPanel {

  /**
   * sets the given view to this IVisualJpanel.
   *
   * @param view the view to allow the Jpanel to access
   */
  void setView(IPlusFillInteractiveView view);

}
