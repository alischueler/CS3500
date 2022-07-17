import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.IButtonListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * tests th button listener class.
 */
public class ButtonListenerTest {

  IButtonListener bl;
  StringBuilder mockString;
  Map<String, Runnable> mapOfRunnable;


  protected void examples() {
    bl = new ButtonListener();
    mockString = new StringBuilder();

    mapOfRunnable = new HashMap<>();
    mapOfRunnable.put("test", new MutateString());

  }

  /**
   * a runnable to test the actionPerformed method.
   */
  public class MutateString implements Runnable {

    @Override
    public void run() {
      mockString.append("Runnable run");
    }
  }


  // action performed null
  @Test(expected = IllegalArgumentException.class)
  public void testMapNull() {
    examples();
    bl.setButtonClickedActionMap(null);
  }

  // action performed null
  @Test(expected = IllegalArgumentException.class)
  public void testActPrefNull() {
    examples();
    bl.setButtonClickedActionMap(mapOfRunnable);
    bl.actionPerformed(null);
  }

  // testing the action performed and adding a map works
  @Test
  public void actionPerformedAndAddMap() {
    examples();
    bl.setButtonClickedActionMap(mapOfRunnable);
    bl.actionPerformed(new ActionEvent(1, 1, "test"));
    assertEquals(mockString.toString(), "Runnable run");
  }


}
