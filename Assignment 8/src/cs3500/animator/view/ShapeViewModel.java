package cs3500.animator.view;

import cs3500.animator.model.IBackground;
import cs3500.animator.model.ISimpleCommands;
import cs3500.animator.model.Shape;
import cs3500.animator.model.IAnimator;
import java.util.List;

/**
 * a IViewModel that uses composition to make all the methods of the given model read only.
 */
public class ShapeViewModel implements IViewModel<Shape> {

  IAnimator<Shape> model;

  /**
   * constructor for the ShapeViewModel.
   *
   * @param model IAnimator to be adapted to a IViewModel
   */
  public ShapeViewModel(IAnimator<Shape> model) {
    this.model = model;
  }


  @Override
  public void startAnimator() throws IllegalArgumentException {
    this.model.startAnimator();
  }

  @Override
  public List<Shape> getShapes() {
    return this.model.getShapes();
  }

  @Override
  public IBackground getBackground() {
    return this.model.getBackground();
  }

  @Override
  public int getLastTick() {
    return this.model.getLastTick();
  }

  @Override
  public boolean isStarted() {
    return this.model.isStarted();
  }

  @Override
  public List<ISimpleCommands> getCommands() {
    return this.model.getCommands();
  }
}
