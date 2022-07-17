import cs3500.animator.controller.ButtonListener;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IInteractiveView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Mock Interactive View to work for testing the controller and listeners. An instance of
 * IInteractiveView.
 */
public class MockInteractiveView implements IInteractiveView {

  private int tempo;
  private boolean loop;
  private List<ActionListener> listeners;
  private String choice;
  private ButtonListener buttonList;

  /**
   * constructs a mock interactive view.
   *
   * @param choice     the string of what button has been pressed.
   * @param buttonList the buttonlistener for all the buttons in this view.
   * @param tempo      the tempo the view has been set to run at.
   */
  MockInteractiveView(String choice, ButtonListener buttonList, int tempo) {
    this.tempo = tempo;
    this.loop = false;
    listeners = new ArrayList<>();
    this.choice = choice;
    this.buttonList = buttonList;
  }

  @Override
  public void setTempo(int tempo) throws UnsupportedOperationException {
    this.tempo = tempo;
  }

  @Override
  public boolean getLoop() {
    return loop;
  }

  @Override
  public void clearText() {
    // do nothing
  }

  @Override
  public void updateTempo() {
    // do nothing
  }

  @Override
  public void addActionListener(ActionListener feature) throws UnsupportedOperationException {
    listeners.add(feature);
  }

  @Override
  public String getNewTempo() {
    return "500";
  }

  @Override
  public void renderAnimate(List<Shape> shapes) throws UnsupportedOperationException {
    if (choice.equals("START")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "START"));
    }
    if (choice.equals("PAUSE")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "PAUSE"));
    }
    if (choice.equals("RESUME")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "RESUME"));
    }
    if (choice.equals("RESTART")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "RESTART"));
    }
    if (choice.equals("LOOP")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "LOOP"));
    }
    if (choice.equals("change")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "change"));
    }
    if (choice.equals("start info")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "start info"));
    }
    if (choice.equals("pause info")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "pause info"));
    }
    if (choice.equals("resume info")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "resume info"));
    }
    if (choice.equals("restart info")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "restart info"));
    }
    if (choice.equals("loop info")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "loop info"));
    }
    if (choice.equals("tempo info")) {
      buttonList.actionPerformed(new ActionEvent(this, 2, "tempo info"));
    }

  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public void render(Appendable out) throws IOException, UnsupportedOperationException {
    // do nothing
  }

  @Override
  public void setLoop(boolean loop) throws UnsupportedOperationException {
    this.loop = loop;
  }
}
