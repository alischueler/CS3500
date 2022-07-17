package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class to represent basic shapes to be changed by the model.
 */
public class BasicShape implements Shape {

  protected final String name;
  protected final String shapeType;
  //INVARIANT: is not negative
  protected int startTick;
  //INVARIANT: will be greater than start tick
  protected int endTick;
  protected Position startPos;
  protected Position endPos;
  protected Color startColor;
  protected Color endColor;
  protected TotalSize startDimensions;
  protected TotalSize endDimensions;

  protected Position currPos;
  protected Color currColor;
  protected TotalSize currDimensions;

  protected List<String> information;


  /**
   * Constructs a BasicShape to be changed.
   *
   * @param name            represents the identifying name of the basic shape.
   * @param shapeType       represents the type of basic shape that it is.
   * @param startTick       represents the start time of the change of the shape.
   * @param endTick         represents the end time of the change of the shape.
   * @param startPos        represents the position the shape starts at.
   * @param endPos          represents the position the shape ends at.
   * @param startColor      represents the color the shape starts as.
   * @param endColor        represents the color the shape ends at.
   * @param startDimensions represents the total dimensions the shape starts with.
   * @param endDimensions   represents the total dimensions the shape ends with.
   */
  public BasicShape(String name, String shapeType, int startTick, int endTick, Position startPos,
      Position endPos, Color startColor, Color endColor, TotalSize startDimensions,
      TotalSize endDimensions) {
    if (startTick < 0) {
      throw new IllegalArgumentException("Start tick cannot be negative");
    }
    if (startTick >= endTick) {
      throw new IllegalArgumentException("Start must be > than end");
    }

    this.name = name;
    this.shapeType = shapeType;
    this.startTick = startTick;
    this.endTick = endTick;
    this.startPos = startPos;
    this.endPos = endPos;
    this.startColor = startColor;
    this.endColor = endColor;
    this.startDimensions = startDimensions;
    this.endDimensions = endDimensions;

    this.currPos = new Position(startPos.getX(), startPos.getY());
    this.currColor = new Color(startColor.getRed(), startColor.getBlue(),
        startColor.getGreen());
    this.currDimensions = new TotalSize(startDimensions.getTotalHeight(),
        startDimensions.getTotalWidth());

    this.information = new ArrayList<>();
  }

  public BasicShape(String name, String shapeType) {
    this.name = name;
    this.shapeType = shapeType;
    this.information = new ArrayList<>();
  }


  @Override
  public String getShapeName() {
    return this.name;
  }

  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }


  @Override
  public Color getStartColor() {
    checkNull(this.startColor, "Start Color not initialized");
    return this.startColor;
  }

  @Override
  public Color getEndColor() {
    checkNull(this.endColor, "End Color not initialized");
    return this.endColor;
  }

  @Override
  public Position getStartPosition() {
    checkNull(this.startPos, "Start Position not initialized");
    return this.startPos;
  }

  @Override
  public Position getEndPosition() {
    checkNull(this.endPos, "End Position not initialized");
    return this.endPos;
  }

  @Override
  public Color getCurrColor() {
    checkNull(this.currColor, "Current Color not initialized");
    return this.currColor;
  }

  @Override
  public Position getCurrentPosition() {
    checkNull(this.currPos, "Current Position not initialized");
    return this.currPos;
  }

  @Override
  public TotalSize getCurrentDimension() {
    checkNull(this.currDimensions, "Current Dimension not initialized");
    return this.currDimensions;
  }

  @Override
  public TotalSize getStartDimension() {
    checkNull(this.startDimensions, "Start Dimension not initialized");
    return this.startDimensions;
  }

  @Override
  public TotalSize getEndDimension() {
    checkNull(this.endDimensions, "End Dimension not initialized");
    return this.endDimensions;
  }


  @Override
  public void setStartTime(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("start cannot be negative");
    }
    this.startTick = time;
  }

  @Override
  public void setEndTime(int time) {

    if (this.startTick >= time) {
      throw new IllegalArgumentException("end tick cannot be before start tick");
    }
    this.endTick = time;
  }

  @Override
  public void setEndPosition(Position newEndP) {
    this.endPos = newEndP;
  }

  @Override
  public void setStartPosition(Position newStartP) {
    this.startPos = newStartP;
  }

  @Override
  public void setStartColor(Color c) {
    this.startColor = c;
  }

  @Override
  public void setEndColor(Color c) {
    this.endColor = c;
  }

  @Override
  public void setStartSize(TotalSize size) {
    this.startDimensions = size;
  }

  @Override
  public void setEndSize(TotalSize size) {
    this.endDimensions = size;
  }

  @Override
  public void updateCurrPos(double x, double y) {
    if (this.currPos == null) {
      this.currPos = new Position(x, y);
    }
    this.currPos.updatePos(x, y);
  }

  @Override
  public void updateCurrColor(double r, double g, double b) {
    if (this.currColor == null) {
      this.currColor = new Color(r, g, b);
    }
    this.currColor.updateColor(r, g, b);
  }

  @Override
  public void updateCurrSize(double h, double w) {
    if (this.currDimensions == null) {
      this.currDimensions = new TotalSize(h, w);
    }
    this.currDimensions.updateDimensions(h, w);
  }

  @Override
  public String getShapeInfo() {
    String out = " ";
    for (String s : information) {
      out += s + "\n";
    }
    return out;
  }

  @Override
  public void addToInfo() {
    this.information.add(toString());
  }

  /**
   * Throws an IllegalState if the given field is null, else do nothing.
   *
   * @param field the field to be checked if null or not
   * @param msg   the message to put in the IllegalState
   * @throws IllegalStateException if the given field is null
   */
  private void checkNull(Object field, String msg) throws IllegalStateException {
    if (field == null) {
      throw new IllegalStateException(msg);
    }
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof BasicShape)) {
      return false;
    }

    BasicShape that = (BasicShape) a;

    return this.name.equals(that.name) &
        this.shapeType.equals(that.shapeType) &
        this.currPos.equals(that.currPos) &
        this.currColor.equals(that.currColor) &
        this.currDimensions.equals(that.currDimensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.shapeType, this.currPos, this.currColor,
        this.currDimensions);
  }

  @Override
  public String toString() {
    try {
      return this.name + " " + this.shapeType + " " + startTick + " " + startPos + " " +
          startDimensions + " " + startColor + " " + endTick + " " + endPos + " " + endDimensions +
          " " + endColor;
    }
    catch (NullPointerException n) {
      throw new IllegalStateException("Fields have not been initialized");
    }

  }
}
