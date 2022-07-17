package cs3500.animator.view;

import cs3500.animator.model.ICommands;
import cs3500.animator.model.Shape;
import java.io.IOException;

/**
 * draws a simple string representation of a animation. this class acts as the view for the textual
 * based animator.
 */
public class TextualAnimatorView implements IAnimatorView {

  protected IViewModel<Shape> animator;
  private final int tempo;

  /**
   * constructor for the SimpleAnimatorView.
   *
   * @param animator the IAnimator we want to use in the view
   */
  public TextualAnimatorView(IViewModel<Shape> animator, int tempo) {
    if (animator == null) {
      throw new IllegalArgumentException("Null animator");
    }
    if (tempo < 1) {
      throw new IllegalArgumentException("tempo is less than 1");
    }
    this.animator = animator;
    this.tempo = tempo;
  }

  /**
   * gets the start time in milliseconds.
   *
   * @param startTick the string of the startTick
   * @return start time of the command in milliseconds rounded to two decimal places
   */
  private String getStartTime(int startTick) {
    double start = ((double) startTick / (double) tempo);
    return String.format("%.2f", start);
  }

  /**
   * gets the end time in milliseconds.
   *
   * @param endTick the String of the endTick
   * @return start time of the command in milliseconds rounded to two decimal places
   */
  private String getEndTime(int endTick) {
    double start = ((double) endTick / (double) tempo);
    return String.format("%.2f", start);
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("canvas " + animator.getBackground().toString() + "\n");
    for (Shape s : animator.getShapes()) {
      out.append("shape " + s.getShapeName() + " " + s.getShapeType().getType() + "\n");
      for (ICommands command : s.getCommands()) {
        //getting all starting info
        String startTime = getStartTime(command.getTicks().getStart());
        out.append("motion " + s.getShapeName() + " " + startTime + " ");
        StringBuilder startValues = new StringBuilder();
        startValues.append((command.getStartPosition()).toString());
        startValues.append(" ");
        startValues.append(command.getStartSize().toString());
        startValues.append(" ");
        startValues.append(command.getStartColor());

        //getting all ending values
        String endTime = getEndTime(command.getTicks().getEnd());
        StringBuilder endValues = new StringBuilder();
        endValues.append(endTime + " ");
        endValues.append(command.getEndPosition().toString());
        endValues.append(" ");
        endValues.append(command.getEndSize().toString());
        endValues.append(" ");
        endValues.append(command.getEndColor().toString());

        String startString = startValues.toString();
        String endString = endValues.toString();
        out.append(startString);
        out.append("     ");
        out.append(endString);
        out.append("\n");
      }
      if (animator.getShapes().indexOf(s) != animator.getShapes().size() - 1) {
        out.append("\n");
      }
    }
    return out.toString();
  }

  @Override
  public void render(Appendable out) throws IOException {
    if (out == null) {
      throw new IllegalArgumentException("appendable is null");
    }
    try {
      out.append(toString());
    } catch (IOException e) {
      throw new IllegalArgumentException("could not append output");
    }
  }

  @Override
  public void setLoop(boolean loop) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("does not support this operation");
  }


}
