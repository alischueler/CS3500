package cs3500.animator.tweener;

import cs3500.animator.model.IColor;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.ITotalSize;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * a object that tweens a given list of shapes. gets the state of the shapes at a given tick. call
 * this repeadtly to make a animator.
 */
public class Tweener implements ITweener<Shape> {

  private final IViewModel<Shape> delegate;
  private List<Shape> listOfShapes;

  /**
   * constructor for the Tweener.
   *
   * @param delegate the animator that's being used
   */
  public Tweener(IViewModel<Shape> delegate) {
    if (!delegate.isStarted()) {
      throw new IllegalStateException("not started");
    }
    this.delegate = delegate;
  }

  /**
   * sees if the tick is valid.
   *
   * @param tick the tick that is being checked
   * @throws IllegalArgumentException if tick is negative
   */
  private void checkTick(int tick) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("tick is negative");
    }
  }

  @Override
  public List<Shape> getStateAt(int tick) throws IllegalArgumentException,
      IllegalStateException {
    checkTick(tick);
    return updateToState(tick);
  }

  /**
   * tweens all the shapes in the model to a spefic tick.
   *
   * @param tick the tick that the game is being made for
   */
  private List<Shape> updateToState(int tick) {
    getCurrent(tick);
    for (Shape shape : this.listOfShapes) {
      tweenShape(tick, shape);
    }
    return listOfShapes;
  }


  /**
   * gets the current shapes cureent comand is updated.
   *
   * @param tick the tick to be tweened to
   * @return list of tweened shapes
   * @throws IllegalStateException if the model is not started
   */
  public List<Shape> getCurrent(int tick) throws IllegalStateException {
    listOfShapes = new ArrayList<>();
    for (Shape s : getShapes()) {
      if (s.compareTick(tick)) {
        s.updateShape(tick);
        listOfShapes.add(s);
      }
    }
    return listOfShapes;
  }


  /**
   * returns a copy of the current shapes.
   *
   * @return copy of the current schapes.
   */
  public List<Shape> getShapes() {
    List<Shape> shapeList = new ArrayList<>();
    shapeList.addAll(this.delegate.getShapes());
    return shapeList;
  }

  /**
   * moves a shape to the given tick. mutates everything color, position, size.
   *
   * @param shape the shape to be tweened
   * @param tick  the tick to be tweened to
   */
  private void tweenShape(int tick, Shape shape) {
    // tweening the different aspects of the shape
    changeSize(tick, shape);
    changeColor(tick, shape);
    changePosition(tick, shape);
  }


  /**
   * calcuates the position of the data based on the inputs passed. is used in the change methods.
   *
   * @param posToCalculate the current tick of the animator its: currtick - shape.startTime this
   *                       calcuation would be redundent if done in this method
   * @param amountOfTime   the amount of time the enimation exists for
   * @param startPoint     the starting point of the shape
   * @param endPoint       the ending point of the shape
   * @return the poition of the data point for the shape
   */
  private static double calculatePoint(int posToCalculate, int amountOfTime, double startPoint,
      double endPoint) {
    double dist = endPoint - startPoint;
    if (amountOfTime == 0) {
      return startPoint;
    } else {
      double slopeWidth = dist / amountOfTime;
      return slopeWidth * posToCalculate + startPoint;
    }
  }


  /**
   * changes the size of the shape to be the correct size at the given tick.
   *
   * @param tick  the tick to move the data to
   * @param shape the shape that's being move=d
   */
  private void changeSize(int tick, Shape shape) {
    int posToCalculate = tick - shape.getCurrCommand().getTicks().getStart();
    int amountOfTime = shape.getCurrCommand().getTicks().getEnd() -
        shape.getCurrCommand().getTicks().getStart();
    ITotalSize startPos = shape.getCurrCommand().getStartSize();
    ITotalSize endPos = shape.getCurrCommand().getEndSize();

    double newWidth = calculatePoint(posToCalculate, amountOfTime, startPos.getTotalWidth(),
        endPos.getTotalWidth());

    double newHeight = calculatePoint(posToCalculate, amountOfTime, startPos.getTotalHeight(),
        endPos.getTotalHeight());

    shape.updateCurrSize(newWidth, newHeight);
  }


  /**
   * changes the color of the shape to be the correct color at the given tick.
   *
   * @param shape the shape that's being move=d
   */
  private void changeColor(int tick, Shape shape) {
    int posToCalculate = tick - shape.getCurrCommand().getTicks().getStart();
    int amountOfTime = shape.getCurrCommand().getTicks().getEnd() -
        shape.getCurrCommand().getTicks().getStart();
    IColor startPos = shape.getCurrCommand().getStartColor();
    IColor endPos = shape.getCurrCommand().getEndColor();

    double newRed = calculatePoint(posToCalculate, amountOfTime, startPos.getRed(),
        endPos.getRed());

    double newBlue = calculatePoint(posToCalculate, amountOfTime, startPos.getBlue(),
        endPos.getBlue());

    double newGreen = calculatePoint(posToCalculate, amountOfTime, startPos.getGreen(),
        endPos.getGreen());

    shape.updateCurrColor(newRed, newGreen, newBlue);
  }


  /**
   * changes the position of the shape to be the correct position at the given tick.
   *
   * @param tick  the tick to move the data to
   * @param shape the shape that's being moved
   */
  private void changePosition(int tick, Shape shape) {
    int posToCalculate = tick - shape.getCurrCommand().getTicks().getStart();
    int amountOfTime = shape.getCurrCommand().getTicks().getEnd() -
        shape.getCurrCommand().getTicks().getStart();
    IPosition startPos = shape.getCurrCommand().getStartPosition();
    IPosition endPos = shape.getCurrCommand().getEndPosition();

    double newXPos = calculatePoint(posToCalculate, amountOfTime, startPos.getX(),
        endPos.getX());

    double newYPos = calculatePoint(posToCalculate, amountOfTime, startPos.getY(),
        endPos.getY());

    shape.updateCurrPos(newXPos, newYPos);
  }


}
