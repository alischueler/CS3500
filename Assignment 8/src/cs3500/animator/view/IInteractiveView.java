package cs3500.animator.view;

import cs3500.animator.controller.IButtonListener;

/**
 * a interface that controls the functionality of the interactive views. allows for the controller
 * to communicate with the view.
 */
public interface IInteractiveView extends IVisualView {

  /**
   * a setter to change the tempo of the view.
   *
   * @param tempo the desired tempo of the view
   */
  void setTempo(int tempo);


  /**
   * sees if the animation is supposed to loop or not.
   */
  boolean getLoop();

  /**
   * clears the text of the set tempo box.
   */
  void clearText();

  /**
   * updates the tempo of the jlabel.
   */
  void updateTempo();


  /**
   * Adds the given ActionListener to the view.
   *
   * @param feature the feature to be added to the view.
   */
  void addActionListener(IButtonListener feature);


  /**
   * gets the new tempo from the view types by the user.
   *
   * @return the tempo entered by the user
   */
  String getNewTempo();

}
