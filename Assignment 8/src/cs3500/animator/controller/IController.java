package cs3500.animator.controller;

/**
 * represents a controller for for the MVC architecture of the animator.
 */
public interface IController {

  /**
   * starts the animation (textual or visual).
   *
   * @param out the appendable to append the text output to
   */
  void startAnimate(Appendable out);
}
