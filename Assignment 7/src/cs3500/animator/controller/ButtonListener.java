package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.util.Map;

/**
 * a class that allows for running ActionListeners.
 * THIS CLASS WAS TAKEN DIRECTLY FROM LECTURE NOTES
 */
public class ButtonListener implements IButtonListener {
  Map<String,Runnable> buttonClickedActions;

  /**
   * Empty default constructor.
   */
  public ButtonListener() {
    //there is nothing to initialize
  }

  @Override
  public void setButtonClickedActionMap(Map<String,Runnable> map) {
    if (map == null) {
      throw new IllegalArgumentException("no null");
    }
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      throw new IllegalArgumentException("no null");
    }
    if (buttonClickedActions.containsKey(e.getActionCommand())) {

      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
