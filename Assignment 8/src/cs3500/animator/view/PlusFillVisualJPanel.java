package cs3500.animator.view;

import cs3500.animator.model.IBackground;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/**
 * an implementation of IPlusFillVisualJPanel that shares common methods with VisualJPanel.
 */
public class PlusFillVisualJPanel extends VisualJPanel implements IPlusFillVisualJPanel {

  private final IViewModel<Shape> animator;
  private IPlusFillInteractiveView view;
  private List<Shape> currShapes;

  /**
   * constructor for the VisualJPanel.
   *
   * @param animator animator to be drawn
   */
  public PlusFillVisualJPanel(IViewModel<Shape> animator) {
    super(animator);
    this.animator = animator;
    this.currShapes = new ArrayList<>();
  }

  @Override
  public void setView(IPlusFillInteractiveView view) {
    this.view = view;
  }


  /**
   * paints the animator onto the jpanel.
   *
   * @param g the graphics being painted onto.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    IBackground bg = animator.getBackground();
    Graphics2D g2 = (Graphics2D) g;
    if (currShapes.size() != 0) {
      for (Shape s : currShapes) {
        g2.setColor(new Color((int) s.getCurrColor().getRed(),
            (int) s.getCurrColor().getGreen(), (int) s.getCurrColor().getBlue()));
        int xpos = (int) s.getCurrentPosition().getX() - bg.getXBound();
        int ypos = (int) s.getCurrentPosition().getY() - bg.getYBound();
        int width = (int) s.getCurrentDimension().getTotalWidth();
        int height = (int) s.getCurrentDimension().getTotalHeight();
        if (s.getShapeType() == ShapeType.RECTANGLE) {
          if (view.getFill()) {
            g2.fillRect(xpos, ypos, width, height);
          } else {
            g2.drawRect(xpos, ypos, width, height);
          }
        }
        if (s.getShapeType() == ShapeType.ELLIPSE) {
          if (view.getFill()) {
            g2.fillOval(xpos, ypos, width, height);
          } else {
            g2.drawOval(xpos, ypos, width, height);
          }
        }
        if (s.getShapeType() == ShapeType.PLUS) {
          GeneralPath plus = new GeneralPath();
          List<Double> plusPointsX = new ArrayList<>();
          List<Double> plusPointsY = new ArrayList<>();
          for (int i = 1; i <= 12; i++) {
            plusPointsX.add(findPointsX(i, s));
            plusPointsY.add(findPointsY(i, s));
          }
          plus.moveTo(plusPointsX.get(0) - bg.getXBound(), plusPointsY.get(0) -
              bg.getYBound());
          for (int i = 1; i < 12; i++) {
            plus.lineTo(plusPointsX.get(i) - bg.getXBound(),
                plusPointsY.get(i) - bg.getYBound());
          }
          plus.closePath();
          if (view.getFill()) {
            g2.fill(plus);
          } else {
            g2.draw(plus);
          }
        }
      }
    }
  }

  /**
   * finds the correct x position of the plus shape
   *
   * @param i the step of drawing the plus shape.
   * @param s the specific plus shape to be drawn.
   * @return the double representing the x coordinate of the plus.
   */
  private double findPointsX(int i, Shape s) {
    double width = s.getCurrentDimension().getTotalWidth();
    double x = s.getCurrentPosition().getX();
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
   * finds the correct y position of the plus shape
   *
   * @param i the step of drawing the plus shape.
   * @param s the specific plus shape to be drawn.
   * @return the double representing the y coordinate of the plus.
   */
  private double findPointsY(int i, Shape s) {
    double height = s.getCurrentDimension().getTotalHeight();
    double y = s.getCurrentPosition().getY();
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

  @Override
  public void drawAnimation(List<Shape> currShapes) {
    this.currShapes = currShapes;
    repaint();
  }

}


