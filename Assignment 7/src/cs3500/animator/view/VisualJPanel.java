package cs3500.animator.view;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.SimpleBackground;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Class to draw the animation. extends JPanel to make the animation. Inherits all its methods to
 * make drawing easier.
 */
public class VisualJPanel extends JPanel implements IVisualJPanel {

  private final IViewModel<Shape> animator;
  private List<Shape> currShapes;

  /**
   * constructor for the VisualJPanel.
   *
   * @param animator animator to be drawn
   */
  public VisualJPanel(IViewModel<Shape> animator) {
    super();
    this.animator = animator;
    this.currShapes = new ArrayList<>();
  }


  /**
   * paints the animator onto the jpanel.
   *
   * @param g the graphics being painted onto.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    SimpleBackground bg = animator.getBackground();
    Graphics2D g2 = (Graphics2D) g;
    if (currShapes.size() != 0) {
      for (Shape s : currShapes) {
        g2.setColor(new Color((int) s.getCurrColor().getRed(),
            (int) s.getCurrColor().getGreen(), (int) s.getCurrColor().getBlue()));
        if (s.getShapeType() == ShapeType.RECTANGLE) {
          g2.fillRect((int) s.getCurrentPosition().getX() - bg.getXBound(),
              (int) s.getCurrentPosition().getY() - bg.getYBound(),
              (int) s.getCurrentDimension().getTotalWidth(),
              (int) s.getCurrentDimension().getTotalHeight());
        }
        if (s.getShapeType() == ShapeType.ELLIPSE) {
          g2.fillOval((int) s.getCurrentPosition().getX() - bg.getXBound(),
              (int) s.getCurrentPosition().getY() - bg.getYBound(),
              (int) s.getCurrentDimension().getTotalWidth(),
              (int) s.getCurrentDimension().getTotalHeight());
        }
      }
    }
  }


  @Override
  public void drawAnimation(List<Shape> currShapes) {
    this.currShapes = currShapes;
    repaint();
  }

}

