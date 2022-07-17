package cs3500.animator.view;

import cs3500.animator.model.ISimpleCommands;
import cs3500.animator.model.ITempoCommands;
import cs3500.animator.model.Shape;

/**
 * an implementation of ISlowMoView that shares common methods with InteractiveView. Allows a user
 * to see an animation in slowmo.
 */
public class SlowMoView extends InteractiveAnimationView implements ISlowMoView {

  private IViewModel<Shape> model;
  private int ogTempo;

  /**
   * Constructs an interactive animation view.
   *
   * @param jpanel the jpanel the animation will be displayed on.
   * @param model  the view model the view will reference
   * @param tempo  the tempo the animation will run at.
   */
  public SlowMoView(IVisualJPanel jpanel,
      IViewModel<Shape> model, int tempo) {
    super(jpanel, model, tempo);
    this.model = model;
    this.ogTempo = tempo;
  }

  @Override
  public int getCorrectTempo(int tick) {
    for (ISimpleCommands c : model.getCommands()) {
      if (c.getShape().equals("slow-mo")) {
        if ((c.getTicks().getStart() <= tick) && (c.getTicks().getEnd() >= tick)) {
          super.tempo = ((ITempoCommands) c).getTempo();
          super.updateTempo();
          return tempo;
        }
      }
    }
    this.tempo = ogTempo;
    super.updateTempo();
    return this.ogTempo;
  }

  @Override
  public void setTempo(int tempo) {
    this.ogTempo = tempo;
  }

}
