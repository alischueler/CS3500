package cs3500.animator.view;

/**
 * an public interface to allow for the option to outline the animation.
 */
public interface IPlusFillInteractiveView extends IInteractiveView {

  /**
   * Sets the animation to be filled or outlined.
   *
   * @param fill the boolean deciding if the animation is filled or not
   */
  void setFill(boolean fill);

  /**
   * determines if the animations should be filled or not.
   *
   * @return true if the animation should be filled, false if otherwise
   */
  boolean getFill();

}
