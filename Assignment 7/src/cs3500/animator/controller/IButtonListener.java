package cs3500.animator.controller;

import java.awt.event.ActionListener;
import java.util.Map;

/**
 * requires the necessary functionality to make button listeners.
 * also adds a button listener map.
 */
public interface IButtonListener extends ActionListener {

  /**
   * Set the map for button pressed events. The name relating to the button events is the name of
   * the button that was set using jswing.
   * @param map map to be set
   * @throws IllegalArgumentException if map is null
   */
  void setButtonClickedActionMap(Map<String,Runnable> map) throws IllegalArgumentException;

}
