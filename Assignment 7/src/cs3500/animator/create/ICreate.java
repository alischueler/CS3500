package cs3500.animator.create;

import java.io.IOException;

/**
 * An interface that allows for animations to be created.
 */
public interface ICreate {

  /**
   * a method that is called to make the animation.
   *
   * @param out the Appendable to write the data too
   * @throws IOException if the append on the appendable fails
   */
  void makeAnimation(Appendable out) throws IOException;


}
