package cs3500.animator.view;

import java.io.IOException;

/**
 * a interface that controls the functionality of the all views.
 * allows for the controller to communicate with the view.
 */
public interface IAnimatorView {

  /**
   * draws the output for the view.
   *
   * @throws IOException if the output cannot be appended
   * @throws UnsupportedOperationException if the view does not render a text output.
   */
  void render(Appendable out) throws IOException, UnsupportedOperationException;


  /**
   * sets the view to loop or not.
   * @param loop boolean to loop or not
   * @throws UnsupportedOperationException if the view cannot change looping.
   */
  void setLoop(boolean loop) throws UnsupportedOperationException;


}
