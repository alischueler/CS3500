package cs3500.animator.view;

import cs3500.animator.model.ICommands;
import cs3500.animator.model.IPosition;
import cs3500.animator.model.ITotalSize;
import cs3500.animator.model.Shape;

/**
 * an instance of IAnimator view that shares common methods with svganimator view but allows a plus
 * shape to be drawn in svg.
 */
public class SVGWithPlusView extends SVGAnimatorView {

  /**
   * constructor for the SimpleAnimatorView.
   *
   * @param animator the IViewModel we want to use in the view
   * @param tempo    the tempo to run the svg at
   */
  public SVGWithPlusView(IViewModel<Shape> animator, int tempo) {
    super(animator, tempo);
  }


  /**
   * initializing the shape in the svg code.
   *
   * @param soFar    StringBuilder to append the svg commands to
   * @param commands first command in the shape
   */
  @Override
  protected void initializeShapeSVG(StringBuilder soFar, ICommands commands, String type) {
    // writes the starting point for the shape
    if (type.equals("plus")) {
      makeStartPlus(soFar, commands);
    } else {
      super.initializeShapeSVG(soFar, commands, type);
    }
  }

  /**
   * appends the plus shape information to what has been created for the svg file so far.
   *
   * @param soFar    the svg file so far created.
   * @param commands the commands of the start plus shape.
   */
  private void makeStartPlus(StringBuilder soFar, ICommands commands) {
    soFar.append("<polygon id=\"").append(commands.getShape()).append("\" points=\"")
        .append(makePoints(commands.getStartSize(), commands.getStartPosition()))
        .append("\" fill=\"transparent\" visibility=\"visible\" >");
  }

  /**
   * adds a specific shape to a String builder.
   *
   * @param soFar StringBuilder to append the svg commands to
   * @param s     the shape who's commands are being made
   */
  @Override
  protected void addSVGShapeToAppendable(StringBuilder soFar, Shape s) {

    super.addSVGShapeToAppendable(soFar, s);
    if (s.getShapeType().getType().equals("plus")) {
      soFar.append("</polygon>");
    }
    // adding ending tag

  }

  @Override
  protected void makeSVGForCommand(StringBuilder soFar, ICommands command, String loopBackString,
      String type, String fill) {
    super.makeSVGForCommand(soFar, command, loopBackString, type, fill);
    if (type.equals("plus")) {
      String begin = loopBackString + getStartTime(command.getTicks());
      String dur = getDurTime(command.getTicks());

      makeAnimationCommand(soFar, begin, dur, "points", makePoints(command.getStartSize(),
          command.getStartPosition()), makePoints(command.getEndSize(),
          command.getEndPosition()), fill);
      makeAnimationCommand(soFar, begin, dur, "fill", makeColor(command.getStartColor()),
          makeColor(command.getEndColor()), fill);
    }
  }

  /**
   * makes the points of the plus shape for the svg file.
   *
   * @param size the size of the plus.
   * @param pos  the position of the plus.
   * @return the string of the points of the plus shape.
   */
  private String makePoints(ITotalSize size, IPosition pos) {
    int xBound = animator.getBackground().getXBound();
    int yBound = animator.getBackground().getYBound();
    StringBuilder out = new StringBuilder();
    for (int i = 1; i <= 12; i++) {
      out.append(findPointsX(i, size, pos) - xBound).append(",");
      out.append(findPointsY(i, size, pos) - yBound).append(" ");
    }
    return out.toString();
  }

  /**
   * finds the correct x position of the plus shape.
   *
   * @param i the step of drawing the plus shape.
   * @return the double representing the x coordinate of the plus.
   */
  private double findPointsX(int i, ITotalSize size, IPosition pos) {
    double width = size.getTotalWidth();
    double x = pos.getX();
    switch (i) {
      case 1:
      case 8:
      case 9:
      case 12:
        return x + (width / 4.0);
      case 2:
      case 3:
      case 6:
      case 7:
        return x + (width * (3.0 / 4.0));
      case 4:
      case 5:
        return x + width;
      case 10:
      case 11:
        return x;
      default:
        return 0.0;
    }
  }

  /**
   * finds the correct y position of the plus shape.
   *
   * @param i the step of drawing the plus shape.
   * @return the double representing the y coordinate of the plus.
   */
  private double findPointsY(int i, ITotalSize size, IPosition pos) {
    double height = size.getTotalHeight();
    double y = pos.getY();
    switch (i) {
      case 1:
      case 2:
        return y;
      case 8:
      case 7:
        return y + height;
      case 9:
      case 6:
      case 5:
      case 10:
        return y + (height * (3.0 / 4.0));
      case 12:
      case 3:
      case 4:
      case 11:
        return y + (height / 4.0);
      default:
        return 0.0;
    }
  }
}

