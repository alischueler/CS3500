import cs3500.animator.model.IAnimator;
import cs3500.animator.model.ICommands;
import cs3500.animator.model.ISimpleCommands;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleBackground;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * a class to mock a model for testing.
 */
public class MockModel implements IAnimator<Shape> {

  private Appendable ap;
  private List<Shape> shapes;
  private List<ISimpleCommands> commands;

  /**
   * a constructor to mock a model.
   *
   * @param ap appendable to write to
   */
  public MockModel(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void startAnimator() throws IllegalArgumentException {
    this.shapes = new ArrayList<>();
    this.commands = new ArrayList<>();
  }

  @Override
  public void addCommand(ICommands command) throws IllegalArgumentException {
    commands.add(command);
    try {
      ap.append(command + "added to the animation");
    } catch (IOException e) {
      throw new IllegalArgumentException("could not append output");
    }
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    shapes.add(shape);
    try {
      ap.append(shape + "added to the animation");
    } catch (IOException e) {
      throw new IllegalArgumentException("could not append output");
    }
  }

  @Override
  public void removeShape(Shape s) throws IllegalArgumentException {
    shapes.remove(s);
    try {
      ap.append(s + "removed from the animation");
    } catch (IOException e) {
      throw new IllegalArgumentException("could not append output");
    }

  }

  @Override
  public void removeCommand(ICommands command) throws IllegalArgumentException {
    commands.remove(command);
    try {
      ap.append(command + "removed from the animation");
    } catch (IOException e) {
      throw new IllegalArgumentException("could not append output");
    }
  }

  @Override
  public List<Shape> getShapes() {
    return shapes;
  }

  @Override
  public SimpleBackground getBackground() {
    return new SimpleBackground(3, 34, 45, 44);
  }

  @Override
  public int getLastTick() {
    return 0;
  }

  @Override
  public boolean isStarted() {
    return true;
  }

  @Override
  public List<ISimpleCommands> getCommands() {
    return commands;
  }
}
