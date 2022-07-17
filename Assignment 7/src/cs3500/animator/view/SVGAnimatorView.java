package cs3500.animator.view;


import cs3500.animator.model.ChangeTime;
import cs3500.animator.model.Color;
import cs3500.animator.model.ICommands;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.SimpleBackground;
import cs3500.animator.model.TotalSize;
import java.io.IOException;
import java.util.List;


/**
 * a class that takes in a animator and translates that animator into svg code.
 */
public class SVGAnimatorView implements IAnimatorView {

  private final IViewModel<Shape> animator;
  private final int tempo;
  private boolean loop;


  /**
   * constructor for the SimpleAnimatorView.
   *
   * @param animator the IViewModel we want to use in the view
   * @param tempo    the tempo to run the svg at
   */
  public SVGAnimatorView(IViewModel<Shape> animator, int tempo) {
    if (animator == null) {
      throw new IllegalArgumentException("Null animator");
    }

    if (tempo < 1) {
      throw new IllegalArgumentException("tempo too low");
    }
    this.animator = animator;
    this.tempo = tempo;
    this.loop = true;
  }

  @Override
  public void render(Appendable out) throws IOException {
    StringBuilder soFar = new StringBuilder();
    initializeFile(soFar);
    for (Shape s : this.animator.getShapes()) {
      addSVGShapeToAppendable(soFar, s);
    }
    soFar.append("</svg>");
    try {
      out.append(soFar.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Appendable object is unable to transmit");
    }
  }

  /**
   * adds a specific shape to a String builder.
   *
   * @param soFar StringBuilder to append the svg commands to
   * @param s     the shape who's commands are being made
   */
  private void addSVGShapeToAppendable(StringBuilder soFar, Shape s) {
    String loopBackString = "";
    if (this.loop) {
      loopBackString = "base.begin+";
    }
    // assumes they are all ready sorted
    List<ICommands> sortedCommands = s.getCommands();

    try {
      initializeShapeSVG(soFar, sortedCommands.get(0), s.getShapeType().getType());
    } catch (IndexOutOfBoundsException e) {
      // do nothing
    }
    // looping over each command and adding it
    // assumes that sortedCommands is sorted
    String type = s.getShapeType().getType();
    String fill = "freeze";
    // for (String com : sortedCommands) {
    for (int i = 0; i < sortedCommands.size(); i++) {
      ICommands com = sortedCommands.get(i);
      makeSVGForCommand(soFar, com, loopBackString, type, fill);
    }
    soFar.append("<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
        + "attributeName=\"fill\" to=\"transparent\" fill=\"freeze\" />");

    // adding ending tag
    if (s.getShapeType().getType().equals("rectangle")) {
      soFar.append("</rect>");
    } else {
      soFar.append("</ellipse>");
    }

  }

  /**
   * makes the svg code for each command.
   *
   * @param soFar          StringBuilder to append the svg commands to
   * @param command        the command that the code is being made for
   * @param loopBackString the string to add in front of each start time
   * @param type           the type of the comand
   * @param fill           how should the shape be filled
   */
  void makeSVGForCommand(StringBuilder soFar, ICommands command, String loopBackString, String type,
      String fill) {
    String begin = loopBackString + getStartTime(command.getTicks());
    String dur = getDurTime(command.getTicks());

    int xBound = animator.getBackground().getXBound();
    int yBound = animator.getBackground().getYBound();
    Position startPos = command.getStartPosition();
    Position endPos = command.getEndPosition();
    TotalSize startSize = command.getStartSize();
    TotalSize endSize = command.getEndSize();

    if (type.equals("rectangle")) {

      makeAnimationCommand(soFar, begin, dur, "x", transform(startPos.getX(),
          xBound), transform(endPos.getX(), xBound), fill);
      makeAnimationCommand(soFar, begin, dur, "y",
          transform(startPos.getY(), yBound), transform(endPos.getY(), yBound), fill);
      makeAnimationCommand(soFar, begin, dur, "height", transformDouble(startSize.getTotalHeight()),
          transformDouble(endSize.getTotalHeight()), fill);
      makeAnimationCommand(soFar, begin, dur, "width", transformDouble(startSize.getTotalWidth()),
          transformDouble(endSize.getTotalWidth()),
          fill);
    } else {
      makeAnimationCommand(soFar, begin, dur, "cx", transform(startPos.getX(),
          xBound), transform(endPos.getX(), xBound), fill);
      makeAnimationCommand(soFar, begin, dur, "cy",
          transform(startPos.getY(), yBound), transform(endPos.getY(), yBound), fill);
      makeAnimationCommand(soFar, begin, dur, "ry", transformDouble(startSize.getTotalHeight()),
          transformDouble(endSize.getTotalHeight()), fill);
      makeAnimationCommand(soFar, begin, dur, "rx", transformDouble(startSize.getTotalWidth()),
          transformDouble(endSize.getTotalWidth()),
          fill);
    }

    makeAnimationCommand(soFar, begin, dur, "fill", makeColor(command.getStartColor()),
        makeColor(command.getEndColor()), fill);
  }

  /**
   * transforms a coddanate to within the bounds for the canvas.
   *
   * @param ogVal     the original value of the data
   * @param canvasVal a bound of the canvas
   * @return an adjusted position for the value of te
   */
  private String transform(double ogVal, int canvasVal) {
    int adjVal = (int) ogVal - canvasVal;
    return Integer.toString(adjVal);
  }

  /**
   * converts double to string.
   *
   * @param ogVal the original value of the data
   * @return a string of the ogVal
   */
  private String transformDouble(double ogVal) {
    return Integer.toString((int) ogVal);
  }


  /**
   * actually constructs the svg code with the given inputs and adds it to soFar.
   *
   * @param soFar     StringBuilder to append the svg commands to
   * @param begin     time to begin the animation
   * @param dur       the duration of the animation
   * @param type      the data adjusted durring the animation
   * @param startData start point of the data
   * @param endData   end point of the data
   * @param fill      the fill of the shape at the end
   */
  private void makeAnimationCommand(StringBuilder soFar, String begin, String dur, String type,
      String startData, String endData, String fill) {
    soFar.append("<animate attributeType=\"xml\" begin=\"").append(begin).append("ms\" dur=\"")
        .append(dur).append("ms\" attributeName=\"").append(type).append("\" from=\"")
        .append(startData).append(
        "\" to=\"").append(endData).append("\" fill=\"").append(fill).append("\" />");
  }

  /**
   * gets the dur time in milliseconds.
   *
   * @param c ChangeTime of start and end time
   * @return dur time of the command in milliseconds rounded to two decimal places
   */
  private String getDurTime(ChangeTime c) {
    double span = c.getEnd() - c.getStart();
    double dur = (span / (double) tempo) * 1000;
    return String.format("%.2f", dur);
  }

  /**
   * gets the start time in milliseconds.
   *
   * @param c ChangeTime of start and end time
   * @return start time of the command in milliseconds rounded to two decimal places
   */
  private String getStartTime(ChangeTime c) {
    double start = ((double) c.getStart() / (double) tempo) * 1000;
    return String.format("%.2f", start);
  }

  /**
   * makes a color in the format expected in SVG.
   *
   * @param c color object to be turned into a string
   * @return String in rgb(r,g,b) format
   */
  String makeColor(Color c) {
    return String.format("rgb(%1$s,%2$s,%3$s)", (int) c.getRed(), (int) c.getGreen(),
        (int) c.getBlue());
  }

  /**
   * initializing the shape in the svg code.
   *
   * @param soFar    StringBuilder to append the svg commands to
   * @param commands first command in the shape
   */
  void initializeShapeSVG(StringBuilder soFar, ICommands commands, String type) {
    // writes the starting point for the shape
    initializeShapeSVGHelper(soFar, type, commands.getShape(),
        transformDouble(commands.getStartPosition().getX()),
        transformDouble(commands.getStartPosition().getY()),
        transformDouble(commands.getStartSize().getTotalWidth()),
        transformDouble(commands.getStartSize().getTotalHeight()));
  }

  /**
   * initializing the shape in the svg code.
   *
   * @param soFar  StringBuilder to append the svg commands to
   * @param name   the name of the shape
   * @param x      starting x poition
   * @param y      starting y position
   * @param width  starting width
   * @param height starting height
   */
  private void initializeShapeSVGHelper(StringBuilder soFar, String type, String name, String x,
      String y, String width, String height) {
    // <rect id="P" x="200" y="200" width="50" height="100" fill="rgb(128,0,128)"
    // visibility="visible" >
    if (type.equals("rectangle")) {
      soFar.append("<rect id=\"").append(name).append("\" x=\"").append(x).append("\" y=\"")
          .append(y)
          .append("\" width=\"").append(width).append("\" height=\"").append(height)
          .append("\" fill=\"transparent\" visibility=\"visible\" >");
    } else {
      soFar.append("<ellipse id=\"").append(name).append("\" cx=\"").append(x).append("\" cy=\"")
          .append(y).append("\" rx=\"").append(width).append("\" ry=\"").append(height)
          .append("\" fill=\"transparent\" visibility=\"visible\" >");
    }
  }

  /**
   * initializes the svg file with the background. also makes the nessecary rectangle to loop over
   * animation
   *
   * @param soFar String builder which the initialized file text will be added to
   */
  void initializeFile(StringBuilder soFar) {
    SimpleBackground background = this.animator.getBackground();

    // opening svg tag and setting the backround
    soFar.append("<svg width=\"").append(background.getWidth()).append("\" height=\"")
        .append(background.getHeight())
        .append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");

    String duration =
        String.format("%.1f", ((double) this.animator.getLastTick() / (double) this.tempo) * 1000);

    soFar.append("<rect> <animate id=\"base\" begin=\"0;base.end\" dur=\"");
    soFar.append(duration).append("ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>")
        .append("</rect>");

  }

  @Override
  public void setLoop(boolean loop) {
    this.loop = loop;
  }
}
